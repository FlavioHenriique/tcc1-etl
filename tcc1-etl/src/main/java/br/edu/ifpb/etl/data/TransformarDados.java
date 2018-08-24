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

    private List<CSVRecord> records;
    private List<Acao> acoes;
    private List<Favorecido> favorecidos;
    private Data data;
    private List<UnidadeGestora> unidades;
    private List<EmpenhoTemporario> empenhos;

    public TransformarDados(List<CSVRecord> records) {
        this.records = records;
        this.acoes = new ArrayList<>();
        this.favorecidos = new ArrayList<>();
        this.unidades = new ArrayList<>();
        this.empenhos = new ArrayList<>();
    }

    public void transformarTodos() {

        for (int k = 0; k < records.size(); k++) {

            CSVRecord record = records.get(k);

            // Recuperando as ações 
            Acao acao = retornaAcao(record);
            verificaAcao(acao);

            // Recuperando os favorecidos
            Favorecido favorecido = retornaFavorecido(record);
            verificaFavorecido(favorecido);

            //Recuperando unidades gestoras
            UnidadeGestora unidade = retornaUnidade(record);
            verificaUnidadeGestora(unidade);

            //Recupera data para este empenho
            Data dataEmpenho = retornaData(record);

            //Recupera Empenho
            EmpenhoTemporario empenho = new EmpenhoTemporario(acao, favorecido,
                    unidade, dataEmpenho);
            String valor = record.get("Valor do Empenho Convertido pra R$")
                    .replaceAll(",", ".");
            empenho.setValor(new BigDecimal(valor));
            //    verificaEmpenhos(empenho);
            empenhos.add(empenho);
        }
        //Recupera Data
        this.data = retornaData(records.get(0));
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

    private Acao retornaAcao(CSVRecord csv) {

        Acao a = new Acao();

        a.setNomeFuncao(csv.get("Função"));
        a.setCodigoFuncao(Integer.parseInt(csv.get("Código Função")));
        a.setNomePrograma(csv.get("Programa"));
        a.setNomeSubFuncao(csv.get("SubFunção"));
        a.setCodigoSubFuncao(csv.get("Código Função")
                .concat(csv.get("Código SubFunção")));
        a.setCodigoAcao(csv.get("Código Programa")
                .concat(csv.get("Código Ação")));
        a.setCodigoPrograma(csv.get("Código Programa"));
        a.setNomeAcao(csv.get("Ação"));

        return a;
    }

    private Data retornaData(CSVRecord csv) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(csv.get("Data Emissão"), formatter);

        Data d = new Data();

        d.setAno(data.getYear());
        d.setNumero_mes(data.getMonthValue());

        String mes = (d.getNumero_mes() <= 9) ? "0" + d.getNumero_mes()
                : "" + d.getNumero_mes();
        d.setCodigo(Integer.parseInt(d.getAno() + mes));

        d.setNome_mes(data.getMonth().getDisplayName(TextStyle.FULL,
                new Locale("pt")));

        int semestre = (data.getMonthValue() <= 6)
                ? Integer.parseInt("01" + data.getYear())
                : Integer.parseInt("02" + data.getYear());

        d.setSemestre(semestre);

        return d;
    }

    private UnidadeGestora retornaUnidade(CSVRecord csv) {

        UnidadeGestora u = new UnidadeGestora();

        u.setCodigoOrgao(Integer.parseInt(csv.get("Código Órgão")));
        u.setNomeOrgao(csv.get("Órgão"));
        u.setCodigoOrgaoSuperior(Integer.parseInt(csv.get("Código Órgão Superior")));
        u.setNomeOrgaoSuperior(csv.get("Órgão Superior"));
        u.setCodigoUnidadeGestora(Integer.parseInt(csv.get("Código Unidade Gestora")));
        u.setNomeUnidadeGestora(csv.get("Unidade Gestora"));

        return u;
    }

    private Favorecido retornaFavorecido(CSVRecord csv) {

        Favorecido favorecido = new Favorecido();

        favorecido.setCodigo(csv.get("Código Favorecido"));
        favorecido.setNome(csv.get("Favorecido"));

        return favorecido;
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
