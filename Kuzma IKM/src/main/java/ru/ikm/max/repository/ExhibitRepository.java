package ru.ikm.max.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.max.entity.Exhibit;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
}