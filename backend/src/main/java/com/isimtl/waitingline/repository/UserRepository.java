package com.isimtl.waitingline.repository;

import com.isimtl.waitingline.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT CASE WHEN count(u) > 0 THEN true ELSE false END FROM User u where LOWER(u.email) = LOWER(?1) OR LOWER(u.phone) = LOWER(?2)")
    boolean userExist(@Param("email") String email, @Param("phone") String phone);

    @Query(value = "SELECT * FROM User where LOWER(email) = LOWER(?1) OR LOWER(phone) = LOWER(?2) ", nativeQuery = true)
    User getUser(@Param("email") String email, @Param("phone") String phone);
}
