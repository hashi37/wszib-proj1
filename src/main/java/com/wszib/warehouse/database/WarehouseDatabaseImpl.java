package com.wszib.warehouse.database;

import com.wszib.warehouse.model.WarehouseItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("warehouseDatabase")
public class WarehouseDatabaseImpl implements WarehouseDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewItem(WarehouseItem item) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.persist(item);
        tx.commit();
        System.out.println("WarehouseDatabaseImpl addNewItem: "+item);
    }

    @Override
    public void removeItem(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        WarehouseItem item ;

        item = (WarehouseItem)session.load(WarehouseItem.class,id);
        session.delete(item);

        session.flush() ;
        tx.commit();
        System.out.println("WarehouseDatabaseImpl removeItem: "+id);
    }

    @Override
    public String getItemList() {
        System.out.println("WarehouseDatabaseImpl getItemList");
        return getAllItems().toString();
    }

    @Override
    public List getAllItems() {
        System.out.println("WarehouseDatabaseImpl getAllItems");
        List<WarehouseItem> itemList;
        Session session = this.sessionFactory.getCurrentSession();
        itemList = session.createQuery("from WarehouseItem").list();
        return itemList;
    }

    @Override
    public WarehouseItem getById(int id) {
        System.out.println("WarehouseDatabaseImpl getAllItems");
        WarehouseItem item;
        Session session = this.sessionFactory.getCurrentSession();
        //item = (WarehouseItem)session.createQuery("from WarehouseItem where id = :id").setParameter("id",id).getSingleResult();
        item = (WarehouseItem)session.load(WarehouseItem.class,id);
        return item;
    }

    @Override
    public void saveItem(WarehouseItem item) {
        System.out.println("WarehouseDatabaseImpl saveItem: "+item);
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(item);
        session.flush() ;
        tx.commit();
    }
}
