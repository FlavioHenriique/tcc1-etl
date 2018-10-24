package br.edu.ifpb.etl.data;

import br.edu.ifpb.etl.jpa.FindJPA;
import br.edu.ifpb.etl.jpa.PersistJPA;
import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import br.edu.ifpb.etl.data.ExtrairDados;
import br.edu.ifpb.etl.model.EmpenhoTemporario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarregaDados {

    private FindJPA find;
    private PersistJPA persist;

    public CarregaDados() throws IOException {
        this.find = new FindJPA();
        this.persist = new PersistJPA();

    }

    public void salvarAcoes(List<Acao> acoesCSV) throws IOException {

        List<Acao> novasAcoes = acoesCSV
                .stream()
                .filter(a -> find.findAcao(a) == null)
                .collect(Collectors.toList());

        System.out.println("acoes novas: " + novasAcoes.size());
        novasAcoes.forEach(a -> persist.salvarAcao(a));

    }

    public void salvarFavorecidos(List<Favorecido> favorecidosCSV) {

//   
        List<Favorecido> novos = new ArrayList<>();

        novos = favorecidosCSV
                .stream()
                .filter(f -> find.findFavorecido(f) == null)
                .collect(Collectors.toList());
        System.out.println("favorecidos novos: " + novos.size());
        persist.salvarFavorecido(novos);
    }

    public void salvarData(Data data) {

        if (find.findData(data) == null) {
            persist.salvarDatas(data);
        }

    }

    public void salvarUnidades(List<UnidadeGestora> unidadesCSV) {

        List<UnidadeGestora> novas = new ArrayList<>();

        novas = unidadesCSV
                .stream()
                .filter(u -> find.findUnidadeGestora(u) == null)
                .collect(Collectors.toList());

        System.out.println("unidades novas: " + novas.size());
        novas.forEach(u -> persist.salvarUnidadeGestora(u));

    }

    public void salvarEmpenhos(List<EmpenhoTemporario> empenhos) {

        System.out.println("empenhos temporários: " + empenhos.size());
        //empenhos.forEach(e -> persist.salvarEmpenhoTemporario(e));
        persist.salvarEmpenhoTemporario(empenhos);
    }

    public void executaInsereEmpenhos() {
        System.out.println("Executando o procedimento para inserir empenhos");
        persist.executeInsereEmpenhos();
    }

    public void limparEM() {
        find.limparEM();
        persist.limparEM();
    }
}
