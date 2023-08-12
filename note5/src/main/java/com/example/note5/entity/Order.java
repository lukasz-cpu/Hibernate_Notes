package com.example.note5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import org.hibernate.annotations.BatchSize;

@NamedEntityGraphs({
    @NamedEntityGraph(
        name = "order-rows",
        attributeNodes = {
            @NamedAttributeNode(value = "orderRows", subgraph = "orderRows"),
            @NamedAttributeNode("customer")
        },
        subgraphs = @NamedSubgraph(name = "orderRows", attributeNodes = @NamedAttributeNode("product"))
    ),
    @NamedEntityGraph(
        name = "order-and-rows",
        attributeNodes = {
            @NamedAttributeNode(value = "orderRows"),
        }
    ),
  }
)
@Entity
@Table(name = "\"order\"")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime created;
  private BigDecimal total;

  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "order_id")
  @BatchSize(size = 10)
  private Set<OrderRow> orderRows;

  @OneToOne(fetch = FetchType.LAZY)
  private Customer customer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Set<OrderRow> getOrderRows() {
    return orderRows;
  }

  public void setOrderRows(Set<OrderRow> orderRows) {
    this.orderRows = orderRows;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", created=" + created +
        ", total=" + total +
        '}';
  }
}

