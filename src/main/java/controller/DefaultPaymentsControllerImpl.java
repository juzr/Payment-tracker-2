/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data_objects.ExchangeRate;
import data_objects.Money;
import model.DataManager;

/**
 *
 * @author development
 */
@Controller
public class DefaultPaymentsControllerImpl implements PaymentsController {
    
    final private static String COMPARATIVE_CURRENCY = "USD";
    final private static String EXCHANGE_RATES_FILE = "er_" + COMPARATIVE_CURRENCY;
    
    private boolean useExchRates = false;

    @Autowired
    private DataManager dataManager;

    
    @Override
    public void loadFile(String inputFileName) throws InputMismatchException, IOException {
        dataManager.loadPaymentBase(inputFileName);
    }

    @Override
    public void loadExchangeRates() throws InputMismatchException, IOException {
        dataManager.loadExchangeRates(EXCHANGE_RATES_FILE);
        useExchRates = true;
    }

    @Override
    public void paymentEntered(Money payment) {
        dataManager.putPayment(payment);
    }

    @Override
    public List<Money> getNetAmounts(){
        if (useExchRates){
            return enrichNetAmountsByComparativeCurrency(dataManager.getNetAmounts());
        } else {
            return dataManager.getNetAmounts();
        }
    }

    private List<Money> enrichNetAmountsByComparativeCurrency(List<Money> netAmounts) {
        Double rate;
        for (Money netAmount : netAmounts) {
            rate = dataManager.getExchangeRate(netAmount.getCurrency());
            if (rate == null) continue;
            netAmount.setComparativeCurrency(new ExchangeRate(COMPARATIVE_CURRENCY, rate));
        }
        return netAmounts;
    }
}
