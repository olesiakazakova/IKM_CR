package ru.ikm.max.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая экспонат в системе.
 * Содержит информацию об экспонате и его описании.
 * Соответствует таблице "exhibits" в базе данных.
 */
@Getter
@Setter
@Entity
@Table(name = "exhibits")
public class Exhibit {
    /**
     * Уникальный идентификатор экспоната.
     * Генерируется автоматически при сохранении.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibits_id", nullable = false)
    private Long id;

    /**
     * Название экспоната. Обязательное поле.
     * Максимальная длина не ограничена (используется Integer.MAX_VALUE).
     */
    @NotNull
    @Column(name = "exhibits_name", nullable = false, length = Integer.MAX_VALUE)
    private String exhibitsName;

    /**
     * Описание экспоната. Необязательное поле.
     * Максимальная длина не ограничена (используется Integer.MAX_VALUE).
     */
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;
}