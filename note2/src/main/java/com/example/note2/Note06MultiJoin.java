package com.example.note2;

import com.example.note2.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note06MultiJoin {

  private static Logger log = LogManager.getLogger(Note05CartesianProduct.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Product> resultList = em.createQuery(
            "select distinct p from product p" +
                " left join fetch p.attributes",
            Product.class
        )
        .getResultList();

    resultList = em.createQuery(
            "select distinct p from product p" +
                " left join fetch p.reviews",
            Product.class
        )
        .getResultList();

    log.info("Size: " + resultList.size());
    for (Product product : resultList) {
      log.info(product);
      log.info(product.getAttributes());
      log.info(product.getReviews());
    }

    em.getTransaction().commit();
    em.close();
  }
}
