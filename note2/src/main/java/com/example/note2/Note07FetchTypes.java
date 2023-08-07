package com.example.note2;

import com.example.note2.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note07FetchTypes {

  private static Logger log = LogManager.getLogger(Note07FetchTypes.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Product product = em.createQuery(
            "select p from product p " +
                " left join fetch p.category c" +
                " where p.id=:id and c.id=:catId",
            Product.class)
        .setParameter("id", 1L)
        .setParameter("catId", 1L)
        .getSingleResult();
    //Product product = em.find(Product.class, 1L);
    log.info(product);
    log.info(product.getCategory());
    log.info(product.getReviews());



    em.getTransaction().commit();
    em.close();
  }
}

