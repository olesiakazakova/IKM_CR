package ru.ikm.max.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.max.entity.Hall;

import java.util.List;

/**
 * Репозиторий для работы с сущностью Hall.
 * Предоставляет стандартные CRUD-операции и методы работы с БД.
 * Наследует функциональность JpaRepository.
 * <p>Основные возможности:</p>
 * <ul>
 *   <li>Сохранение и обновление залов</li>
 *   <li>Поиск по идентификатору</li>
 *   <li>Получение списка всех залов</li>
 *   <li>Удаление залов</li>
 * </ul>
 */
public interface HallRepository extends JpaRepository<Hall, Long> {
}
