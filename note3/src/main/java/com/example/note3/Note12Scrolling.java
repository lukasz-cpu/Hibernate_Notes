package com.example.note3;

import com.example.note3.entity.batch.BatchReview;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.QueryHint;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.HibernateHints;
import org.hibernate.jpa.QueryHints;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note12Scrolling {

  private static Logger log = LogManager.getLogger(Note12Scrolling.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();



    em.createQuery(
            "select r from BatchReview r",
            BatchReview.class
        )
        .setHint(HibernateHints.HINT_FETCH_SIZE, Integer.MIN_VALUE)
        .getResultStream()
        .forEach(batchReview -> {
          batchReview.setContent("Contnet");
          batchReview.setRating(5);
        });

    em.getTransaction().commit();
    em.close();

  }
}