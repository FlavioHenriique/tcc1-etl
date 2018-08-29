package br.edu.ifpb.etl.data;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import org.apache.commons.csv.CSVRecord;

public class RetornaEntidades {

    private  CSVRecord csv;

    public RetornaEntidades(CSVRecord csv) {
        this.csv = csv;
    }

    public Acao retornaAcao() {

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

    public Data retornaData() {

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

    public UnidadeGestora retornaUnidade() {

        UnidadeGestora u = new UnidadeGestora();

        u.setCodigoOrgao(Integer.parseInt(csv.get("Código Órgão")));
        u.setNomeOrgao(csv.get("Órgão"));
        u.setCodigoOrgaoSuperior(Integer.parseInt(csv.get("Código Órgão Superior")));
        u.setNomeOrgaoSuperior(csv.get("Órgão Superior"));
        u.setCodigoUnidadeGestora(Integer.parseInt(csv.get("Código Unidade Gestora")));
        u.setNomeUnidadeGestora(csv.get("Unidade Gestora"));

        return u;
    }

    public Favorecido retornaFavorecido() {

        Favorecido favorecido = new Favorecido();

        favorecido.setCodigo(csv.get("Código Favorecido"));
        favorecido.setNome(csv.get("Favorecido"));

        return favorecido;
    }
}
