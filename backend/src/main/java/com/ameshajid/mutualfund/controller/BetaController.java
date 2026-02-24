package com.ameshajid.mutualfund.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ameshajid.mutualfund.service.NewtonBetaService;

@RestController
@RequestMapping("/api")
public class BetaController {

    private final NewtonBetaService newtonBetaService;

    public BetaController(NewtonBetaService newtonBetaService) {
        this.newtonBetaService = newtonBetaService;
    }

    @GetMapping("/beta")
    public BetaResult getBeta(
            @RequestParam String ticker,
            @RequestParam(defaultValue = "^GSPC") String index,
            @RequestParam(defaultValue = "1mo") String interval,
            @RequestParam(defaultValue = "10") int observations
    ) {

        double beta = newtonBetaService.getBeta(ticker, index, interval, observations);

        BetaResult result = new BetaResult();
        result.setTicker(ticker);
        result.setIndex(index);
        result.setInterval(interval);
        result.setObservations(observations);
        result.setBeta(beta);

        return result;
    }

    public static class BetaResult {
        private String ticker;
        private String index;
        private String interval;
        private int observations;
        private double beta;

        public String getTicker() { return ticker; }
        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public String getIndex() { return index; }
        public void setIndex(String index) {
            this.index = index;
        }

        public String getInterval() { return interval; }
        public void setInterval(String interval) {
            this.interval = interval;
        }

        public int getObservations() { return observations; }
        public void setObservations(int observations) {
            this.observations = observations;
        }

        public double getBeta() { return beta; }
        public void setBeta(double beta) {
            this.beta = beta;
        }
    }
}