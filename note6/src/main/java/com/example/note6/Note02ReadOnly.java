package com.example.note6;

import com.example.note5.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note02ReadOnly {

  private static Logger log = LogManager.getLogger(Note02ReadOnly.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Customer customer = em.createQuery("select c from customer c where c.id = :id", Customer.class)
        .setParameter("id", 1L)
//        .setHint(QueryHints.HINT_READONLY, true)
        .getSingleResult();

    customer.setUpdated(LocalDateTime.now());
    em.merge(customer);
    log.info(customer);

    em.getTransaction().commit();
    em.close();

  }

}
