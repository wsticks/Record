package com.williams.record.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "records")
public class Record {

    private Long id;
    private String uniqueKey;
    private String nameOfProduct;
    private String description;
    private double price;
    private String quantity;
    private Timestamp created_At;
    private Timestamp update_At;

    @Id
    @GeneratedValue
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Basic
    @Column(name="unique_key", nullable = true)
    public String getUniqueKey() { return uniqueKey; }

    public void setUniqueKey(String uniqueKey) { this.uniqueKey = uniqueKey; }

    @Basic
    @Column(name="name_of_product", nullable = true)
    public String getNameOfProduct() { return nameOfProduct; }

    public void setNameOfProduct(String nameOfProduct) { this.nameOfProduct = nameOfProduct; }

    @Basic
    @Column(name="description", nullable = true)
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name="price", nullable = true)
    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    @Basic
    @Column(name="quantity", nullable = true)
    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) { this.quantity = quantity; }

    @Basic
    @Column(name="created_at", nullable = false)
    public Timestamp getCreated_At() {return created_At; }

    public void setCreated_At(Timestamp created_At) { this.created_At = created_At; }

    @Basic
    @Column(name="updated_at", nullable = true)
    public Timestamp getUpdate_At() { return update_At; }

    public void setUpdate_At(Timestamp update_At) { this.update_At = update_At; }

    @PrePersist
    public void beforeSave() {
        this.created_At = new Timestamp(new Date().getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Double.compare(record.price, price) == 0 &&
                Objects.equals(id, record.id) &&
                Objects.equals(uniqueKey, record.uniqueKey) &&
                Objects.equals(nameOfProduct, record.nameOfProduct) &&
                Objects.equals(description, record.description) &&
                Objects.equals(quantity, record.quantity) &&
                Objects.equals(created_At, record.created_At) &&
                Objects.equals(update_At, record.update_At);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uniqueKey, nameOfProduct, description, price, quantity, created_At, update_At);
    }

    @PreUpdate
    private void beforeUpdate() {
        this.update_At = new Timestamp(new Date().getTime());}
}
