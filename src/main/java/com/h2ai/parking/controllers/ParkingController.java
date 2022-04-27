package com.h2ai.parking.controllers;

import com.h2ai.parking.models.Parking;
import com.h2ai.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class ParkingController {
    @Autowired
    private ParkingService parkingService;
    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/parkings", method = RequestMethod.GET)
    public List<Parking> getListePakings(){
        return parkingService.getListeParkings();
    }
}
