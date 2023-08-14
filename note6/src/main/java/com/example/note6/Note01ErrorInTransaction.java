package com.example.note6;

import com.example.note5.entity.Customer;
import com.example.note5.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note01ErrorInTransaction {

  private static Logger log = LogManager.getLogger(Note01ErrorInTransaction.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    try {
      Customer customer = em.createQuery("select c from customer c where c.id = :id",
              Customer.class)
          .setParameter("id", 99L)
          .getSingleResult();
      log.info(customer);
      Product product = em.createQuery("select p from product p where p.id = :id", Product.class)
          .setParameter("id", 1L)
          .getSingleResult();
      log.info(product);
      em.getTransaction().commit();
    } catch (Exception e) {
      log.info(em.getTransaction().isActive());
      log.info(em.getTransaction().getRollbackOnly());
      if (em.getTransaction().isActive() || em.getTransaction().getRollbackOnly()) {
        em.getTransaction().rollback();
        log.error("Rollback transakcji");
      }
      log.error(e, e);
    }

    em.close();
  }

}

