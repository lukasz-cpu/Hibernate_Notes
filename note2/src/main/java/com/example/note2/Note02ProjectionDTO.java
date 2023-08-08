package com.example.note2;

import com.example.note2.entity.ProductInCategoryCounterDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note02ProjectionDTO {

  private static final Logger log = LogManager.getLogger(Note1JPQL.class);
  private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {

    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Query query = em.createQuery(
        "select new com.example.note2.entity.ProductInCategoryCounterDTO(p.category.id, COUNT(p)) " +
            "from product p group by p.category"
    );

    List<ProductInCategoryCounterDTO> resultListOrigin = query.getResultList();



    for (ProductInCategoryCounterDTO productInCategoryCounterDTO : resultListOrigin) {
      System.out.println(productInCategoryCounterDTO);
    }

    em.getTransaction().commit();
    em.close();
  }


}
