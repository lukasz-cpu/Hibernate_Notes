package com.example.note3;

import com.example.note3.entity.batch.BatchReview;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note11Update {

  private static Logger log = LogManager.getLogger(com.example.note3.Note10Insert.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Long count = em.createQuery("select count(r) from BatchReview r", Long.class).getSingleResult();
    int batchSize = 10;
    for (int i = 0; i < count; i = i + batchSize) {
      List<BatchReview> review = em.createQuery(
              "select r from BatchReview r",
              BatchReview.class
          )
          .setFirstResult(i)
          .setMaxResults(batchSize)
          .getResultList();
      for (BatchReview batchReview : review) {
        batchReview.setContent("Nowa treść !!!!!!!!!");
        batchReview.setRating(15);
      }
      em.flush();
      em.clear();
    }

    em.getTransaction().commit();
    em.close();

  }
}