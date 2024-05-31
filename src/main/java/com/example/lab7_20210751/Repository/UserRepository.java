package com.example.lab7_20210751.Repository;

import com.example.lab7_20210751.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
