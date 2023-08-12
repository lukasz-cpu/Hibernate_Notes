package com.example.note5;

import com.example.note5.entity.RealProduct;
import com.example.note5.entity.VirtualProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Note04ComparingHashCode {
  private static Logger log = LogManager.getLogger(Note04ComparingHashCode.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();




    em.getTransaction().commit();
    em.close();
  }
}
