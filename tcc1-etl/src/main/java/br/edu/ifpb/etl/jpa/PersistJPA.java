package br.edu.ifpb.etl.jpa;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Empenho;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistJPA {

    private EntityManager manager;

    public PersistJPA() {
        this.manager = Persistence.createEntityManagerFactory("TCC1-ETL")
                .createEntityManager();
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
    
    public void salvarEmpenho(Empenho empenho){
        
        persistir(empenho);
    }
    
    private void persistir(Object obj) {

        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }
    
    
}
