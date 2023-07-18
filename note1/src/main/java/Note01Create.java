
import entity.Product;
import entity.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@SpringBootApplication
public class Note01Create {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Product product = new Product();
        product.setName("Rower 01");
        product.setDescription("Opis produktu 01");
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        product.setPrice(new BigDecimal("19.99"));
        product.setProductType(ProductType.REAL);

        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }


}
