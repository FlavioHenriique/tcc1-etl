package br.edu.ifpb.etl.jpa;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import br.edu.ifpb.etl.read.ReadCSV;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerificacaoDados {

    private FindJPA find;
    private PersistJPA persist;
    private ReadCSV readCSV;

    public VerificacaoDados(String path) throws IOException {
        this.find = new FindJPA();
        this.persist = new PersistJPA();
        this.readCSV = new ReadCSV(path);
    }

    public void salvarAcoes() throws IOException {

        List<Acao> acoesCSV = readCSV.getAcoes();
        List<Acao> acoesBanco = find.findAcoes();
        List<Acao> novasAcoes = new ArrayList<>();

        int cont = 0;
        if (!acoesBanco.isEmpty()) {
            for (int k = 0; k < acoesCSV.size(); k++) {
                boolean igual = false;
                for (int i = 0; i < acoesBanco.size(); i++) {
                    if (acoesCSV.get(k).getCodigoAcao().equals(acoesBanco.get(i)
                            .getCodigoAcao())) {
                        igual = true;
                    }
                }
                if (igual == false) {
                    novasAcoes.add(acoesCSV.get(k));
                }
            }

            System.out.println("quantidade acoes novas : " + novasAcoes.size());
            novasAcoes.forEach(a -> persist.salvarAcao(a));

        } else {
            acoesCSV.forEach(a -> persist.salvarAcao(a));
        }
    }

    public void salvarFavorecidos() {

        List<Favorecido> favorecidosBanco = find.findFavorecidos();
        List<Favorecido> favorecidosCSV = readCSV.getFavorecidos();
        List<Favorecido> novos = new ArrayList<>();

        if (!favorecidosBanco.isEmpty()) {
            for (int k = 0; k < favorecidosCSV.size(); k++) {
                boolean existe = false;
                for (int i = 0; i < favorecidosBanco.size(); i++) {
                    if (favorecidosCSV.get(k).getCodigo().equals(favorecidosBanco
                            .get(i).getCodigo())) {
                        existe = true;
                    }
                }
                if (existe == false) {
                    novos.add(favorecidosCSV.get(k));
                }
            }
            System.out.println("favorecidos novos: " + novos.size());
            novos.forEach(f -> persist.salvarFavorecido(f));

        } else {
            favorecidosCSV.forEach(f -> persist.salvarFavorecido(f));
        }
    }

    public void salvarData() {

        Data data = readCSV.getData();
        persist.salvarDatas(data);
    }

    public void salvarUnidades() {

        List<UnidadeGestora> unidadesBanco = find.findUnidadesGestoras();
        List<UnidadeGestora> unidadesCSV = readCSV.getUnidadesGestoras();
        List<UnidadeGestora> novas = new ArrayList<>();

        if (!unidadesBanco.isEmpty()) {
            for (int k = 0; k < unidadesCSV.size(); k++) {
                boolean existe = false;
                for (int i = 0; i < unidadesBanco.size(); i++) {
                    if (unidadesCSV.get(k).getCodigoUnidadeGestora() == unidadesBanco
                            .get(i).getCodigoUnidadeGestora()) {
                        existe = true;
                    }
                }
                if (existe == false) {
                    novas.add(unidadesCSV.get(k));
                }
            }
            System.out.println("unidades novas: "+ novas.size());
            novas.forEach(u -> persist.salvarUnidadeGestora(u));

        } else {
            unidadesCSV.forEach(u -> persist.salvarUnidadeGestora(u));
        }

    }
}
