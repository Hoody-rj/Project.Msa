package com.msa.middleauth.sign.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Setter
@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
public class User {
    @Id
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_phone;
    private String user_auth;

    public User(String user_id, String user_pwd, String user_name, String user_phone) {
        this.user_id = user_id;
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_auth = "user";
    }
}
