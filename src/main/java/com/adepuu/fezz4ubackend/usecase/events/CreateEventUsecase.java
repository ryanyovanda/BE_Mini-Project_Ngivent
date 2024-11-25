package com.adepuu.fezz4ubackend.usecase.events;

import com.adepuu.fezz4ubackend.infrastructure.events.dto.CreateEventRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.events.dto.CreateEventResponseDTO;

public interface CreateEventUsecase {
  CreateEventResponseDTO create(CreateEventRequestDTO req);
}
