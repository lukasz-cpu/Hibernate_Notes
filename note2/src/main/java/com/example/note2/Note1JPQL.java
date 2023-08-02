package com.example.note2;

import com.example.note2.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note1JPQL {
	private static final Logger log = LogManager.getLogger(Note1JPQL.class);
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

	public static void main(String[] args) {

		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Product> query = em.createQuery("select p from product p order by p.id desc",
				Product.class);
		TypedQuery<Product> query2 = em.createQuery("select p from product p where p.name like '%04'",
				Product.class);
		TypedQuery<Product> queryWithParam = em.createQuery("select p from product p where p.id=:id",
				Product.class);
		queryWithParam.setParameter("id", 3);
		TypedQuery<Double> query1 = em.createQuery("select avg(p.price) from product p", Double.class);

		System.out.println(query1.getSingleResult());

		List<Product> resultList = queryWithParam.getResultList();

		for (Product product : resultList) {
			log.info(product.toString());
		}
		Query countWithAvg = em.createQuery("select count(p), avg(p.price) from product p");
		Map<String, Object> hints = countWithAvg.getHints();

		System.out.println("----------------------");
		System.out.println(hints);
		System.out.println("------------------");

		Object[] countWithAvgResult = (Object[]) countWithAvg.getSingleResult();

		System.out.println(countWithAvgResult[0]);
		System.out.println(countWithAvgResult[1]);

		Query countCategory = em.createQuery("select count(p) from product p group by p.category");

		long countCategroy = (long) countCategory.getSingleResult();

		System.out.println("Category count: " + countCategroy);

		Query categoryWithCount = em.createQuery(
				"select p.category.name, count(p) from product p group by p.category");
		List<Object[]> categoryWithCountList = categoryWithCount.getResultList();
		Object[] categoryWithCountArray = categoryWithCountList.get(0);
		String category = (String) categoryWithCountArray[0];
		long size = (long) categoryWithCountArray[1];

		System.out.println("Category: " + category + " size: " + size);

		em.getTransaction().commit();
		em.close();
	}


}
