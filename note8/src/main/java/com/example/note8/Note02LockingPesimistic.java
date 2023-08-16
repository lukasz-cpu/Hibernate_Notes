package com.example.note8;

import com.example.note8.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note02LockingPesimistic {

  private static Logger log = LogManager.getLogger(Note02LockingPesimistic.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
//        Order order = em.find(Order.class, 3L, LockModeType.PESSIMISTIC_READ);
//        logger.info(order);

    Order singleResult = em.createQuery("select o from Order o where o.id=:id", Order.class)
        .setParameter("id", 3L)
        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
        .getSingleResult();
    log.info(singleResult);

    em.getTransaction().commit();
    em.clear();
  }

}

