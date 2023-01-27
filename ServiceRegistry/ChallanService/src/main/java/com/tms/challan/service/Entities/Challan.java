package com.tms.challan.service.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Documented;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "challans")
public class Challan {

    @Id
    private String challanId;
    private String userId;
    private String tollid;
    private String name;
    private String tollName;
    private String challanCharge;
    private String vehicletype;
    private String vehiclenumber;
}
