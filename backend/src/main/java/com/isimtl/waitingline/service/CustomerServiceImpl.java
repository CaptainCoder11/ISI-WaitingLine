package com.isimtl.waitingline.service;

import com.isimtl.waitingline.Exception.OtpException;
import com.isimtl.waitingline.Utils.Utils;
import com.isimtl.waitingline.entity.User;
import com.isimtl.waitingline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private UserRepository userRepository;
    private MailService mailService;

    @Autowired
    public CustomerServiceImpl(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public List<User> findAll() {
        List<User> user = this.userRepository.findAll();
        System.out.println(user);
        return (user);
    }

    @Override
    public User findById(int id) {
        Optional<User> result = userRepository.findById(Integer.valueOf(id));
        User user = null;
        if (result.isPresent())
            user = result.get();
        else
            throw new RuntimeException("User not found with id - " + id);
        return (user);
    }

    @Override
    public User save(User user) throws IOException {
        Optional<User> tempUser = userRepository.findByEmail(user.getEmail());
        if (tempUser.isPresent()) {
            if (tempUser.get().getPhone() != user.getPhone()) {
                try {
                    String phone = user.getPhone() == null ? tempUser.get().getPhone() : user.getPhone();
                    user.setPhone(phone);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            user.setEmail(tempUser.get().getEmail());
            user.setId(tempUser.get().getId());
            user.setDateAdded(tempUser.get().getDateAdded());
        } else {
            user.setDateAdded(LocalDateTime.now());
            user.setId(0);
        }
        String otp = Utils.getOTP();
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));

        mailService.sendTextEmail(otp, user.getEmail(),user.getVerificationData());
        userRepository.save(user);
        return user;
    }

    @Override
    public User verify(User user) {
        System.out.println(user);
        User tempUser = (findById(user.getId()));
        System.out.println(tempUser);
        if (tempUser.getOtp() != null && tempUser.getOtpExpiry() != null) {
            boolean isValidOtp = (LocalDateTime.now()).until(tempUser.getOtpExpiry(), ChronoUnit.MINUTES) > 0;
            boolean isUserVefiry = user.getOtp().equals(tempUser.getOtp());
            if (isValidOtp) {
                if (!isUserVefiry)
                    throw new OtpException("Invalid Otp");
                else {
                    tempUser.setOtp(null);
                    tempUser.setOtpExpiry(null);
                }
            } else {
                throw new OtpException("OTP is expired");
            }
        } else
            throw new OtpException("Invalid OTP");

        userRepository.save(tempUser);
        return (tempUser);
    }

}
