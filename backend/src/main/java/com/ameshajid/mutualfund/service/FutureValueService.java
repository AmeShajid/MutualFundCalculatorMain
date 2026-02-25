package com.ameshajid.mutualfund.service;

import org.springframework.stereotype.Service;

import com.ameshajid.mutualfund.model.FutureValueResponse;

@Service
public class FutureValueService {

    private static final double RISK_FREE_RATE = 0.04;

    private final NewtonBetaService newtonBetaService;
    private final YahooReturnService yahooReturnService;

    public FutureValueService(NewtonBetaService newtonBetaService, YahooReturnService yahooReturnService) {
        this.newtonBetaService = newtonBetaService;
        this.yahooReturnService = yahooReturnService;
    }

    public FutureValueResponse calculateFutureValue(String symbol, double principal, int years) {

        double beta = newtonBetaService.getBeta(symbol, "^GSPC", "1mo", 12);

        double expectedReturnRate = yahooReturnService.getLastYearReturn(symbol);

        double capmRate = RISK_FREE_RATE + (beta * (expectedReturnRate - RISK_FREE_RATE));

        double futureValue = principal * Math.exp(capmRate * years);

        FutureValueResponse response = new FutureValueResponse();
        response.setSymbol(symbol);
        response.setPrincipal(principal);
        response.setYears(years);

        response.setRiskFreeRate(RISK_FREE_RATE);
        response.setExpectedReturnRate(expectedReturnRate);
        response.setBeta(beta);

        response.setCapmRate(capmRate);
        response.setFutureValue(futureValue);

        return response;
    }
}
