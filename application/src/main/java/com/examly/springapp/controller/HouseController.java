package com.examly.springapp.controller;

import com.examly.springapp.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HouseController {
    private List<House> houses = new ArrayList<>();

    @PostMapping("/saveHouse")
    public ResponseEntity<String> saveHouse(@RequestBody House house) {
        houses.add(house);
        return new ResponseEntity<>("House added successfully", HttpStatus.OK);
    }

    @GetMapping("/getAllHouse")
    public ResponseEntity<List<House>> getAllHouses() {
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @GetMapping("/getHouse")
    public ResponseEntity<House> getHouseById(@RequestParam("id") String id) {
        House house = houses.stream()
                .filter(h -> h.getHouseId().equals(id))
                .findFirst()
                .orElse(null);
        if (house != null) {
            return new ResponseEntity<>(house, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByType")
    public ResponseEntity<List<House>> getHousesByType(@RequestParam("type") String type) {
        List<House> filteredHouses = houses.stream()
                .filter(h -> h.getType().equals(type))
                .collect(Collectors.toList());
        if (!filteredHouses.isEmpty()) {
            return new ResponseEntity<>(filteredHouses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/deleteHouse")
    public ResponseEntity<String> deleteHouse(@RequestParam("id") String id) {
        houses.removeIf(h -> h.getHouseId().equals(id));
        return new ResponseEntity<>("House deleted successfully", HttpStatus.OK);
    }
}
