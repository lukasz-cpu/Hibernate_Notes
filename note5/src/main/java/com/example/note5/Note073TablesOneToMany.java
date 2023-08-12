package com.example.note5;

import com.example.note5.entity.Customer;
import com.example.note5.entity.Note;
import com.example.note5.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Note073TablesOneToMany {

  private static Logger log = LogManager.getLogger(Note073TablesOneToMany.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Customer customer = em.find(Customer.class, 1L);
    customer.getNotes().add(new Note("Content 1", LocalDateTime.now()));
    customer.getNotes().add(new Note("Content 2", LocalDateTime.now()));
    customer.getNotes().add(new Note("Content 3", LocalDateTime.now()));
    customer.getNotes().add(new Note("Content 4", LocalDateTime.now()));

    em.getTransaction().commit();
    em.close();
  }
}
