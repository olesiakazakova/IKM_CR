package ru.ikm.max.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.max.entity.Hall;
import ru.ikm.max.entity.Museum;

/**
 * Репозиторий для работы с сущностью {@link Museum}.
 * Предоставляет стандартные CRUD-операции и методы работы с БД.
 * Наследует функциональность {@link JpaRepository}.
 *
 * <p>Основные возможности:</p>
 * <ul>
 *   <li>Сохранение и обновление музеев</li>
 *   <li>Поиск по идентификатору</li>
 *   <li>Получение списка всех музеев</li>
 *   <li>Удаление музеев</li>
 * </ul>
 *
 * @see JpaRepository
 * @see Hall
 */
public interface MuseumRepository extends JpaRepository<Museum, Long> {
}