package com.nyc.health.Clinic.EntityClinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Entity
@Table(name="clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    @NotEmpty(message = "First name is required")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="email")
    @Pattern(regexp = "")
    @NotEmpty(message = "Email is required")
    @Email
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="Mobile number is invalid")
    @Column(name="phoneNumber")
    private String phoneNumber;
@Column(name="status")
    private String status;
    @Column(name="StartDate")
    private String StartDate;
    @Column(name="EndDate")
    private String EndDate;
}
