package com.example.note3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Note14ClassicDelete {

  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    int updated = em.createQuery("delete from review r where r.product.id=:id")
        .setParameter("id", 2L)
        .executeUpdate();

    em.getTransaction().commit();
    em.close();

  }
}
