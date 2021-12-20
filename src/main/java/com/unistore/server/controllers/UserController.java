package com.unistore.server.controllers;

import com.unistore.server.dto.MostUsersShopping;
import com.unistore.server.models.*;
import com.unistore.server.repositories.OrderDetailRepository;
import com.unistore.server.repositories.OrderRepository;
import com.unistore.server.repositories.ProductRepository;
import com.unistore.server.repositories.UserRepository;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getall")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Get all users successfully", users)
        );
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        int offset = limit * (page - 1);
        List<User> orders = userRepository.findAll();
        int totalRow = orders.size();
        int totalPage = (int) Math.ceil( (float)totalRow / limit );
        Pagination pagination = new Pagination(page, totalPage, totalRow);

        if(page<0 || page>totalPage) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Page not found", "", "")
            );
        }

        List<User> usersByPage = userRepository.findByPage(offset, limit);

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Get users successfully", usersByPage, pagination)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        User update = userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setRoles(newUser.getRoles());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setPhone(newUser.getPhone());
                    user.setEnable(newUser.isEnable());
                    return userRepository.save(user);
                }).orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Update user successfully", update)
        );
    }

    @GetMapping("/mostuser")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> mostUsers() {
        List<JSONArray> list = userRepository.usersBuyMost();

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "List top users most shopping", list)
        );
    }
}
