/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PaymentsController;
import data_objects.Money;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author development
 */
@Component
public class NetAmountsViewerRun implements ViewerRun {
	
    final long WAITING_TIME = 60000;   // 1 min
    
    @Autowired
    private PaymentsController controller;

    @Override
    public void run() {
        while (true){
            try {
                showMsg();
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }

    private void showMsg() {
        List<Money> netAmounts = controller.getNetAmounts();
        String msg = "\n";
        for (Money netAmount : netAmounts) {
            if (netAmount.getAmount() != 0) msg += netAmount + "\n";
        }
        System.out.println(msg);
    }
    
}
