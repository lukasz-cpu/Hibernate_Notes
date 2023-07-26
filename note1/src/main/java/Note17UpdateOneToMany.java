import entity.Product;
import entity.Review;
import entity.ReviewDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Note17UpdateOneToMany {
    private static final Logger log = LogManager.getLogger(Note02Read.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, 3L);

        List<ReviewDTO> reviewDtos = getUpdatedReviews();

        Map<Long, ReviewDTO> reviewDtoMap = new HashMap<>();

        for (ReviewDTO reviewDto : reviewDtos) {
            reviewDtoMap.put(reviewDto.getId(), reviewDto);
        }


        List<Review> reviews = product.getReviews();

        for (Review review : reviews) {
            ReviewDTO reviewDto = reviewDtoMap.get(review.getId());
            if (reviewDto != null) {
                review.setContent(reviewDto.getContent());
                review.setRating(reviewDto.getRating());
            }
        }

        em.getTransaction().commit();
        em.close();
    }

    private static List<ReviewDTO> getUpdatedReviews(){
        List<ReviewDTO> list = new ArrayList<>();
        list.add(new ReviewDTO(13l, "Treść opinii 3!!!", 10));
        list.add(new ReviewDTO(14l, "Treść opinii 4!!!", 10));
        list.add(new ReviewDTO(15l, "Treść opinii 5!!!", 10));
        return list;
    }
}
