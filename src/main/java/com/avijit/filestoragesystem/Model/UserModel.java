package com.avijit.filestoragesystem.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserModel extends BaseModel{
    @Column(nullable=false)
    private String name;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private Character gender;
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private UserType userType;

}
