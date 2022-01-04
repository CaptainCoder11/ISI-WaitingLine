package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.Appointment;
import com.isimtl.waitingline.entity.AppointmentStatus;
import com.isimtl.waitingline.entity.User;

import java.util.List;

public interface IAppointmentService {
    public void save(Appointment appointment);

    public Appointment findByIds(int userId, int storeId, AppointmentStatus status);

    public List<Appointment> inStoreUsers(int storeId);
}
