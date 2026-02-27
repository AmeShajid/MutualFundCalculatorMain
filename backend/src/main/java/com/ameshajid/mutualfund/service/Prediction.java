package com.ameshajid.mutualfund.service;

import org.springframework.stereotype.Service;
import com.ameshajid.mutualfund.model.PredictionResponse;

@Service
public class Prediction {

    private static final double RISK_FREE_RATE = 0.045; // Update as needed
    private final NewtonBeta newtonBetaService;
    private final YahooReturn yahooReturnService;

    public Prediction(NewtonBeta newtonBetaService, YahooReturn yahooReturnService) {
        this.newtonBetaService = newtonBetaService;
        this.yahooReturnService = yahooReturnService;
    }

    public PredictionResponse predict(String ticker, double principal, double years) {
        // Get beta
        double beta = newtonBetaService.getBeta(ticker, "^GSPC", "1mo", 12);

        // Get expected return rate
        double expectedReturn = yahooReturnService.getLastYearReturn(ticker);

        // Calculate CAPM rate: r = rf + beta * (expectedReturn - rf)
        double capmRate = RISK_FREE_RATE + beta * (expectedReturn - RISK_FREE_RATE);

        // Calculate future value: FV = P * e^(r*t)
        double futureValue = principal * Math.exp(capmRate * years);

        return new PredictionResponse(futureValue, beta, expectedReturn, RISK_FREE_RATE);
    }
}
