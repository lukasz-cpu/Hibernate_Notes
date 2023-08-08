package com.example.note2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
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

    Query query = em.createQuery(
        "select distinct c.id, c.lastname as customer, ca.name as category, SUM(orw.price) as total " +
            "from customer c" +
            " inner join c.orders o" +
            " inner join o.orderRows orw" +
            " inner join orw.product p" +
            " inner join p.category ca" +
            " group by ca, c" +
            " having SUM(orw.price) > 50" +
            " order by total desc"
    );

    List<Object[]> resultList = query.getResultList();
    for (Object[] row : resultList) {
      log.info(row[0] + ", \t" + row[1] + ", \t" + row[2] + ", \t" + row[3]);
    }


    em.getTransaction().commit();
    em.close();
  }
}
