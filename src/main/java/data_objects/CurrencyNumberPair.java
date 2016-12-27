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
public abstract class CurrencyNumberPair {
	
    private String currency;

    protected CurrencyNumberPair() {
    }
    
                                        // e.g. "USD -4000"
    protected void setCurrencyNumberPair(String currSpaceNumber) throws InputMismatchException {     
        String[] splitArr = currSpaceNumber.split(" ");
        if (splitArr.length != 2) throw new InputMismatchException();

        currency = splitArr[0];       
        if (currency.length() != 3) throw new InputMismatchException();
        for (int i=0; i<currency.length(); i++) if (!Character.isLetter(currency.charAt(i))) throw new InputMismatchException();
        if (!currency.toUpperCase().equals(currency)) throw new InputMismatchException();
        
        try {
            setNumber(splitArr[1]);
        } catch (NumberFormatException ex) {
            throw new InputMismatchException();
        }
    }

    protected CurrencyNumberPair(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) return false;
        CurrencyNumberPair currencyNumberPair = (CurrencyNumberPair) o;
        if (!numberEquals(currencyNumberPair)) return false;
        if (!currencyNumberPair.getCurrency().equals(currency)) return false;
        return true;
    }

    protected abstract void setNumber(String numberStr);

    protected abstract boolean numberEquals(CurrencyNumberPair currencyNumberPair);
    
}
