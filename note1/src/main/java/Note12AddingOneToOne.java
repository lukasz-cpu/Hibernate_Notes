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
public class Note12AddingOneToOne {
    private static final Logger log = LogManager.getLogger(Note02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Product product = em.find(Product.class, 5L);
//        Category category = new Category();
//        category.setName("Nowa kategoria");
//        category.setDescription("Opis nowej kategorii");
//        em.persist(category);
//        product.setCategory(category);

        Attribute attribute = new Attribute();
        attribute.setName("COLOR");
        attribute.setValue("BLACK");
        product.addAttributes(attribute);


        em.getTransaction().commit();
        em.close();
    }


}
