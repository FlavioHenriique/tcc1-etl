package br.edu.ifpb.etl.jpa;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.EmpenhoTemporario;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import java.util.List;
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

    public void salvarFavorecido(List<Favorecido> favorecidos) {
        int batch = 25;
        manager.getTransaction().begin();
        for (int k = 0; k < favorecidos.size(); k++) {
            if(k > 0 && k % batch == 0) {
                manager.getTransaction().commit();
                manager.getTransaction().begin();
                manager.clear();
            }
            manager.persist(favorecidos.get(k));
        }
        manager.getTransaction().commit();
    }

    public void salvarDatas(Data data) {
        persistir(data);
    }

    public void salvarUnidadeGestora(UnidadeGestora unidadeGestora) {

        persistir(unidadeGestora);
    }

    public void salvarEmpenhoTemporario(List<EmpenhoTemporario> empenhos) {

        int batch = 25;
        manager.getTransaction().begin();
        for (int k = 0; k < empenhos.size(); k++) {
            if(k > 0 && k % batch == 0) {
                manager.getTransaction().commit();
                manager.getTransaction().begin();
                manager.clear();
            }
            manager.persist(relacionamentos(empenhos.get(k)));
        }
        manager.getTransaction().commit();
    }
    
    public EmpenhoTemporario relacionamentos(EmpenhoTemporario empenho){
        
        empenho.setAcao(manager.merge(empenho.getAcao()));
        empenho.setUnidadeGestora(manager.merge(empenho.getUnidadeGestora()));
        empenho.setFavorecido(manager.merge(empenho.getFavorecido()));
        empenho.setData(manager.merge(empenho.getData()));
        return empenho;
    }

    private void persistir(Object obj) {
        
        int batch = 25;
        
        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }

    public void executeInsereEmpenhos() {
        StoredProcedureQuery procedure = manager
                .createStoredProcedureQuery("insereempenhos");
        procedure.execute();

    }

    public void limparEM() {
        //manager.flush();
        manager.clear();

    }
}
