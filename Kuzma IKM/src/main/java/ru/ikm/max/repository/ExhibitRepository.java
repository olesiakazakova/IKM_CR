package ru.ikm.max.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.max.entity.Exhibit;
import ru.ikm.max.entity.Hall;

import java.util.List;

/**
 * Репозиторий для работы с сущностью Exhibit.
 * Предоставляет стандартные CRUD-операции и методы работы с БД.
 * Наследует функциональность JpaRepository.
 * <p>Основные возможности:</p>
 * <ul>
 *   <li>Сохранение и обновление экспонатов</li>
 *   <li>Поиск по идентификатору</li>
 *   <li>Получение списка всех экспонатов</li>
 *   <li>Удаление экспонатов</li>
 * </ul>
 */
public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
}
