/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.util.InputMismatchException;

/**
 *
 * @author development
 */
public class Money extends CurrencyNumberPair {
	
    private int amount;
    private ExchangeRate comparativeCurrency = null;

    public Money(String currSpaceNumber) throws InputMismatchException {
        setCurrencyNumberPair(currSpaceNumber);
    }

    public Money(String currency, int amount) {
        super(currency);
        this.amount = amount;
    }

    
    public int getAmount(){
        return amount;
    }

    @Override
    protected void setNumber(String numberStr) throws NumberFormatException {
        amount = Integer.parseInt(numberStr);
    }

    @Override
    public String toString() {
        String res = super.toString() + " " + Integer.toString(amount);
        if ((comparativeCurrency != null) && (!comparativeCurrency.getCurrency().equals(this.getCurrency()))) {
            res += " (" + comparativeCurrencyStr() + ")";
        }
        
        return res;
    }

    @Override
    protected boolean numberEquals(CurrencyNumberPair currencyNumberPair) {
        Money money = (Money) currencyNumberPair;
        return money.getAmount() == this.amount;
    }

    public void setComparativeCurrency(ExchangeRate exchangeRate) {
        comparativeCurrency = exchangeRate;
    }

    private String comparativeCurrencyStr() {
        return comparativeCurrency.getCurrency() + " " + String.format("%.2f", amount * comparativeCurrency.getRate());
    }

    
}
