package com.examly.springapp.service;
import java.util.List;

import com.examly.springapp.model.*;

public interface HouseService {

    House addHouse(House house);

    List<House> getHousesByType(String houseType);

    void deleteHouseById(String houseId);

    List<House> getAllHouses();

    House getHouseById(String houseId);
}
