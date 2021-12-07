package com.isimtl.waitingline.service;

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
    public CustomerServiceImpl(UserRepository userRepository,  MailService mailService) {
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

        if (userRepository.userExist(user.getEmail(), user.getPhone())) {
            User tempUser = userRepository.getUser(user.getEmail(), user.getPhone());
            System.out.println(tempUser);
            user.setId(tempUser.getId());
            user.setEmail(tempUser.getEmail());
            user.setPhone(tempUser.getPhone());
            user.setDateAdded(tempUser.getDateAdded());
        } else {
            System.out.println(user);
            user.setDateAdded(LocalDateTime.now());
            user.setId(0);
        }

        String otp = Utils.getOTP();
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
        mailService.sendTextEmail(otp, user.getEmail());
        userRepository.save(user);
        return (user);
    }

    @Override
    public User verify(User user) {
        User tempUser = (findById(user.getId()));
        System.out.println(tempUser);
        if (tempUser.getOtp() != null && tempUser.getOtpExpiry() != null) {
            boolean isUserVefiry = user.getOtp().equals(tempUser.getOtp());
            boolean isValidOtp = (LocalDateTime.now()).until(tempUser.getOtpExpiry(), ChronoUnit.MINUTES) > 0;
            if (isValidOtp) {
                if (!isUserVefiry)
                    System.out.println("Invalid OTP");
                else
                    System.out.println("Verification Done");
            } else {
                System.out.println("OTP Expire");
            }
        } else
            System.out.println("Invalid OTP. Try to login again");

        return (tempUser);
    }
}
