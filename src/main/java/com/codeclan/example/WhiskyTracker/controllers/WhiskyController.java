package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

//    @GetMapping(name = "/whiskies")
//    public ResponseEntity<List<Whisky>> getWhiskies(){
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskies(
            @RequestParam(name = "year", required = false, defaultValue = "0")int year,
            @RequestParam(name = "age", required=false, defaultValue = "0")int age,
            @RequestParam(name="distillery_id", required = false, defaultValue = "0")int distillery_id) {
        if (year == 0 && age == 0 && distillery_id == 0) {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
    }

}
