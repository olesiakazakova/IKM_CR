package ru.ikm.max.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, представляющая зал в системе.
 * Содержит информацию о зале, включая его название.
 * Соответствует таблице "halls" в базе данных.
 */
@Getter
@Setter
@Entity
@Table(name = "halls")
public class Hall {
    /**
     * Уникальный идентификатор зала.
     * Генерируется автоматически при сохранении.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "halls_id", nullable = false)
    private Long id;

    /**
     * Название зала. Обязательное поле.
     * Максимальная длина - 100 символов.
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "halls_name", nullable = false, length = 100)
    private String hallsName;
}