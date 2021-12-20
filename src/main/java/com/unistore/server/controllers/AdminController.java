package com.unistore.server.controllers;

import com.unistore.server.common.JwtUtils;
import com.unistore.server.dto.DashBoardResponse;
import com.unistore.server.dto.MessageResponse;
import com.unistore.server.models.ResponseObject;
import com.unistore.server.repositories.OrderDetailRepository;
import com.unistore.server.repositories.OrderRepository;
import com.unistore.server.repositories.ProductRepository;
import com.unistore.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
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

    @GetMapping("/check")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> check(@RequestHeader String Authorization) {
        String token = Authorization.substring(7, Authorization.length());
        String username = jwtUtils.getUsernameFromJwtToken(token);
        return ResponseEntity.ok().body(
                new MessageResponse("ok", username)
        );
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> dashboard() {
        long countProduct = productRepository.count();
        long countUser = userRepository.count();
        long countOrder = orderRepository.count();
        long countOutOfStock = productRepository.countOutOfStock();
        long countUsersBlock = userRepository.countUsersBlock();
        double totalMoney = orderRepository.totalMoney();
        int countAdminMod = userRepository.countAdminMod();
        int countProductNo = productRepository.countProductNoQuantity();
        int countOrdersCancel = orderRepository.countOrdersCancel();


        DashBoardResponse dbr = new DashBoardResponse(countUser, countProduct, countOrder, countOutOfStock, countUsersBlock, totalMoney, countAdminMod, countProductNo, countOrdersCancel);

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Get data dashboard successfully", dbr)
        );
    }

}
