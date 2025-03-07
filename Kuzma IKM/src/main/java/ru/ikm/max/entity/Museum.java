package ru.ikm.max.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "museums")
public class Museum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "museums_id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "museums_name", nullable = false, length = 50)
    private String museumsName;

    @NotNull
    @Column(name = "foundation_year", nullable = false)
    private LocalDate foundationYear;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

}