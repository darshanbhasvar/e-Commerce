package com.example.productservicenaman.Models;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;

}
