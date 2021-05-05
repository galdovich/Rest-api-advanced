package com.galdovich.esm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO extends RepresentationModel<OrderDTO> {
    private Long id;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private Long certificateId;
    private Long userId;

    public OrderDTO(Long id, BigDecimal price, LocalDateTime createdDate, Long certificateId, Long userId) {
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
        if (!super.equals(o)) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (id != null ? !id.equals(orderDTO.id) : orderDTO.id != null) return false;
        if (price != null ? !price.equals(orderDTO.price) : orderDTO.price != null) return false;
        if (createdDate != null ? !createdDate.equals(orderDTO.createdDate) : orderDTO.createdDate != null)
            return false;
        if (certificateId != null ? !certificateId.equals(orderDTO.certificateId) : orderDTO.certificateId != null)
            return false;
        return userId != null ? userId.equals(orderDTO.userId) : orderDTO.userId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (certificateId != null ? certificateId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
