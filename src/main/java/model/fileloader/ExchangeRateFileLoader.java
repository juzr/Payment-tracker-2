/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fileloader;

import data_objects.ExchangeRate;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;


public class ExchangeRateFileLoader extends FileLoader {
    
    public HashMap<String, Double> getExchangeRates(String filename) throws IOException, InputMismatchException {
        HashMap<String, Double> res = new HashMap<>();
        
        List<String> allLines = getFileContent(filename);
        
        ExchangeRate er;
        for (String line : allLines) {
            if (line.equals("")) continue;
            er = new ExchangeRate(line);
            res.put(er.getCurrency(), er.getRate());
        }
        return res;
    }
    
}
