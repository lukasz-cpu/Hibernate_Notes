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
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note02Filtering {

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
    ParameterExpression<Long> id1 = criteriaBuilder.parameter(Long.class);
    ParameterExpression<Long> id2 = criteriaBuilder.parameter(Long.class);
    ParameterExpression<String> lastname = criteriaBuilder.parameter(String.class);
    ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
    ParameterExpression<Collection> ids = criteriaBuilder.parameter(Collection.class);
    criteriaQuery.select(customer).distinct(true)
        .where(
            criteriaBuilder.between(orders.get("total"), total,
                criteriaBuilder.literal(new BigDecimal("70.00"))),
            criteriaBuilder.isNotNull(customer.get("firstname")),
            customer.get("id").in(ids));

    TypedQuery<Customer> query = em.createQuery(criteriaQuery);
//    query.setParameter(id1, 1L);
//    query.setParameter(id2, 1L);
    query.setParameter(ids, List.of(1L, 2L, 4L));
    query.setParameter(total, new BigDecimal("30.00"));
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

