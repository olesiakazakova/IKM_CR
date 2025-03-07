package ru.ikm.max.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.max.entity.Museum;

public interface MuseumRepository extends JpaRepository<Museum, Long> {
}