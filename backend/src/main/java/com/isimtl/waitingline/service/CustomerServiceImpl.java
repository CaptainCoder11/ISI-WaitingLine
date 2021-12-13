package com.isimtl.waitingline.service;

import com.isimtl.waitingline.Exception.OtpException;
import com.isimtl.waitingline.Utils.Utils;
import com.isimtl.waitingline.entity.*;
import com.isimtl.waitingline.repository.StoreRepository;
import com.isimtl.waitingline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private UserRepository userRepository;
    private StoreRepository storeRepository;
    private MailService mailService;
    private FBUserService fbUserService;
    private IAppointmentService appointmentService;

    @Autowired
    public CustomerServiceImpl(UserRepository userRepository, StoreRepository storeRepository, MailService mailService, FBUserService fbUserService, IAppointmentService appointmentService) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.mailService = mailService;
        this.fbUserService = fbUserService;
        this.appointmentService = appointmentService;
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
        mailService.sendTextEmail(otp, user.getEmail(), user.getVerificationData());
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

    @Override
    public void joinWaitingLine(int userId, int storeId) throws ExecutionException, InterruptedException {
        FBUser fbUser = getFbUser(userId, storeId);
        appointmentService.save(getAppointmentDetail(fbUser));
        fbUserService.save(fbUser);
    }

    @Override
    public void removeWaitingLine(int userId, int storeId) throws ExecutionException, InterruptedException {
        try {
            FBUser fbUser = getFbUser(userId, storeId);
            fbUserService.delete(fbUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private FBUser getFbUser(int userId, int storeId) {
        Optional<Store> storeResult = storeRepository.findById(Integer.valueOf(storeId));
        Store store = null;
        if (storeResult.isPresent())
            store = storeResult.get();
        else
            throw new RuntimeException("Invalid Store");

        Optional<User> userResult = userRepository.findById(Integer.valueOf(userId));
        User user = null;
        if (userResult.isPresent())
            user = userResult.get();
        else
            throw new RuntimeException("Invalid Store");

        FBUser fbUser = new FBUser(store, user);
        return fbUser;
    }

    private Appointment getAppointmentDetail(FBUser fbUser) {
        Appointment appointment = new Appointment();
        appointment.setUserId(fbUser.getId());
        appointment.setStoreId(fbUser.getStoreId());
        appointment.setDateCreated(LocalDateTime.now());
        appointment.setStatus(AppointmentStatus.In_Queue);
        appointment.setAppointmentNumber(99);
        appointment.setId(0);
        return appointment;

    }

}
