package com.tms.user.service.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Challan {

    private String challanId;
    private String userId;
    private String tollid;
    private String name;
    private String tollName;
    private String challanCharge;
    private String vehicletype;
    private String vehiclenumber;

    private Toll toll;

}
