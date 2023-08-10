package com.example.note5;

import com.example.note5.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note03Aggregating {

  private static Logger log = LogManager.getLogger(Note01CriteriaJoin.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
    Root<Customer> customer = criteriaQuery.from(Customer.class);
    Join<Object, Object> orders = customer.join("orders", JoinType.INNER);
    Join<Object, Object> orderRows = orders.join("orderRows");
    Join<Object, Object> product = orderRows.join("product");
    Join<Object, Object> category = product.join("category");

    criteriaQuery.multiselect(
            customer.get("id"),
            customer.get("lastname"),
            category.get("name"),
            criteriaBuilder.sum(orderRows.get("price"))
        )
        .groupBy(category.get("id"), customer.get("id"))
        .orderBy(criteriaBuilder.desc(criteriaBuilder.sum(orderRows.get("price"))))
        .having(criteriaBuilder.greaterThan(criteriaBuilder.sum(orderRows.get("price")), 50));

    TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
    List<Object[]> resultList = query.getResultList();
    for (Object[] row : resultList) {
      log.info(row[0] + ", " + row[1] + ", " + row[2] + ", " + row[3]);
    }

    em.getTransaction().commit();
    em.close();

  }
}