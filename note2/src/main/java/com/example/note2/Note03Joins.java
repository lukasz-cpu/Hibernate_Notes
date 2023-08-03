package com.example.note2;

import com.example.note2.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note03Joins {

  private static final Logger log = LogManager.getLogger(Note1JPQL.class);
  private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {

    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

//    TypedQuery<Product> query = em.createQuery(
//        "select p from product p" + " inner join fetch p.category c " + "where c.id=:id", Product.class);

    TypedQuery<Product> query = em.createQuery(
        "select p from product p" + " left join fetch p.category c ", Product.class);
    List<Product> resultList = query.getResultList();
    for (Product product : resultList) {
      log.info("--------");
      log.info(product);
      log.info(product.getCategory());
      log.info("--------");

    }

    em.getTransaction().commit();
    em.close();
  }


}