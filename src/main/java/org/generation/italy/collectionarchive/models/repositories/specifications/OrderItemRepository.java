package org.generation.italy.collectionarchive.models.repositories.specifications;

import org.generation.italy.collectionarchive.models.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
