package com.ameshajid.mutualfund.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameshajid.mutualfund.model.Fund;
import com.ameshajid.mutualfund.service.FundService;

@RestController
@RequestMapping("/api")
public class FundController {

    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping("/funds")
    public List<Fund> getFunds() {
        return fundService.getAllFunds();
    }
}
