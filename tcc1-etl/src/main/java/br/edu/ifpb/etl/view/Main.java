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

        ExtrairDados extrai;
        TransformarDados transforma;
        CarregaDados carrega;

        try {
            carrega = new CarregaDados();
            transforma = new TransformarDados();
            extrai = new ExtrairDados("/home/flavio/ADS/5º  Período/T.C.C/dados/201501/"
                    + "20150124_Despesas_Empenho.csv");

            if (transforma.csvValido(extrai.getRecords())) {

                extrai = new ExtrairDados("/home/flavio/ADS/5º  Período/T.C.C/dados/201501/"
                        + "20150101_Despesas_Empenho.csv");
                //carrega.salvarData(transforma.getData());
                for (int k = 24; k <= 24; k++) {

                    String path = "/home/flavio/ADS/5º  Período/T.C.C/dados/201501/"
                            + "201501";
                    String dia = (k < 10) ? "0" + k : "" + k;
                    path += dia + "_Despesas_Empenho.csv";
                    System.out.println(path);
                    //Extraindo dados
                    extrai = new ExtrairDados(path);

                    //Transformando dados
                    transforma.transformarTodos(extrai.getRecords());

                    //Carregando dados
                    carrega.salvarAcoes(transforma.getAcoes());
                    carrega.salvarFavorecidos(transforma.getFavorecidos());
                    carrega.salvarUnidades(transforma.getUnidades());
                    carrega.salvarEmpenhos(transforma.getEmpenhos());
                }
                carrega.executaInsereEmpenhos();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
