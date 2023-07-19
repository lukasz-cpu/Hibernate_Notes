import entity.Product;
import entity.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class Note03Update {
    private static Logger log = LogManager.getLogger(Note02Read.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Product product = em.find(Product.class, 1L);
        product.setName("Nowa nazwa produktu");
        Product mergedProduct = em.merge(product);
        log.info(mergedProduct);

        em.getTransaction().commit();
        em.close();
    }


}
