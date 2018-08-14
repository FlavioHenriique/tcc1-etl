package br.edu.ifpb.etl.controller;

import br.edu.ifpb.etl.model.Acao;
import br.edu.ifpb.etl.model.Data;
import br.edu.ifpb.etl.model.Favorecido;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAO {

    private EntityManager manager;

    public DAO() {
        this.manager = Persistence.createEntityManagerFactory("TCC1-ETL")
                .createEntityManager();
    }

    public void salvarAcao(Acao acao) {

        manager.getTransaction().begin();
        manager.persist(acao);
        manager.getTransaction().commit();
    }

    public void salvarFavorecido(Favorecido favorecido) {
        manager.getTransaction().begin();
        manager.persist(favorecido);
        manager.getTransaction().commit();
    }

    public void salvarDatas(Data data) {
        manager.getTransaction().begin();
        manager.persist(data);
        manager.getTransaction().commit();
    }
}
