package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.Appointment;
import com.isimtl.waitingline.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
