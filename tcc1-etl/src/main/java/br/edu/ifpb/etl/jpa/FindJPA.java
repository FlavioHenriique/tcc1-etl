package br.edu.ifpb.etl.jpa;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.EmpenhoTemporario;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindJPA {

    private EntityManager manager;

    public FindJPA() {
        this.manager = Persistence
                .createEntityManagerFactory("TCC1-ETL")
                .createEntityManager();
    }

    public Favorecido findFavorecido(Favorecido f) {
        return manager.find(Favorecido.class, f.getCodigo());
    }

    public Acao findAcao(Acao a) {
        return manager.find(Acao.class, a.getCodigoAcao());
    }

    public UnidadeGestora findUnidadeGestora(UnidadeGestora u) {
        return manager.find(UnidadeGestora.class, u.getCodigoUnidadeGestora());
    }

    public List<EmpenhoTemporario> findEmpenhoTemporarios() {
        Query query = manager.createQuery("select e from EmpenhoTemporario e",
                EmpenhoTemporario.class);
        return query.getResultList();
    }

    public Data findData(Data d) {

        return manager.find(Data.class, d.getCodigo());
    }

    public void limparEM() {
        //manager.flush();
        manager.clear();

    }
}
