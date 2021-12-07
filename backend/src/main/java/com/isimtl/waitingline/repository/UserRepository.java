package com.isimtl.waitingline.repository;

import com.isimtl.waitingline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
