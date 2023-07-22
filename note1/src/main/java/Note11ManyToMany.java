import entity.Attribute;
import entity.Category;
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
public class Note11ManyToMany {
    private static final Logger log = LogManager.getLogger(Note02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        log.info(product.toString());
        log.info(product.getAttributes());

        Attribute attribute = em.find(Attribute.class, 1L);
        log.info(attribute.toString());
        log.info(attribute.getProducts());


        em.getTransaction().commit();
        em.close();
    }


}
