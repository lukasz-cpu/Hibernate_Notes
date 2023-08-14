package com.example.note5;

import com.example.note5.entity.Customer;
import com.example.note5.entity.batch.CustomerDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Note02MapsId {

  private static Logger log = LogManager.getLogger(Note02MapsId.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    var customer = em.find(Customer.class, 1L);
//    CustomerDetails customerDetails = new CustomerDetails();
//    customerDetails.setBirthPlace("Warszawa");
//    customerDetails.setBirthDay(LocalDate.of(2000, 10, 22));
//    customerDetails.setFatherName("Jan");
//    customerDetails.setMotherName("Janina");
//    customerDetails.setPesel("11111111111");
//    customerDetails.setCustomer(customer);
//    customer.setCustomerDetails(customerDetails);
//    em.persist(customer);

    log.info(customer);
    log.info(customer.getCustomerDetails());

    em.getTransaction().commit();
    em.close();
  }
}