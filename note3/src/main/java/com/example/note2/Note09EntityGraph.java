package com.example.note2;

import com.example.note2.entity.Order;
import com.example.note2.entity.OrderRow;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Subgraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

//    EntityGraph<?> entityGraph = em.getEntityGraph("order-rows");

    EntityGraph entityGraph = em.createEntityGraph(Order.class);
    entityGraph.addAttributeNodes("orderRows");
    entityGraph.addAttributeNodes("customer");
    Subgraph orderRowsSubgraph = entityGraph.addSubgraph("orderRows");
    orderRowsSubgraph.addAttributeNodes("product");

//    Map<String, Object> properties = new HashMap<>();
//    properties.put("jakarta.persistence.fetchgraph", entityGraph);
//    Order order = em.find(Order.class, 1L, properties);

    List<Order> orderList = em.createQuery("select o from Order o", Order.class).setHint("jakarta.persistence.fetchgraph", entityGraph)
        .getResultList();

    for (Order order : orderList) {
      log.info(order);
      Set<OrderRow> orderRows = order.getOrderRows();
      for (OrderRow orderRow : orderRows) {
        log.info("Product: " + orderRow.getProduct());
      }
      log.info(orderRows);
      log.info(order.getCustomer());

    }


    em.getTransaction().commit();
    em.close();
  }
}
