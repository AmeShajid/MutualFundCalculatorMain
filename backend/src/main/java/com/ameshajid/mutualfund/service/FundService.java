package com.ameshajid.mutualfund.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ameshajid.mutualfund.model.Fund;

@Service
public class FundService {

    public List<Fund> getAllFunds() {

        //List to hold all the funds we want to return
        List<Fund> funds = new ArrayList<>();

        funds.add(new Fund("VSMPX", "Vanguard Total Stock Market Index Fund;Institutional Plus"));
        funds.add(new Fund("FXAIX", "Fidelity 500 Index Fund"));
        funds.add(new Fund("VFIAX", "Vanguard 500 Index Fund;Admiral"));
        funds.add(new Fund("VTSAX", "Vanguard Total Stock Market Index Fund;Admiral"));
        funds.add(new Fund("SPAXX", "Fidelity Government Money Market Fund"));
        funds.add(new Fund("VMFXX", "Vanguard Federal Money Market Fund;Investor"));
        funds.add(new Fund("VGTSX", "Vanguard Total International Stock Index Fund;Investor"));
        funds.add(new Fund("SWVXX", "Schwab Prime Advantage Money Market Fund;Inv"));
        funds.add(new Fund("FDRXX", "Fidelity Government Cash Reserves"));
        funds.add(new Fund("FGTXX", "Goldman Sachs FS Government Fund;Institutional"));
        funds.add(new Fund("OGVXX", "JPMorgan US Government Money Market Fund;Capital"));
        funds.add(new Fund("FCTDX", "Fidelity Strategic Advisers Fidelity US Total Stk"));
        funds.add(new Fund("VIIIX", "Vanguard Institutional Index Fund;Inst Plus"));
        funds.add(new Fund("FRGXX", "Fidelity Instl Government Portfolio;Institutional"));
        funds.add(new Fund("VTBNX", "Vanguard Total Bond Market II Index Fund;Institutional"));
        funds.add(new Fund("MVRXX", "Morgan Stanley Inst Liq Government Port;Institutional"));
        funds.add(new Fund("TFDXX", "BlackRock Liquidity FedFund;Institutional"));
        funds.add(new Fund("GVMXX", "State Street US Government Money Market Fund;Prem"));
        funds.add(new Fund("AGTHX", "American Funds Growth Fund of America;A"));
        funds.add(new Fund("VTBIX", "Vanguard Total Bond Market II Index Fund;Investor"));
        funds.add(new Fund("CJTXX", "JPMorgan 100% US Treasury Securities Money Market Fund;Capital"));
        funds.add(new Fund("TTTXX", "BlackRock Liquidity Treasury Trust Fund;Institutional"));
        funds.add(new Fund("FCNTX", "Fidelity Contrafund"));
        funds.add(new Fund("SNAXX", "Schwab Prime Advantage Money Market Fund;Ultra"));
        funds.add(new Fund("PIMIX", "PIMCO Income Fund;Institutional"));

        return funds;
    }
}