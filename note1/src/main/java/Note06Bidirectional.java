import entity.Product;
import entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Note06Bidirectional {
    private static final Logger log = LogManager.getLogger(Note02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();


        Query selectPFromProductP = em.createQuery("select r from review r");
        List<Review> resultList = selectPFromProductP.getResultList();

        for (Review review : resultList) {
            log.info(review);
        }


        em.getTransaction().commit();
        em.close();
    }


}
