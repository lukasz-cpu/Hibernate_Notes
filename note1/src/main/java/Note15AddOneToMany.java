import entity.Product;
import entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note15AddOneToMany {
    private static final Logger log = LogManager.getLogger(Note02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Product product = em.find(Product.class, 5L);

//        Review review = new Review();
//        review.setContent("Nowa opinia 4");
//        review.setRating(2);
//        product.addReview(review);
//        review.setProduct(product);

        Review review = em.find(Review.class, 12L);
        product.addReview(review);




        em.getTransaction().commit();
        em.close();
    }


}