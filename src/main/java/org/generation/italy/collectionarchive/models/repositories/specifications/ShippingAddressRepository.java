package org.generation.italy.collectionarchive.models.repositories.specifications;

import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
}