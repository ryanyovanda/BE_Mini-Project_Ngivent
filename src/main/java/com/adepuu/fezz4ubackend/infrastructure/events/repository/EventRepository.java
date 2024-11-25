package com.adepuu.fezz4ubackend.infrastructure.events.repository;

import com.adepuu.fezz4ubackend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
