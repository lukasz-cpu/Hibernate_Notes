package com.example.note2;


import com.example.note2.entity.ProductInCategoryCounterDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note02MappingToDTO {

  private static final Logger log = LogManager.getLogger(Note1JPQL.class);
  private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {

    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Query queryOrigin = em.createQuery(
        "select p.category.id, COUNT(p) from product p group by p.category"
    );

    List<Object[]> resultListOrigin = queryOrigin.getResultList();

    List<ProductInCategoryCounterDTO> productInCategoryCounterResult = resultListOrigin.stream()
        .map(result -> new ProductInCategoryCounterDTO(
            (Long) Optional.ofNullable(result[0]).orElse(0L),
            (Long) Optional.ofNullable(result[1]).orElse(0L))).toList();

    for (ProductInCategoryCounterDTO productInCategoryCounterDTO : productInCategoryCounterResult) {
      System.out.println(productInCategoryCounterDTO);
    }

    em.getTransaction().commit();
    em.close();
  }


}
