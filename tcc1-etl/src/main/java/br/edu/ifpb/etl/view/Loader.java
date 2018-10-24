package br.edu.ifpb.etl.view;

import br.edu.ifpb.etl.data.CarregaDados;
import br.edu.ifpb.etl.data.ExtrairDados;
import br.edu.ifpb.etl.data.RetornaEntidades;
import br.edu.ifpb.etl.data.TransformarDados;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Loader {

    private static final String path = "/home/flavio/ADS/5º Período/T.C.C 1/dados/";
    private static ExtrairDados extrai;
    private static TransformarDados transforma;
    private static CarregaDados carrega;
    private static String ano = "2018";

    public static void main(String[] args) {

        //deletaCSVsVazios();
        try {
            for (int k = 1; k <= 12; k++) {
                String pasta = (k > 9) ? path + ano + k : path + ano + "0" + k;
                carrega = new CarregaDados();

                List<File> arquivos = arquivos(pasta);
                // Salvando data
                extrai = new ExtrairDados(arquivos.get(0).getPath());

                RetornaEntidades retorna = new RetornaEntidades(
                        extrai.getRecords().get(0)
                );

                carrega.salvarData(retorna.retornaData());
                System.out.println("data salva: " + pasta);

                long tempo = System.currentTimeMillis();

                // Processo de ETL em cada arquivo
                arquivos.forEach(a -> processarArquivo(a));

                // Executando procedimento armazenado 
                carrega.executaInsereEmpenhos();

                long tempofinal = System.currentTimeMillis() - tempo;
                long minutos = minutos = (tempofinal / 60000) % 60;
                System.out.println("tempo de execução: " + minutos + " minutos");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*private static void deletaCSVsVazios() {

        List<File> pastas = arquivos();
        for (File pasta : pastas) {
            File[] arquivos = new File(pasta.getPath()).listFiles();

            for (File arquivo : arquivos) {
                System.out.println(arquivo.toString());
                try {
                    ExtrairDados extrairDados = new ExtrairDados(arquivo.getPath());
                    if (!extrairDados.verificarCSV()) {

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }*/
    private static List<File> pastas() {

        File[] pastas = new File(path).listFiles();
        return Arrays.asList(pastas)
                .stream()
                .filter(p -> p.listFiles().length > 0)
                .collect(Collectors.toList());
    }

    private static List<File> arquivos(String pasta) {
        System.out.println(pasta);
        return Arrays.asList(new File(pasta).listFiles());

    }

    // MÉTODO PARA O PROCESSO DO ETL EM CADA ARQUIVO
    private static void processarArquivo(File a) {

        try {
            extrai = new ExtrairDados(a.getPath());

            transforma = new TransformarDados();
            transforma.transformarTodos(extrai.getRecords());

            carrega.salvarAcoes(transforma.getAcoes());
            carrega.salvarFavorecidos(transforma.getFavorecidos());
            carrega.salvarUnidades(transforma.getUnidades());

            carrega.salvarEmpenhos(transforma.getEmpenhos());

            System.out.println("arquivo salvo: " + a.getName());

            carrega.limparEM();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
