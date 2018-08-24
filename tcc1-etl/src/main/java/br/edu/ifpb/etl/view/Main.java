package br.edu.ifpb.etl.view;

import br.edu.ifpb.etl.data.CarregaDados;
import br.edu.ifpb.etl.data.ExtrairDados;
import br.edu.ifpb.etl.data.TransformarDados;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVRecord;

public class Main {

    public static void main(String[] args) {

        ExtrairDados extrairDados;
        try {
            extrairDados = new ExtrairDados("/home/flavio/ADS/5º  Período/T.C.C/dados/201501/"
                    + "20150101_Despesas_Empenho.csv");
            List<CSVRecord> records = extrairDados.getRecords();
            TransformarDados transforma = new TransformarDados(records);
            transforma.transformarTodos();
            CarregaDados carrega = new CarregaDados();
            carrega.salvarData(transforma.getData());

            for (int k = 1; k <= 6; k++) {
                String path = "/home/flavio/ADS/5º  Período/T.C.C/dados/201501/"
                        + "201501";
                String dia = (k < 10) ? "0" + k : "" + k;
                path += dia + "_Despesas_Empenho.csv";

                //Extraindo dados
                extrairDados = new ExtrairDados(path);
                records = extrairDados.getRecords();

                //Transformando dados
                transforma = new TransformarDados(records);
                transforma.transformarTodos();

                //Carregando dados
                carrega.salvarAcoes(transforma.getAcoes());
                //carrega.salvarData(transforma.getData());
                carrega.salvarFavorecidos(transforma.getFavorecidos());
                carrega.salvarUnidades(transforma.getUnidades());
                carrega.salvarEmpenhos(transforma.getEmpenhos());

            }
            carrega.executaInsereEmpenhos();
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
