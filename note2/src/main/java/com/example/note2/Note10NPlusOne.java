package com.example.note2;

import com.example.note2.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note10NPlusOne {

  private static Logger log = LogManager.getLogger(Note09EntityGraph.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

//    List<Order> orderList = em.createQuery("select o from Order o inner join fetch o.orderRows", Order.class).getResultList();

//    EntityGraph<?> entityGraph = em.getEntityGraph("order-and-rows");
//
//    List<Order> orderList = em.createQuery("select o from Order o", Order.class)
//        .setHint("jakarta.persistence.fetchgraph", entityGraph).getResultList();

    List<Order> orderList = em.createQuery("select o from Order o",
        Order.class).getResultList();

    for (Order order : orderList) {
      log.info(order.toString());
      log.info(order.getOrderRows());
    }

    em.getTransaction().commit();
    em.close();

  }
}
