package br.edu.ifpb.etl.view;

import br.edu.ifpb.etl.controller.DAO;
import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.read.ReadCSV;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            ReadCSV read = new ReadCSV("/home/flavio/20170101_Despesas_Empenho.csv");
            DAO dao = new DAO();

            int count = 0;
            /*for (Acao a : read.getAcoes()) {
                dao.salvarAcao(a);
                System.out.println("salvo " + ++count);
            }
            
            List<Favorecido> favorecidos = read.getFavorecidos();
            for (Favorecido f : favorecidos) {
                dao.salvarFavorecido(f);
                System.out.println("salvo "+ f.getNome());
            }*/

            dao.salvarDatas(read.getDatas());
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
