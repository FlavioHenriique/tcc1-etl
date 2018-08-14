package br.edu.ifpb.etl.view;

import br.edu.ifpb.etl.read.ReadCSV;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    public static void main(String[] args) {
        
        try {
            ReadCSV read = new ReadCSV("/home/flavio/20170101_Despesas_Empenho.csv");
            read.getAcao();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
