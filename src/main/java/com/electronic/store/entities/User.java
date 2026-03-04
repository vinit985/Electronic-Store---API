package com.electronic.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "electronic_store_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @Column(name = "name")
    private String userName;
    @Column(name = "email",unique = true,nullable = false)
    private String userEmail;
    @Column(name = "password" ,length = 10,nullable = false)
    private String userPassword;
    @Column(name = "gender")
    private String userGender;
    @Column(name = "about",length = 1000)
    private String userAbout;
    @Column(name = "imageName")
    private String userImageName;


}
