package com.example.firstSB.Controller;

import com.example.firstSB.service.CalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cal")
public class CalController {

    @Autowired // Dependency Inj
    CalService calService;

    @GetMapping("/add/{first}/{second}")
    public double add(@PathVariable("first") double firstNumber,
                      @PathVariable("second") double secondNumber){
        return firstNumber + secondNumber;
    }

    @GetMapping("/divide/{first}/{second}")
    public ResponseEntity<Object> divide(@PathVariable("first") double firstNumber,
                         @PathVariable("second") double secondNumber){
        if(secondNumber==0){
            return new ResponseEntity<>( "Divide by Zero is not possible, choose a different number and try again", HttpStatus.BAD_REQUEST);
        }
        double result = calService.divide(firstNumber, secondNumber);
        return new ResponseEntity<>( result, HttpStatus.OK);
    }

}
