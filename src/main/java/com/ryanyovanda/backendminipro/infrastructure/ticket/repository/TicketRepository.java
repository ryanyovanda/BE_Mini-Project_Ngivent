package com.ryanyovanda.backendminipro.infrastructure.ticket.repository;

import com.ryanyovanda.backendminipro.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findById(int id);

    List<Ticket> findTicketByEventId(int id);

    @Query("SELECT SUM(t.soldSeat) AS ticketsSold, " +
            "SUM(t.soldSeat * t.price) AS revenue, " +
            "TO_CHAR(t.createdAt, 'YYYY-MM-DD') AS timeUnit " +
            "FROM Ticket t JOIN t.event e " +
            "WHERE e.user.id = :userId AND t.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY TO_CHAR(t.createdAt, 'YYYY-MM-DD')")
    List<Object[]> getAnalyticsData(@Param("userId") Integer userId,
                                    @Param("startDate") OffsetDateTime startDate,
                                    @Param("endDate") OffsetDateTime endDate);

}
