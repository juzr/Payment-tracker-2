package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

import data_objects.Money;

public interface PaymentsController {
	
	void loadFile(String inputFileName) throws InputMismatchException, IOException;
	
	void loadExchangeRates() throws InputMismatchException, IOException;

	void paymentEntered(Money payment);
	
	List<Money> getNetAmounts();
	
}
