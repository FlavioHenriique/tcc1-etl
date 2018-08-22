package br.edu.ifpb.etl.view;

import br.edu.ifpb.etl.data.CarregaDados;
import br.edu.ifpb.etl.data.ExtrairDados;
import br.edu.ifpb.etl.data.TransformarDados;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVRecord;

public class Main {

    public static void main(String[] args) {

        String path = "/home/flavio/ADS/5º  Período/T.C.C/dados/201501/"
                + "20150117_Despesas_Empenho.csv";

        try {
            //Extraindo dados
            ExtrairDados extrairDados = new ExtrairDados(path);
            List<CSVRecord> records = extrairDados.getRecords();
            
            //Transformando dados
            TransformarDados transforma = new TransformarDados(records);
            transforma.transformarTodos();
            
            //Carregando dados
            CarregaDados carrega = new CarregaDados();
            carrega.salvarAcoes(transforma.getAcoes());
            carrega.salvarData(transforma.getData());
            carrega.salvarFavorecidos(transforma.getFavorecidos());
            carrega.salvarUnidades(transforma.getUnidades());
            carrega.salvarEmpenhos(transforma.getEmpenhos());
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
