package com.tms.user.service.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="ID")
    private String userId;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    private String gender;

    @Column(name="adharnumber")
    private String adharNumber;

    @Column(name="mobilenumber")
    private String mobileNumber;

    @Transient
    private List<Challan> challancharge =new ArrayList<>();

}
