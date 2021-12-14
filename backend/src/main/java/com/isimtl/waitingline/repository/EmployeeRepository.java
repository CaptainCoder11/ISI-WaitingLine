package com.isimtl.waitingline.repository;

import com.isimtl.waitingline.entity.Employee;
import com.isimtl.waitingline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT u FROM Employee u where LOWER(u.email) = LOWER(?1)")
    public Optional<Employee> findByEmail(@Param("email") String email);
}
