package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.Appointment;
import com.isimtl.waitingline.entity.AppointmentStatus;
import com.isimtl.waitingline.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
    AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findByIds(int userId, int storeId, AppointmentStatus status) {
        Optional<Appointment> result = appointmentRepository.findUserByStatus(Integer.valueOf(userId),Integer.valueOf(storeId),status);
        Appointment appointment = null;
        if (result.isPresent())
            appointment = result.get();
        else
            throw new RuntimeException("User not found with id - " + userId);
        return (appointment);
    }

    @Override
    public List<Appointment> inStoreUsers(int storeId) {
        Optional<List<Appointment>> result = appointmentRepository.inStoreUsers(Integer.valueOf(storeId),AppointmentStatus.In_Store);
        List<Appointment> appointment = null;
        if (result.isPresent()) {
            appointment = result.get();
            System.out.println(appointment);
        }else
            throw new RuntimeException("Store not found ");
        return (appointment);
    }
}
