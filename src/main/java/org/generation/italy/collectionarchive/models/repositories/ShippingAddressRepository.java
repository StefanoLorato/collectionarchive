package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
    List<ShippingAddress> findByUserUserId(Integer id);
}