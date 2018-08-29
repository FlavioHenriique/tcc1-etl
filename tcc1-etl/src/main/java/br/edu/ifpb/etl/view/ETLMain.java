package br.edu.ifpb.etl.view;

import br.edu.ifpb.etl.data.CarregaDados;
import br.edu.ifpb.etl.data.ExtrairDados;
import br.edu.ifpb.etl.data.RetornaEntidades;
import br.edu.ifpb.etl.data.TransformarDados;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVRecord;

public class ETLMain {

    private static final String path = "/home/flavio/ADS/5º  Período/T.C.C/dados/";

    public static void main(String[] args) {

        ExtrairDados extrai;
        TransformarDados transforma;
        CarregaDados carrega;

        //deletaCSVsVazios();
        for (File pasta : pastas()) {

            List<String> arquivos = new ArrayList<>();

            Arrays.asList(new File(pasta.getPath()).listFiles())
                    .forEach(a -> arquivos.add(a.getPath()));

            try {
                // Inserindo as datas
                carrega = new CarregaDados();
                extrai = new ExtrairDados(arquivos.get(0));
                RetornaEntidades entidades = new RetornaEntidades(
                        extrai.getRecords().get(0));

                System.out.println(arquivos.get(0));
                carrega.salvarData(entidades.retornaData());

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    private static void deletaCSVsVazios() {

        List<File> pastas = pastas();
        for (File pasta : pastas) {
            File[] arquivos = new File(pasta.getPath()).listFiles();

            for (File arquivo : arquivos) {
                try {
                    ExtrairDados extrairDados = new ExtrairDados(arquivo.getPath());
                    if (!extrairDados.verificarCSV()) {

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static List<File> pastas() {

        File[] pastas = new File(path).listFiles();

        return Arrays.asList(pastas)
                .stream()
                .filter(f -> f.listFiles().length > 0)
                .collect(Collectors.toList());

    }
}
