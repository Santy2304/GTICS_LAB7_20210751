package com.example.lab7_20210751.Repository;

import com.example.lab7_20210751.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(nativeQuery = true,value = "select u.name from users u INNER JOIN resources r on (u.authorizedResource = r.resourceId) where r.resourceId =?1 and u.active=1")
    List<User> listar(int id);

}
