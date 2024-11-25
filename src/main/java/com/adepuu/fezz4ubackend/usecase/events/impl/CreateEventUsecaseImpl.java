package com.adepuu.fezz4ubackend.usecase.events.impl;

import com.adepuu.fezz4ubackend.entity.Event;
import com.adepuu.fezz4ubackend.entity.User;
import com.adepuu.fezz4ubackend.infrastructure.events.dto.CreateEventRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.events.dto.CreateEventResponseDTO;
import com.adepuu.fezz4ubackend.infrastructure.events.repository.CategoryRepository;
import com.adepuu.fezz4ubackend.infrastructure.events.repository.EventRepository;
import com.adepuu.fezz4ubackend.infrastructure.users.repository.UsersRepository;
import com.adepuu.fezz4ubackend.usecase.events.CreateEventUsecase;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUsecaseImpl implements CreateEventUsecase {
  private final EventRepository eventRepository;
  private final UsersRepository usersRepository;
  private final CategoryRepository categoryRepository;

  public CreateEventUsecaseImpl(EventRepository eventRepository, UsersRepository usersRepository, CategoryRepository categoryRepository) {
    this.eventRepository = eventRepository;
    this.usersRepository = usersRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public CreateEventResponseDTO create(CreateEventRequestDTO req) {
    Event event = req.toEntity();
    User assignedOrganizer = usersRepository.findById(req.getOrganizerId()).orElseThrow(() -> new RuntimeException("Organizer not found"));
    if (!assignedOrganizer.isOrganizer()) {
      throw new RuntimeException("User is not an organizer");
    }
    event.setOrganizer(assignedOrganizer);
    event.setCategory(categoryRepository.findById(req.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));
    Event savedEvent = eventRepository.save(event);
    return new CreateEventResponseDTO(
            savedEvent.getId(),
            savedEvent.getOrganizer(),
            savedEvent.getCategory(),
            savedEvent.getTitle(),
            savedEvent.getDescription(),
            savedEvent.getLocation(),
            savedEvent.getEventDate(),
            savedEvent.getIsFree(),
            savedEvent.getPrice(),
            savedEvent.getAllocatedSeats(),
            savedEvent.getAvailableSeats()
    );
  }
}
