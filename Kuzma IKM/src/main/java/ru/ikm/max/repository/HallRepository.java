package ru.ikm.max.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.max.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {
}