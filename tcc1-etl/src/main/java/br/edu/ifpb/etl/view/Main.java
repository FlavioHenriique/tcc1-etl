package br.edu.ifpb.etl.view;

import br.edu.ifpb.etl.jpa.FindJPA;
import br.edu.ifpb.etl.jpa.PersistJPA;
import br.edu.ifpb.etl.jpa.VerificacaoDados;
import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.read.ReadCSV;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            ReadCSV read = new ReadCSV("/home/flavio/20170101_Despesas_Empenho.csv");

            VerificacaoDados dados = new VerificacaoDados(
                    "/home/flavio/20180531_Despesas_Empenho.csv");

            //read.getAcoes();
            //dados.salvarAcoes();
            //dados.salvarFavorecidos();
            //dados.salvarData();
            dados.salvarUnidades();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
