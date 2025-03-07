package ru.ikm.max.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exhibits")
public class Exhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibits_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "exhibits_name", nullable = false, length = Integer.MAX_VALUE)
    private String exhibitsName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

}