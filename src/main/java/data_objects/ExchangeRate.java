/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.util.InputMismatchException;


public class ExchangeRate extends CurrencyNumberPair {
	
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public ExchangeRate(String currSpaceNumber)throws InputMismatchException{
        setCurrencyNumberPair(currSpaceNumber);
    }
    
    public ExchangeRate(String currency, Double rate) {
        super(currency);
        this.rate = rate;
    }

    @Override
    protected void setNumber(String numberStr) throws NumberFormatException{
        rate = Double.parseDouble(numberStr);
    }

    @Override
    protected boolean numberEquals(CurrencyNumberPair currencyNumberPair) {
        ExchangeRate exchangeRate = (ExchangeRate) currencyNumberPair;
        return Double.compare(exchangeRate.getRate(), this.rate) == 0;
    }

}
