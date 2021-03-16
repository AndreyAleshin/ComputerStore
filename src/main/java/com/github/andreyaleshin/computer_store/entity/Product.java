package com.github.andreyaleshin.computer_store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @URL
    @NotEmpty
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private BigDecimal price;

//    @CreationTimestamp
//    private Date createdAt;
//
//    @UpdateTimestamp
//    private Date updatedAt;

}
