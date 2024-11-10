package com.SpringProject.SpringBoot_Ecom.model;
import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigInteger price;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private Boolean productAvailable;
    private int stockQuantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
}
