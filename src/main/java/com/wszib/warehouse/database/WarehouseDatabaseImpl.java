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
        System.out.println("WarehouseDatabaseImpl addNewItem: "+item);
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(item);
        tx.commit();
        session.close();
    }

    @Override
    public void removeItem(int id) {
        System.out.println("WarehouseDatabaseImpl removeItem: "+id);
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        WarehouseItem item ;

        item = (WarehouseItem)session.load(WarehouseItem.class,id);
        session.delete(item);

        session.flush() ;
        tx.commit();
        session.close();
    }

    @Override
    public String getItemList() {
        System.out.println("WarehouseDatabaseImpl getItemList");
        Session session = this.sessionFactory.openSession();
        List<WarehouseItem> itemList = session.createQuery("from WarehouseItem").list();
        session.close();
        return itemList.toString();
    }
}
