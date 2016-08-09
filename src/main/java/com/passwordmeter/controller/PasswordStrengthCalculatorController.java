package com.passwordmeter.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.passwordmeter.service.PasswordStrengthCalculatorService;
import com.passwordmeter.vo.ScoreResultVO;

@RestController
@RequestMapping(value = "calculateScore")
public class PasswordStrengthCalculatorController {

    @Autowired
    private PasswordStrengthCalculatorService service;
    
    @RequestMapping(value = "/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreResultVO> calculateStrength(@PathVariable("password") Optional<String> password) {
        if(!password.isPresent()) {
            password = Optional.of("");
        }
        ScoreResultVO scoreResultVO = this.service.calculateStrength(password.get());
        return new ResponseEntity<ScoreResultVO>(scoreResultVO, HttpStatus.OK);
    }
}