package com.unistore.server.repositories;

import com.unistore.server.models.User;
import org.json.simple.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Find user by username
    Optional<User> findByUsername(String username);

    //Check exists an user by username
    Boolean existsByUsername(String username);

    //Check exists an user email
    Boolean existsByEmail(String email);

    //Check enable
    @Query(value = "select enable from users where username = :username", nativeQuery = true)
    Boolean isEnable(@Param("username") String username);

    //find by page
    @Query(value = "SELECT * from users order by users.id desc LIMIT :offset , :limit", nativeQuery = true)
    public List<User> findByPage(@Param("offset") int offset, @Param("limit") int limit);

    //count user not enable
    @Query(value = "SELECT count(id) from users where users.enable = false", nativeQuery = true)
    public int countUsersBlock();

    //count user admin mod
    @Query(value = "SELECT * FROM users u, user_roles ur where u.id = ur.user_id and ur.role_id = 1 or ur.role_id = 2", nativeQuery = true)
    public int countAdminMod();

    //list user buy most money
    @Query(value = "select u.id, u.name, count(id) as count_order, sum(total) as total from users u, orders o where u.id = o.user_id group by u.id order by total desc limit 10", nativeQuery = true)
    public List<JSONArray> usersBuyMost();
}
