/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import data_objects.Money;
import model.fileloader.ExchangeRateFileLoader;
import model.fileloader.PaymentFileLoader;

/**
 *
 * @author development
 */
@Component
public class DefaultDataManagerImpl implements DataManager {

    private List<Money> paymentList;
    private ConcurrentHashMap<String, Integer> netAmounts;
    private HashMap<String, Double> exchangeRates;

    public DefaultDataManagerImpl() {
        paymentList = new LinkedList<>();
        netAmounts = new ConcurrentHashMap<>();
    }

    public void loadPaymentBase(String inputFileName) throws InputMismatchException, IOException {
        PaymentFileLoader paymentFileLoader = new PaymentFileLoader();
        paymentList = paymentFileLoader.getPaymentList(inputFileName);
        netAmounts = new ConcurrentHashMap<>();
        for (Money payment : paymentList) {
            updateNetAmounts(payment);
        }
    }
    
    public void putPayment(Money payment){
        paymentList.add(payment);
        updateNetAmounts(payment);
    }
    
    public List<Money> getNetAmounts() {
        List<Money> res = new ArrayList<>();
        Set<Map.Entry<String, Integer>> entrySet = netAmounts.entrySet();
        for (Map.Entry<String, Integer> item : entrySet) {
            res.add(new Money(item.getKey(), item.getValue()));
        }
        return res;
    }

    public Double getExchangeRate(String currency) {
        if (exchangeRates == null) return null;
        return exchangeRates.get(currency);
    }

    public void loadExchangeRates(String filename) throws InputMismatchException, IOException {
        ExchangeRateFileLoader fileLoader = new ExchangeRateFileLoader();
        exchangeRates = fileLoader.getExchangeRates(filename);
    }
    
    private void updateNetAmounts(Money payment) {
        Integer netAmount = netAmounts.get(payment.getCurrency());
        if (netAmount == null){
            netAmount = payment.getAmount();
        } else {
            netAmount += payment.getAmount();
        }
        netAmounts.put(payment.getCurrency(), netAmount);
    }

}
