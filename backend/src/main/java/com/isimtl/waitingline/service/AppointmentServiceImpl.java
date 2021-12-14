package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.Appointment;
import com.isimtl.waitingline.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Appointment findByIds(int userId, int storeId) {
        Optional<Appointment> result = appointmentRepository.findByIDs(Integer.valueOf(userId),Integer.valueOf(storeId));
        Appointment appointment = null;
        if (result.isPresent())
            appointment = result.get();
        else
            throw new RuntimeException("User not found with id - " + userId);
        return (appointment);

    }
}
