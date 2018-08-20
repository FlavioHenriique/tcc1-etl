package br.edu.ifpb.etl.jpa;

import br.edu.ifpb.etl.model.Acao;
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

    public List<Favorecido> findFavorecidos() {
        Query query = manager.createQuery("select f from Favorecido f", Favorecido.class);
        return query.getResultList();
    }

    public List<Acao> findAcoes() {
        Query query = manager.createQuery("select a from Acao a", Acao.class);
        return query.getResultList();
    }

    public List<UnidadeGestora> findUnidadesGestoras() {
        Query query = manager.createQuery("select u from UnidadeGestora u", UnidadeGestora.class);
        return query.getResultList();
    }
}
