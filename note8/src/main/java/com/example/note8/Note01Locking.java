package com.example.note8;

import com.example.note8.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note01Locking {

  private static Logger log = LogManager.getLogger(Note01Locking.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    EntityManager em2 = entityManagerFactory.createEntityManager();
    em2.getTransaction().begin();

    Order order = em.find(Order.class, 3L);
    Order order2 = em2.find(Order.class, 3L);

    order.setTotal(new BigDecimal("22.35"));
    log.info(order);
    em.getTransaction().commit();
    em.close();

    order2.setTotal(new BigDecimal("22.44"));
    log.info(order2);
    em2.getTransaction().commit();
    em2.close();
  }

}

