package com.avijit.filestoragesystem.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserType extends BaseModel {
    @Column(unique = true)
    private String type;

}
