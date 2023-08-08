package com.example.note2;

import com.example.note2.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note08QueryWithIn {

  private static Logger log = LogManager.getLogger(Note07FetchTypes.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Query query = em.createQuery("select o from Order o where id in (:ids)");
    query.setParameter("ids", List.of(1L, 3L, 5L));
    List<Order> orderList = (List<Order>) query.getResultList();
    for (Order order : orderList) {
      log.info(order);
    }

    em.getTransaction().commit();
    em.close();
  }
}
