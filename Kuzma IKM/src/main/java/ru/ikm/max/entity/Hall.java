package ru.ikm.max.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "halls_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "halls_name", nullable = false, length = 100)
    private String hallsName;

}