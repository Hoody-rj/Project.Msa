package com.msa.middleauth.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_phone;
    private String user_auth;
}
