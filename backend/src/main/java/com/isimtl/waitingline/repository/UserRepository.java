package com.isimtl.waitingline.repository;

import com.isimtl.waitingline.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u where LOWER(u.email) = LOWER(?1)")
    public Optional<User> findByEmail(@Param("email") String email);
}
