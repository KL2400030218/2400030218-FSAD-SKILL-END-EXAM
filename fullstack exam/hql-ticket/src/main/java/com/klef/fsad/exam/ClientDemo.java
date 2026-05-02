package com.klef.fsad.exam;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.Date;

public class ClientDemo {
    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Ticket t = new Ticket();
        t.setName("Issue A");
        t.setDate(new Date());
        t.setStatus("Open");
        session.save(t);
        String hql = "update Ticket set name=?1, status=?2 where id=?3";
        Query query = session.createQuery(hql);
        query.setParameter(1, "Updated Issue");
        query.setParameter(2, "Closed");
        query.setParameter(3, 1);

        query.executeUpdate();

        tx.commit();
        session.close();
        sf.close();

        System.out.println("Insert and Update using HQL completed.");
    }
}