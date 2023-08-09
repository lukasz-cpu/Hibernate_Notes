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
public class Note13ClassicUpdate {

  private static Logger log = LogManager.getLogger(Note12Scrolling.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();




//    int updated = em.createQuery("update review r SET r.rating=6, r.content='New Content'")
    int updated = em.createQuery("update review r SET rating=:rating" +
            " where r.product.id=:id")
        .setParameter("rating", 11)
        .setParameter("id", 1L)
        .executeUpdate();

    em.getTransaction().commit();
    em.close();

  }
}
