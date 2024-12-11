package com.adepuu.fezz4ubackend.infrastructure.events.dto;

import com.adepuu.fezz4ubackend.entity.Category;
import com.adepuu.fezz4ubackend.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventResponseDTO {
  private Long id;

  @NotNull
  private User organizer;

  @NotNull
  private Category category;

  @Size(max = 100)
  @NotNull
  private String title;

  @NotNull
  private String description;

  @Size(max = 100)
  @NotNull
  private String location;

  @NotNull
  @Column(name = "event_date", nullable = false)
  private OffsetDateTime eventDate;

  @NotNull
  private Boolean isFree = false;

  private BigDecimal price;

  @NotNull
  private Integer allocatedSeats;

  @NotNull
  private Integer availableSeats;
}
