package ru.ikm.max.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, представляющая музей в системе.
 * Содержит основную информацию о музее: название, дату основания,
 * местоположение (страна и город).
 * Соответствует таблице "museums" в базе данных.
 */
@Getter
@Setter
@Entity
@Table(name = "museums")
public class Museum {
    /**
     * Уникальный идентификатор музея.
     * Генерируется автоматически при сохранении.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "museums_id", nullable = false)
    private Long id;

    /**
     * Название музея. Обязательное поле.
     * Максимальная длина - 50 символов.
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "museums_name", nullable = false, length = 50)
    private String museumsName;

    /**
     * Год основания музея. Обязательное поле.
     */
    @NotNull
    @Column(name = "foundation_year", nullable = false)
    //TODO:
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate foundationYear;

    /**
     * Страна расположения музея. Обязательное поле.
     * Максимальная длина - 50 символов.
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    /**
     * Город расположения музея. Обязательное поле.
     * Максимальная длина - 50 символов.
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;
}