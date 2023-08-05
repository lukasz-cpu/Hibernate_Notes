package com.example.note2;

import com.example.note2.entity.Category;
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
public class Note04MultiJoins {
  private static Logger log = LogManager.getLogger(Note04MultiJoins.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    TypedQuery<Category> query = em.createQuery(
        "select c from category c left join fetch c.products p left join fetch p.reviews where c.id=:id",
        Category.class);
    query.setParameter("id", 1L);
    List<Category> resultList = query.getResultList();
    for (Category category : resultList) {
      log.info(category);
      log.info(category.getProducts());
      for (Product product : category.getProducts()) {
        log.info(product.getReviews());
      }
    }

    em.getTransaction().commit();
    em.close();
  }
}