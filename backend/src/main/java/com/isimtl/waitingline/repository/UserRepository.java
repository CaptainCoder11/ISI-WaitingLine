package com.isimtl.waitingline.repository;

import com.isimtl.waitingline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
