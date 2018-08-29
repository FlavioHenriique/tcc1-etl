package br.edu.ifpb.etl.data;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.EmpenhoTemporario;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.csv.CSVRecord;

public class TransformarDados {

    private List<Acao> acoes;
    private List<Favorecido> favorecidos;
    private Data data;
    private List<UnidadeGestora> unidades;
    private List<EmpenhoTemporario> empenhos;

    public TransformarDados() {

        this.acoes = new ArrayList<>();
        this.favorecidos = new ArrayList<>();
        this.unidades = new ArrayList<>();
        this.empenhos = new ArrayList<>();
    }

    public boolean csvValido(List<CSVRecord> records) {
        return (records.size() != 0);
    }

    public void transformarTodos(List<CSVRecord> records) {

        if (records.size() != 0) {
            for (int k = 0; k < records.size(); k++) {
                System.out.println(k);
                CSVRecord record = records.get(k);

                RetornaEntidades entidades = new RetornaEntidades(record);

                // Recuperando as ações 
                Acao acao = entidades.retornaAcao();
                verificaAcao(acao);

                // Recuperando os favorecidos
                Favorecido favorecido = entidades.retornaFavorecido();
                verificaFavorecido(favorecido);

                //Recuperando unidades gestoras
                UnidadeGestora unidade = entidades.retornaUnidade();
                verificaUnidadeGestora(unidade);

                //Recupera data para este empenho
                Data dataEmpenho = entidades.retornaData();

                //Recupera Empenho
                EmpenhoTemporario empenho = new EmpenhoTemporario(acao, favorecido,
                        unidade, dataEmpenho);
                String valor = record.get("Valor do Empenho Convertido pra R$")
                        .replaceAll(",", ".");
                empenho.setValor(new BigDecimal(valor));
                System.out.println(record.get("Data Emissão"));
                //    verificaEmpenhos(empenho);
                empenhos.add(empenho);
            }
            //Recupera Data
            RetornaEntidades entidades = new RetornaEntidades(records.get(0));
            this.data = entidades.retornaData();
        }
    }

    private void verificaAcao(Acao acao) {
        boolean existe = false;
        for (Acao ac : acoes) {
            if (ac.getCodigoAcao().equals(acao.getCodigoAcao())) {
                existe = true;
            }
        }
        if (existe == false) {
            acoes.add(acao);
        }
    }

    private void verificaFavorecido(Favorecido favorecido) {
        boolean existe = false;
        for (Favorecido f : favorecidos) {
            if (f.getCodigo().equals(favorecido.getCodigo())) {
                existe = true;
            }
        }
        if (existe == false) {
            favorecidos.add(favorecido);
        }
    }

    private void verificaUnidadeGestora(UnidadeGestora u) {
        boolean existe = false;
        for (UnidadeGestora unidade : unidades) {
            if (unidade.getCodigoUnidadeGestora()
                    == u.getCodigoUnidadeGestora()) {
                existe = true;
            }
        }
        if (existe == false) {
            unidades.add(u);
        }
    }

    private void verificaEmpenhos(EmpenhoTemporario empenho) {

        boolean existe = false;

        for (EmpenhoTemporario e : empenhos) {
            if ((e.getAcao().getCodigoAcao().equals(empenho.getAcao()
                    .getCodigoAcao())) && (e.getFavorecido().getCodigo()
                            .equals(empenho.getFavorecido().getCodigo()))
                    && (e.getUnidadeGestora().getCodigoUnidadeGestora()
                    == empenho.getUnidadeGestora().getCodigoUnidadeGestora())) {

                existe = true;
            }
        }
        if (existe == false) {
            empenhos.add(empenho);

        }
    }

    public List<Acao> getAcoes() {
        return acoes;
    }

    public List<Favorecido> getFavorecidos() {
        return favorecidos;
    }

    public Data getData() {
        return data;
    }

    public List<UnidadeGestora> getUnidades() {
        return unidades;
    }

    public List<EmpenhoTemporario> getEmpenhos() {
        return empenhos;
    }

}
