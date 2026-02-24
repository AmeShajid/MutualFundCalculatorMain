package com.ameshajid.mutualfund.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ameshajid.mutualfund.model.Fund;
import com.ameshajid.mutualfund.service.FundService;

@RestController
@RequestMapping("/api")
public class FundController {

    //accessing the service layer
    private FundService fundService;

    //Constructor
    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    //This is the GET for the funds
    @GetMapping("/funds")
    public List<Fund> getFunds() {

        //returning the hardcoded list
        return fundService.getAllFunds();
    }
}
