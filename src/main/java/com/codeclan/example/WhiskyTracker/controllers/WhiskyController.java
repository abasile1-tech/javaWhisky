package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskies(
            @RequestParam(name = "year", required = false, defaultValue = "0") int year,
            @RequestParam(name = "age", required = false, defaultValue = "0") int age,
            @RequestParam(name = "distillery_id", required = false, defaultValue = "0") Long distillery_id) {
        if (year != 0 && age == 0 && distillery_id == 0) {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        } else if (age != 0 && year == 0 && distillery_id == 0) {
            return new ResponseEntity<>(whiskyRepository.findByAge(age), HttpStatus.OK);
        } else if (distillery_id != 0 && year == 0 && age == 0) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryId(distillery_id), HttpStatus.OK);
        } else if (distillery_id != 0 && age != 0 ){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryIdAndAge(distillery_id, age), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        }
    }

}
