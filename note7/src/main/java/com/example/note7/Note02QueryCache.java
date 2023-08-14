package com.example.note7;

import com.example.note7.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.HibernateHints;
import org.hibernate.jpa.QueryHints;

public class Note02QueryCache {

  private static Logger log = LogManager.getLogger(Note02QueryCache.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Customer> customer = em.createQuery("select c from customer c", Customer.class)
        .setHint(HibernateHints.HINT_CACHEABLE, true)
        .getResultList();
    log.info(customer);
    em.getTransaction().commit();
    em.close();

    em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    customer = em.createQuery("select c from customer c", Customer.class)
        .setHint(HibernateHints.HINT_CACHEABLE, true)
        .getResultList();
    log.info(customer);
    em.getTransaction().commit();
    em.close();
  }

}
