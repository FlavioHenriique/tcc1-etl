package br.edu.ifpb.etl.jpa;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.EmpenhoTemporario;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

public class PersistJPA {

    private EntityManager manager;
    private FindJPA find;

    public PersistJPA() {
        this.manager = Persistence.createEntityManagerFactory("TCC1-ETL")
                .createEntityManager();
        this.find = new FindJPA();
    }

    public void salvarAcao(Acao acao) {

        persistir(acao);
    }

    public void salvarFavorecido(Favorecido favorecido) {
        persistir(favorecido);
    }

    public void salvarDatas(Data data) {
        persistir(data);
    }

    public void salvarUnidadeGestora(UnidadeGestora unidadeGestora) {

        persistir(unidadeGestora);
    }

    public void salvarEmpenhoTemporario(EmpenhoTemporario empenho) {

        manager.getTransaction().begin();

        empenho.setAcao(manager.merge(empenho.getAcao()));
        empenho.setUnidadeGestora(manager.merge(empenho.getUnidadeGestora()));
        empenho.setFavorecido(manager.merge(empenho.getFavorecido()));
        empenho.setData(manager.merge(empenho.getData()));
        manager.persist(empenho);
        manager.getTransaction().commit();
    }

    private void persistir(Object obj) {

        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }
    
    public void executeInsereEmpenhos(){
        StoredProcedureQuery procedure = manager
                .createStoredProcedureQuery("insereempenhos");
        procedure.execute();
        
    }

}
