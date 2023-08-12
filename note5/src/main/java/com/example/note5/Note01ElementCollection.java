package com.example.note5;

import com.example.note5.entity.Address;
import com.example.note5.entity.AddressType;
import com.example.note5.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Note01ElementCollection {

  private static Logger log = LogManager.getLogger(Note01ElementCollection.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Customer customer = new Customer();
    customer.setFirstname("Customer 1");
    customer.setLastname("Customer 1");
    customer.setCreated(LocalDateTime.now());
    customer.setUpdated(LocalDateTime.now());
    customer.setAddress(Arrays.asList(
        new Address(AddressType.BILLING, "Polna 10/10", "00-000", "Warszawa"),
        new Address(AddressType.SHIPPING, "Letnia 10/10", "00-000", "Warszawa"),
        new Address(AddressType.INVOICE, "Wiosenna 10/10", "00-000", "Warszawa")
    ));
    em.persist(customer);

    em.flush();
    em.clear();

    Customer customer1 = em.find(Customer.class, customer.getId());
    log.info(customer1);
    log.info(customer1.getAddress());
    em.getTransaction().commit();
    em.close();

  }
}
