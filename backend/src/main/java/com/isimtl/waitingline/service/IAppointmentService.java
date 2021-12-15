package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.Appointment;
import com.isimtl.waitingline.entity.AppointmentStatus;
import com.isimtl.waitingline.entity.User;

public interface IAppointmentService {
    public void save(Appointment appointment);

    public Appointment findByIds(int userId, int storeId, AppointmentStatus status);
}
