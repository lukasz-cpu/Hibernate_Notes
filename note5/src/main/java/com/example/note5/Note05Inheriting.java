package com.example.note5;

import com.example.note5.entity.RealProduct;
import com.example.note5.entity.VirtualProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Note05Inheriting {
  private static Logger log = LogManager.getLogger(Note05Inheriting.class);
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
      "unit");

  public static void main(String[] args) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();


    VirtualProduct virtualProduct = new VirtualProduct();
    virtualProduct.setName("Virtual");
    virtualProduct.setDescription("Desc virtual");
    virtualProduct.setCreated(LocalDateTime.now());
    virtualProduct.setDownloadable(true);
    virtualProduct.setFileName("test.txt");
    virtualProduct.setFilePath("/store");
    em.persist(virtualProduct);

    RealProduct realProduct = new RealProduct();
    realProduct.setName("Real");
    realProduct.setDescription("Desc real");
    realProduct.setCreated(LocalDateTime.now());
    realProduct.setWeight(10.5f);
    realProduct.setWidth(20);
    realProduct.setLength(10);
    realProduct.setHeight(30);
    em.persist(realProduct);

    em.flush();
    em.clear();

    log.info(em.find(VirtualProduct.class, virtualProduct.getId()));
    log.info(em.find(RealProduct.class, realProduct.getId()));



    em.getTransaction().commit();
    em.close();
  }
}
