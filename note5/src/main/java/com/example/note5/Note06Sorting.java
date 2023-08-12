package com.example.note5;

import com.example.note5.entity.Customer;
import com.example.note5.entity.Product;
import com.example.note5.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Note06Sorting {

  private static Logger log = LogManager.getLogger(Note06Sorting.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

        Product product = em.find(Product.class, 1L);

//        Review review = new Review();
//        review.setContent("Content 1");
//        review.setRating(5);
//        review.setProduct(product);
//
//        Review review2 = new Review();
//        review2.setContent("Content 2");
//        review2.setRating(5);
//        review2.setProduct(product);
//
//        Review review3 = new Review();
//        review3.setContent("Content 3");
//        review3.setRating(5);
//        review3.setProduct(product);
//
//        Customer customer = em.find(Customer.class, 1L);
//        customer.getReviews().add(review);
//        customer.getReviews().add(review2);
//        customer.getReviews().add(review3);

    Customer customer = em.createQuery(
            "select c From customer c" +
                " inner join fetch c.reviews r" +
                " where c.id=:id" +
                " order by r.id desc",
            Customer.class
        )
        .setParameter("id", 1L)
        .getSingleResult();

    customer.getReviews().forEach(review -> {
      log.info(review);
    });

    em.getTransaction().commit();
    em.close();
  }
}
