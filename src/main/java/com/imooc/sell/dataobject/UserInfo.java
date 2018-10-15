package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;




@Entity
@Data
@Table(name = "user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 4887904943282174032L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    private boolean active;

    @NotEmpty
    private String address;

    @NaturalId
    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min=3, message = "Length must be more than 3")
    private String password;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String role;
}
