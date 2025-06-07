package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
}