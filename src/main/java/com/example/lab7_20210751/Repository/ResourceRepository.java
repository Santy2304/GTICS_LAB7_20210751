package com.example.lab7_20210751.Repository;

import com.example.lab7_20210751.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,Integer> {
}
