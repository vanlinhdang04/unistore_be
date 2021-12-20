package com.unistore.server.repositories;

import com.unistore.server.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders order by orders.order_id desc", nativeQuery = true)
    public List<Order> findAllOrderByIdDesc();

    //find by page
    @Query(value = "SELECT * from orders order by orders.order_id desc LIMIT :offset , :limit", nativeQuery = true)
    public List<Order> findByPage(@Param("offset") int offset, @Param("limit") int limit);

    //get total money
    @Query(value = "select ifnull(sum(total),0) from orders where status='success'", nativeQuery = true)
    public double totalMoney();

    //count orders cancel
    @Query(value = "select count(order_id) from orders where orders.status = 'cancel'", nativeQuery = true)
    public int countOrdersCancel();


}
