package com.smecosystem_rest.smecosystem_rest.controller;


import com.smecosystem_rest.smecosystem_rest.model.Company;
import com.smecosystem_rest.smecosystem_rest.repositories.CompanyRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companyRestService")
public class CompanyController {

    @Autowired
    private CompanyRepositoryImpl companyRepositoryImpl;

    @GetMapping("/list")
    public List<Company> getAllUsers() {
        return companyRepositoryImpl.findAll();
    }

}
