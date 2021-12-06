package com.isimtl.waitingline.repository;

import com.isimtl.waitingline.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {

}
