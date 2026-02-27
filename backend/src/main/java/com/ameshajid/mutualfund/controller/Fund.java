package com.ameshajid.mutualfund.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Fund {

    private final com.ameshajid.mutualfund.service.Fund fundService;

    public Fund(com.ameshajid.mutualfund.service.Fund fundService) {
        this.fundService = fundService;
    }

    @GetMapping("/funds")
    public List<com.ameshajid.mutualfund.model.Fund> getFunds() {
        return fundService.getAllFunds();
    }
}
