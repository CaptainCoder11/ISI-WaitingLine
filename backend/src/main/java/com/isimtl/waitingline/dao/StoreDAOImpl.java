package com.isimtl.waitingline.dao;

import com.isimtl.waitingline.entity.Store;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
public class StoreDAOImpl implements StoreDAO {


    private EntityManager entityManager;

    @Autowired
    public StoreDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Store> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Store> theQuery = currentSession.createQuery("from Store", Store.class);
        List<Store> storeList = theQuery.getResultList();
        return storeList;
    }

    @Override
    public Store findById(UUID id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Store store = currentSession.get(Store.class, id);
        return store;
    }

    @Override
    public void save(Store store) {

    }
}
