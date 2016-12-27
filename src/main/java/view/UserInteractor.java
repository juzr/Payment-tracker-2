/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import controller.PaymentsController;
import data_objects.Money;

/**
 *
 * @author development
 */
@Component
public class UserInteractor implements CommandLineRunner {

	@Autowired
    private PaymentsController controller;    
	
	@Autowired
	private ViewerRun viewerRun;
    
	public void run(String... args) throws Exception {
        System.out.println(" ### Payment Tracker ### ");
        
        Scanner in = new Scanner(System.in);
        tryLoadFile(in);
        tryLoadExchangeRates();
        Thread viewerThread = startViewerThread();
        userInputCycle(in);
        viewerThread.interrupt();
    }

    private void tryLoadFile(Scanner in) {
        String input;
        for (int i=0; i<10; i++){
            try {
                System.out.println("Enter filename or '.' for no file: ");
                input = in.nextLine();
                if (input.equals(".")) break;
                controller.loadFile(input);
                System.out.println("File loaded");
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid format of this file");
            } catch (NoSuchFileException ex){
                System.out.println("File not found");
            } catch (IOException ex) {
                System.out.println("IO error");
            }
        }
    
    }

    private void tryLoadExchangeRates() {
        try {
            controller.loadExchangeRates();
            System.out.println("Exchange rates loaded");
        } catch (InputMismatchException ex) {
            System.out.println("Invalid format of exchange rates file... ignoring...");
        } catch (NoSuchFileException ex) {
            System.out.println("Exchange rates file not found... ignoring...");
        } catch (IOException ex) {
            System.out.println("IO error - exchange rates file... ignoring...");
        }
    }

    private Thread startViewerThread() {
        Thread thread = new Thread(viewerRun);
        thread.start();
        return thread;
    }

    private void userInputCycle(Scanner in) {
        String input;
        System.out.println("Enter payment ([CURRENCY] [amount]) or \"quit\" to exit: ");
        while (true) {
            input = in.nextLine();
            
            if (input.equals("q")) break;
            if (input.equals("quit")) break;
            
            try {
                Money payment = new Money(input);
                controller.paymentEntered(payment);
            } catch (InputMismatchException exc) {
                System.out.println("Invalid format. [A-Z][A-Z][A-Z] [n]   e.g. USD -500");
            }
        }
    }

}
