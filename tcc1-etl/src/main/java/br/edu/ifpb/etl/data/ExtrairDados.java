package br.edu.ifpb.etl.data;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ExtrairDados {

    private String path;
    private Reader reader;
    private CSVParser parser;
    private Iterator<CSVRecord> iterator;

    public ExtrairDados(String path) throws IOException {

        this.path = path;
        this.reader = Files.newBufferedReader(Paths.get(path),
                Charset.forName("ISO-8859-1"));
        this.parser = new CSVParser(
                this.reader,
                CSVFormat.newFormat(';')
                        .withQuote('"')
                        .withFirstRecordAsHeader()
                        .withIgnoreSurroundingSpaces()
        );
        this.iterator = parser.iterator();

    }

    public List<CSVRecord> getRecords() {

        List<CSVRecord> csvs = new ArrayList<>();

        while (iterator.hasNext()) {
            csvs.add(iterator.next());
        }
        
        return csvs;
    }

    public boolean verificarCSV() {

        return iterator.hasNext();
    }
}
