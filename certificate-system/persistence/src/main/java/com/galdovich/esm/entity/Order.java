package com.galdovich.esm.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificate_orders")
public class Order implements GiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_order_id")
    private Long id;
    @Column(name = "certificate_order_price")
    private BigDecimal price;
    @Column(name = "certificate_order_date")
    private LocalDateTime createdDate;
    @Column(name = "certificate_id")
    private Long certificateId;
    @Column(name = "user_id")
    private Long userId;

    public Order() {
    }

    public Order(Long certificateId, Long userId) {
        this.certificateId = certificateId;
        this.userId = userId;
    }

    public Order(Long id, BigDecimal price, LocalDateTime createdDate, Long certificateId, Long userId) {
        this.id = id;
        this.price = price;
        this.createdDate = createdDate;
        this.certificateId = certificateId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (createdDate != null ? !createdDate.equals(order.createdDate) : order.createdDate != null) return false;
        if (certificateId != null ? !certificateId.equals(order.certificateId) : order.certificateId != null)
            return false;
        return userId != null ? userId.equals(order.userId) : order.userId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (certificateId != null ? certificateId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", certificateId=").append(certificateId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
