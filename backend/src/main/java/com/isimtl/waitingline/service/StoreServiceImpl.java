package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.*;
import com.isimtl.waitingline.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j

@Service
public class StoreServiceImpl implements IStoreService {

    private StoreRepository storeRepository;
    private FBUserService fbUserService;
    private IAppointmentService appointmentService;
    private ICustomerService customerService;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, FBUserService fbUserService, IAppointmentService appointmentService, ICustomerService customerService) {
        this.storeRepository = storeRepository;
        this.fbUserService = fbUserService;
        this.appointmentService = appointmentService;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public List<Store> findAll() {
        List<Store> stores = this.storeRepository.findAll();

        return (stores);
    }

    @Override
    @Transactional
    public Store findById(int id) {
        Optional<Store> result = storeRepository.findById(Integer.valueOf(id));
        Store store = null;
        if (result.isPresent())
            store = result.get();
        else
            throw new RuntimeException("Employee not found with id - " + id);
        return (store);
    }

    @Override
    @Transactional
    public void save(Store store) {

    }

    @Override
    @Transactional
    public void arrival(int userId, int storeId) throws ExecutionException, InterruptedException {
        //Have to change status in_store, firebase remove from queue, arrival time
        try {
            FBUser fbUser = customerService.getFbUser(userId, storeId);
            Appointment appointment = appointmentService.findByIds(userId,storeId);
            appointment.setStatus(AppointmentStatus.In_Store);
            appointmentService.save(appointment);
            fbUserService.delete(fbUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void departure(int userId, int storeId) throws ExecutionException, InterruptedException {
        //Have to change status in_store, departure time
        try {
            FBUser fbUser = customerService.getFbUser(userId, storeId);
            Appointment appointment = appointmentService.findByIds(userId,storeId);
            appointment.setStatus(AppointmentStatus.Departed);
            appointmentService.save(appointment);
            fbUserService.delete(fbUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }




}
