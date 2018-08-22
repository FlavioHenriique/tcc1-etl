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

    public List<Favorecido> findFavorecidos() {
        Query query = manager.createQuery("select f from Favorecido f", Favorecido.class);
        return query.getResultList();
    }

    public Favorecido findFavorecido(Favorecido f) {
        return manager.find(Favorecido.class, f.getCodigo());
    }

    public List<Acao> findAcoes() {
        Query query = manager.createQuery("select a from Acao a", Acao.class);
        return query.getResultList();
    }

    public Acao findAcao(Acao a) {
        return manager.find(Acao.class, a.getCodigoAcao());
    }

    public List<UnidadeGestora> findUnidadesGestoras() {
        Query query = manager.createQuery("select u from UnidadeGestora u", UnidadeGestora.class);
        return query.getResultList();
    }

    public UnidadeGestora findUnidadeGestora(UnidadeGestora u) {
        return manager.find(UnidadeGestora.class, u.getCodigoUnidadeGestora());
    }

    public List<EmpenhoTemporario> findEmpenhoTemporarios() {
        Query query = manager.createQuery("select e from EmpenhoTemporario e",
                EmpenhoTemporario.class);
        return query.getResultList();
    }
    
    public Data findData(Data d){
        
        return manager.find(Data.class, d.getCodigo());
    }
}
