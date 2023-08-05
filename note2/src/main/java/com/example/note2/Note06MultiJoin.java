package com.example.note2;

import com.example.note2.entity.Category;
import com.example.note2.entity.Customer;
import com.example.note2.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note06MultiJoin {

  private static Logger log = LogManager.getLogger(Note05CartesianProduct.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    TypedQuery<Order> query = em.createQuery(
        "select c from order c",
        Order.class);


    em.getTransaction().commit();
    em.close();
  }
}
