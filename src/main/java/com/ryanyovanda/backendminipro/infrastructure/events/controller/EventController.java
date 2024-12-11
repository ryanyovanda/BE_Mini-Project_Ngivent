//package com.ryanyovanda.backendminipro.infrastructure.events.controller;
//
//import com.ryanyovanda.backendminipro.common.response.Response;
//import com.ryanyovanda.backendminipro.infrastructure.auth.Claims;
////import com.ryanyovanda.backendminipro.infrastructure.events.dto.CreateEventRequestDTO;
//import com.ryanyovanda.backendminipro.usecase.events.CreateEventUsecase;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/events")
//public class EventController {
//  private final CreateEventUsecase createEventUsecase;
//
//  public EventController(CreateEventUsecase createEventUsecase) {
//    this.createEventUsecase = createEventUsecase;
//  }
//
//  @PreAuthorize("hasAuthority('SCOPE_ORGANIZER')")
//  @PostMapping
//  public ResponseEntity<?> createEvent(@Validated @RequestBody CreateEventRequestDTO req) {
//    req.setOrganizerId(Claims.getUserIdFromJwt());
//    return Response.successfulResponse("Create event success", createEventUsecase.create(req));
//  }
//}
