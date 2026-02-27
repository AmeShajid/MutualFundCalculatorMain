package com.ameshajid.mutualfund.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Fund {

    public List<com.ameshajid.mutualfund.model.Fund> getAllFunds() {

        //List to hold all the funds we want to return
        List<com.ameshajid.mutualfund.model.Fund> funds = new ArrayList<>();

        funds.add(new com.ameshajid.mutualfund.model.Fund("VSMPX", "Vanguard Total Stock Market Index Fund;Institutional Plus"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("FXAIX", "Fidelity 500 Index Fund"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("VFIAX", "Vanguard 500 Index Fund;Admiral"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("VTSAX", "Vanguard Total Stock Market Index Fund;Admiral"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("SPAXX", "Fidelity Government Money Market Fund"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("VMFXX", "Vanguard Federal Money Market Fund;Investor"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("VGTSX", "Vanguard Total International Stock Index Fund;Investor"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("SWVXX", "Schwab Prime Advantage Money Market Fund;Inv"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("FDRXX", "Fidelity Government Cash Reserves"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("FGTXX", "Goldman Sachs FS Government Fund;Institutional"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("OGVXX", "JPMorgan US Government Money Market Fund;Capital"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("FCTDX", "Fidelity Strategic Advisers Fidelity US Total Stk"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("VIIIX", "Vanguard Institutional Index Fund;Inst Plus"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("FRGXX", "Fidelity Instl Government Portfolio;Institutional"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("VTBNX", "Vanguard Total Bond Market II Index Fund;Institutional"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("MVRXX", "Morgan Stanley Inst Liq Government Port;Institutional"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("TFDXX", "BlackRock Liquidity FedFund;Institutional"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("GVMXX", "State Street US Government Money Market Fund;Prem"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("AGTHX", "American Funds Growth Fund of America;A"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("VTBIX", "Vanguard Total Bond Market II Index Fund;Investor"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("CJTXX", "JPMorgan 100% US Treasury Securities Money Market Fund;Capital"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("TTTXX", "BlackRock Liquidity Treasury Trust Fund;Institutional"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("FCNTX", "Fidelity Contrafund"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("SNAXX", "Schwab Prime Advantage Money Market Fund;Ultra"));
        funds.add(new com.ameshajid.mutualfund.model.Fund("PIMIX", "PIMCO Income Fund;Institutional"));

        return funds;
    }
}