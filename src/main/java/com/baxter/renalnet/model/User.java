package com.baxter.renalnet.model;

import com.baxter.renalnet.model.mapped.Vivo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Entity
public class User extends Vivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    Long id;

    @NotEmpty
    String name;

    @NotEmpty
    String address;

    @NotEmpty
    String email;
}
