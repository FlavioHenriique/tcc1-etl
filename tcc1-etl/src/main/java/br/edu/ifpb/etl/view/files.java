package br.edu.ifpb.etl.view;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class files {

    public static void main(String[] args) {
        String path = "/home/flavio/ADS/5º  Período/T.C.C/dados/";

        File pasta = new File("/home/flavio/ADS/5º  Período/T.C.C/dados/");

        File[] pastas = new File(path).listFiles(File::isDirectory);

        for (File f : pastas) {
            System.out.println(f.getPath());
        }

        File[] arquivos = pasta.listFiles();

        for (int k = 0; k < arquivos.length; k++) {
            if (arquivos[k].isFile()) {
                System.out.println(arquivos[k].getName());
            }
        }

    }
}
