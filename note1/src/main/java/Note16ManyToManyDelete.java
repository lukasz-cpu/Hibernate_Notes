import entity.Attribute;
import entity.Product;
import entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Note16ManyToManyDelete {
    private static final Logger log = LogManager.getLogger(Note02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
//
//        Product product = em.find(Product.class, 5L);
//        product.getAttributes().clear();
        Attribute attribute = em.find(Attribute.class, 1L);
        List<Product> products = attribute.getProducts().stream().toList();
        for (Product product : products) {
            attribute.removeProduct(product);
        }
        em.remove(attribute);

        em.getTransaction().commit();
        em.close();
    }
}
