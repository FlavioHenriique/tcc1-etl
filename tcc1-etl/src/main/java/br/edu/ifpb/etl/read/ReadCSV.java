package br.edu.ifpb.etl.read;

import br.edu.ifpb.etl.model.Acao;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void getAcao() throws IOException {

        Iterator<CSVRecord> iterator = parser.iterator();
        CSVRecord record = iterator.next();
        
        Acao a = new Acao();
        a.setNomeFuncao(record.get("Função"));
        a.setCodigoFuncao(Integer.parseInt(record.get("Código Função")));
        a.setNomePrograma(record.get("Programa"));
        a.setNomeSubFuncao(record.get("SubFunção"));
        
        System.out.println(a.toString());
        
    }
}
