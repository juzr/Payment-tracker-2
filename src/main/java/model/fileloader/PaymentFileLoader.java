/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fileloader;

import data_objects.Money;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author development
 */
public class PaymentFileLoader extends FileLoader {

    public List<Money> getPaymentList(String filename) throws IOException, InputMismatchException {
        List<Money> res = new LinkedList<>();
        
        List<String> allLines = getFileContent(filename);
        
        for (String line : allLines) {
            if (line.equals("")) continue;
            res.add(new Money(line));
        }
        
        return res;
    }


}
