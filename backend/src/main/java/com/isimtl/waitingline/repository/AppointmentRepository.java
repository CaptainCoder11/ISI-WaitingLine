package com.isimtl.waitingline.repository;

import com.isimtl.waitingline.entity.Appointment;
import com.isimtl.waitingline.entity.AppointmentStatus;
import com.isimtl.waitingline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    @Query(value = "SELECT a FROM Appointment a where (a.user.id) = (?1) AND (a.storeId) = (?2) AND (a.status) = (?3)")
    public Optional<Appointment> findUserByStatus(@Param("userId") int userId, @Param("storeId") int storeId , @Param("status") AppointmentStatus status);

    @Query(value = "SELECT a FROM Appointment a where (a.storeId) = (?1) AND (a.status) = (?2)")
    public Optional<List<Appointment>> inStoreUsers(@Param("storeId") int storeId , @Param("status") AppointmentStatus status);

}
