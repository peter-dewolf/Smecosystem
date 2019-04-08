package com.smecosystem_rest.smecosystem_rest.controller;

import com.smecosystem_rest.smecosystem_rest.model.Smartcontract;
import com.smecosystem_rest.smecosystem_rest.repositories.SmartcontractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/smartcontractRestservice")
public class SmartcontractController {

    @Autowired
    private SmartcontractRepository smartcontractRepository;

    @GetMapping("/list")
    public List<Smartcontract> getAllContracts() {
        return smartcontractRepository.findAll();
    }





}
