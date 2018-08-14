package br.edu.ifpb.etl.read;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Favorecido;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ReadCSV {

    private String path;
    private Reader reader;
    private CSVParser parser;

    public ReadCSV(String path) throws IOException {

        this.path = path;
        this.reader = Files.newBufferedReader(Paths.get(path),
                Charset.forName("ISO-8859-1"));
        this.parser = new CSVParser(this.reader, CSVFormat.newFormat(';')
                .withQuote('"')
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces());

    }

    public List<Acao> getAcoes() throws IOException {

        Iterator<CSVRecord> iterator = parser.iterator();

        List<Acao> lista = new ArrayList<>();
        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();

            Acao a = new Acao();
            a.setNomeFuncao(record.get("Função"));
            a.setCodigoFuncao(Integer.parseInt(record.get("Código Função")));
            a.setNomePrograma(record.get("Programa"));
            a.setNomeSubFuncao(record.get("SubFunção"));
            a.setCodigoSubFuncao(record.get("Código Função")
                    .concat(record.get("Código SubFunção")));
            a.setCodigoAcao(record.get("Código Programa")
                    .concat(record.get("Código Ação")));
            a.setCodigoPrograma(record.get("Código Programa"));
            a.setNomeAcao(record.get("Ação"));

            lista.add(a);
        }

        return lista;

    }

    public List<Favorecido> getFavorecidos() {

        Iterator<CSVRecord> iterator = parser.iterator();
        List<Favorecido> lista = new ArrayList<>();

        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();
            Favorecido favorecido = new Favorecido();
            favorecido.setCodigo(record.get("Código Favorecido"));
            favorecido.setNome(record.get("Favorecido"));

            if (!lista.contains(favorecido)) {
                lista.add(favorecido);
            }
        }

        return lista;
    }

    public Data getDatas() {

        Iterator<CSVRecord> iterator = parser.iterator();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        CSVRecord record = iterator.next();

        LocalDate data = LocalDate.parse(record.get("Data Emissão"), formatter);
        Data d = new Data();
        d.setCodigo(Integer.parseInt(Date.valueOf(data).toString()
                .replaceAll("-", "")));
        d.setAno(data.getYear());
        d.setData(Date.valueOf(data));
        d.setNumero_mes(data.getMonthValue());
        d.setNome_mes(data.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")));

        int semestre = 0;
        if (data.getMonthValue() <= 6) {
            semestre = Integer.parseInt("01" + data.getYear());
        } else {
            semestre = Integer.parseInt("02" + data.getYear());
        }
        d.setSemestre(semestre);

        return d;
        
    }
}
