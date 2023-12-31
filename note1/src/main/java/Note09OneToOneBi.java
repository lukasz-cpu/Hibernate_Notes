import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note09OneToOneBi {
    private static final Logger log = LogManager.getLogger(Note02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Category category = em.find(Category.class, 1L);
        log.info(category.getName());
        log.info(category.getProducts().toString());



        em.getTransaction().commit();
        em.close();
    }


}
