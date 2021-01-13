package com.qweed.qweed.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private @Getter @Setter long id;
    @NotNull
    private @Getter @Setter String username;
    @NotNull
    private @Getter @Setter String password;
    private @Getter @Setter String motivation;


    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //private @Getter @Setter List<Medicine> medicines;

    public User(){}
}
