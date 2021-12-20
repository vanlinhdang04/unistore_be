package com.unistore.server.repositories;

import com.unistore.server.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    //get order detail
    @Query(value = "select od.product_id, p.image, p.name, od.quantity, od.total " +
            "from order_details od, product p where od.product_id = p.ProductId and order_id = :order_id", nativeQuery = true)
    public List<?> orderDetail(@Param("order_id") long order_id);
}
