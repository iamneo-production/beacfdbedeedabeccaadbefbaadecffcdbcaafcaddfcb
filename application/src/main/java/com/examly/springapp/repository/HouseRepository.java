package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.examly.springapp.model.*;

public interface HouseRepository extends JpaRepository<House, Long> {

    List<House> findByType(String houseType);

    void deleteById(String houseId);

}