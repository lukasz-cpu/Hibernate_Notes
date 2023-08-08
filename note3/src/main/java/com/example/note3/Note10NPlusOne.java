package com.example.note3;

import com.example.note3.entity.batch.BatchReview;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note10NPlusOne {

  private static Logger log = LogManager.getLogger(Note10NPlusOne.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Long lastId = em.createQuery("select max(r.id) from BatchReview r", Long.class)
        .getSingleResult();

    for (int i = 1; i <= 30; i++) {
      em.persist(new BatchReview(lastId + i, "Treść", 5, 1L));
    }


    log.info("JESTEM TUTAJ");

    em.getTransaction().commit();
    em.close();

  }
}
