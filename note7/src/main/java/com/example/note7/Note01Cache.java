package com.example.note7;

import com.example.note7.entity.Customer;
import com.example.note7.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note01Cache {

  private static Logger log = LogManager.getLogger(Note01Cache.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Customer customer = em.find(Customer.class, 1L);
    log.info(customer);
    em.getTransaction().commit();
    em.close();

    EntityManager em2 = entityManagerFactory.createEntityManager();
    em2.getTransaction().begin();
    customer = em2.find(Customer.class, 1L);
    log.info(customer);
    em2.getTransaction().commit();
    em2.close();
  }

}

