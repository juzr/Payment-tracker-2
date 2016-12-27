package model;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

import data_objects.Money;

public interface DataManager {
	
	void loadPaymentBase(String inputFileName) throws InputMismatchException, IOException;
	
	void putPayment(Money payment);
	
	List<Money> getNetAmounts();
	
	Double getExchangeRate(String currency);
	
	void loadExchangeRates(String filename) throws InputMismatchException, IOException;
	
}
