package com.example.note5;

import com.example.note5.entity.Customer;
import com.example.note5.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Note03OrphanRemoval {
  private static Logger log = LogManager.getLogger(Note03OrphanRemoval.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();


    Order order = em.find(Order.class, 2L);
    em.remove(order);



    em.getTransaction().commit();
    em.close();
  }
}
