package com.unistore.server.controllers;

import com.unistore.server.common.JwtUtils;
import com.unistore.server.dto.MessageResponse;
import com.unistore.server.dto.OrderRequest;
import com.unistore.server.models.*;
import com.unistore.server.repositories.OrderDetailRepository;
import com.unistore.server.repositories.OrderRepository;
import com.unistore.server.repositories.ProductRepository;
import com.unistore.server.repositories.UserRepository;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addOrder(@Validated @RequestHeader String Authorization, @RequestBody OrderRequest orderRequest) throws Exception {
        String token = Authorization.substring(7, Authorization.length());
        String username = jwtUtils.getUsernameFromJwtToken(token);

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Not found user")
        );

        //Time
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDate nowDate = nowDateTime.toLocalDate();

        LocalDate received = nowDate.plusDays(7);

        String purchase_date = nowDateTime.format(dateTimeFormatter);
        String received_date = received.format(dateFormatter);


        Order order = new Order(user.getId(), orderRequest.getName(), orderRequest.getQuantity(), orderRequest.getTotal(), "wait", orderRequest.getAddress(), purchase_date, received_date);

        orderRepository.save(order);

        JSONArray products = orderRequest.getProduct();
        for (int i = 0; i < products.size(); i++) {
            HashMap<String, Integer> detail = (HashMap<String, Integer>) products.get(i);

            Product product = productRepository.findById(Long.valueOf(detail.get("id"))).orElseThrow(
                    () -> new Exception("Not found product.")
            );

            //update quantity and sold product
            product.setQuantity(product.getQuantity() - detail.get("amount"));
            product.setSold(product.getSold() + detail.get("amount"));
            productRepository.save(product);

            Double total = product.getPrice() * Double.parseDouble(String.valueOf(detail.get("amount")));

            OrderDetail orderDetail = new OrderDetail(order.getOrder_id(), Long.valueOf(detail.get("id")), detail.get("amount"), product.getPrice(), total);
//            System.out.println("a: "+orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        return ResponseEntity.ok().body(
                new MessageResponse("ok", "Order successfully")
        );
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        int offset = limit * (page - 1);
        List<Order> orders = orderRepository.findAll();
        int totalRow = orders.size();
        int totalPage = (int) Math.ceil( (float)totalRow / limit );
        Pagination pagination = new Pagination(page, totalPage, totalRow);

        if(page<0 || page>totalPage) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Page not found", "", "")
            );
        }

        List<Order> ordersByPage = orderRepository.findByPage(offset, limit);

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Get order successfully", ordersByPage, pagination)
        );
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> orderDetails(@PathVariable Long id) {
        List<?> list = orderDetailRepository.orderDetail(id);

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Order detail", list)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        Order update = orderRepository.findById(id)
                .map(order -> {
                    order.setName(newOrder.getName());
                    order.setAddress(newOrder.getAddress());
                    order.setStatus(newOrder.getStatus());
                    order.setReceived_date(newOrder.getReceived_date());
                    return orderRepository.save(order);
                }).orElseGet(() -> {
                    newOrder.setOrder_id(id);
                    return orderRepository.save(newOrder);
                });

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Update order successfully", update)
        );
    }
}
