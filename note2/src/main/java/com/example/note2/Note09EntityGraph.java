package com.example.note2;

import com.example.note2.entity.Order;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note09EntityGraph {

  private static Logger log = LogManager.getLogger(Note09EntityGraph.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    EntityGraph<?> entityGraph = em.getEntityGraph("order-rows");

    Map<String, Object> properties = new HashMap<>();
    properties.put("jakarta.persistence.fetchgraph", entityGraph);
    Order order = em.find(Order.class, 1L, properties);

    log.info(order);
    log.info(order.getOrderRows());
    log.info(order.getCustomer());

    em.getTransaction().commit();
    em.close();
  }
}
