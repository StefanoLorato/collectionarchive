package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByBuyerUserId(int id);
    @Query("""
            SELECT DISTINCT o FROM Order o
            JOIN FETCH o.orderItems oi
            WHERE oi.seller.userId = :sellerId
            """)
    List<Order> findOrdersWithItemsFromSeller(@Param("sellerId") int sellerId);
}
