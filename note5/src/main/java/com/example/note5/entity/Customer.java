package com.example.note5.entity;

import com.example.note5.entity.batch.CustomerDetails;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.hibernate.annotations.SortComparator;

@Entity(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstname;
  private String lastname;
  private LocalDateTime created;
  private LocalDateTime updated;

  @OneToMany(mappedBy = "customer")
  private Set<Order> orders;

  @ElementCollection
  private List<Address> address;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, optional = false)
  private CustomerDetails customerDetails;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "customer_id")
  @SortComparator(SortById.class)
  private SortedSet<Review> reviews = new TreeSet<>();

  @OneToMany(cascade = CascadeType.PERSIST)
  private List<Note> notes = new ArrayList<>();

  public static class SortById implements Comparator<Review>{

    @Override
    public int compare(Review o1, Review o2) {
      return o1.getId().compareTo(o2.getId());
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public Set<Order> getOrders() {
    return orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }

  public List<Address> getAddress() {
    return address;
  }

  public void setAddress(List<Address> address) {
    this.address = address;
  }

  public CustomerDetails getCustomerDetails() {
    return customerDetails;
  }

  public void setCustomerDetails(CustomerDetails customerDetails) {
    this.customerDetails = customerDetails;
  }

  public SortedSet<Review> getReviews() {
    return reviews;
  }

  public void setReviews(SortedSet<Review> reviews) {
    this.reviews = reviews;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", created=" + created +
        ", updated=" + updated +
        '}';
  }
}
