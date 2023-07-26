package com.example.note2;

import com.example.note2.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Note1JPQL {
	private static final Logger log = LogManager.getLogger(Note1JPQL.class);
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

	public static void main(String[] args) {

		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Product> query = em.createQuery("select p from product p order by p.id desc", Product.class);

		List<Product> resultList = query.getResultList();

		for (Product product : resultList) {
			log.info(product.toString());
		}


		em.getTransaction().commit();
		em.close();
	}


}
