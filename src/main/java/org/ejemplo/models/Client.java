package org.ejemplo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="dni", length=50)
    private String dni;

    @Column(name="cuil", length=50)
    private String cuil;

    @Column(name="address", length=100)
    private String address;

    @Column(name="phone", length=50)
    private String phone;
}
