package br.edu.ifpb.etl.jpa;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.EmpenhoTemporario;
import br.edu.ifpb.etl.model.Favorecido;
import br.edu.ifpb.etl.model.UnidadeGestora;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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
        
        empenho.setAcao(manager.merge(find.findAcao(empenho.getAcao())));
        empenho.setUnidadeGestora(manager.merge(find
                .findUnidadeGestora(empenho.getUnidadeGestora())));
        empenho.setFavorecido(manager.merge(find.findFavorecido(
                empenho.getFavorecido())));
        empenho.setData(manager.merge(find.findData(empenho.getData())));
        manager.persist(empenho);
        manager.getTransaction().commit();
    }

    private void persistir(Object obj) {

        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }

}
