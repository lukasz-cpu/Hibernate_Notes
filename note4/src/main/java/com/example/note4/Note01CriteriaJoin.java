package com.example.note4;

import com.example.note4.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note01CriteriaJoin {

  private static Logger log = LogManager.getLogger(Note01CriteriaJoin.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
    Root<Customer> customer = criteriaQuery.from(Customer.class);
    Join<Object, Object> orders = (Join<Object, Object>) customer.fetch("orders", JoinType.INNER);
    orders.fetch("orderRows").fetch("product");
    ParameterExpression<Long> id = criteriaBuilder.parameter(Long.class);
    ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
    criteriaQuery.select(customer).distinct(true)
        .where(criteriaBuilder.and(criteriaBuilder.equal(customer.get("id"), id)),
            criteriaBuilder.greaterThan(orders.get("total"), total));

    TypedQuery<Customer> query = em.createQuery(criteriaQuery);
    query.setParameter(id, 1L);
    query.setParameter(total, new BigDecimal("50.00"));
    List<Customer> resultList = query.getResultList();
    for (Customer result : resultList) {
      log.info(result);
      result.getOrders().forEach(order -> {
        log.info(order);
        order.getOrderRows().forEach(orderRow -> {
          log.info(orderRow);
          log.info(orderRow.getProduct());
        });
      });
    }

    em.getTransaction().commit();
    em.close();

  }
}
