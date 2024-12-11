package com.ryanyovanda.backendminipro.infrastructure.transaction.repository;

import com.ryanyovanda.backendminipro.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Find by ID
    Optional<Transaction> findById(int id);

    // Find transactions by ticket ID
    List<Transaction> findTransactionsByTicketId(int ticketId);

    // Find transactions by user ID
    List<Transaction> findTransactionsByUserId(int userId);

    // Find specific transaction by ticket ID and user ID
    Optional<Transaction> findTransactionByTicketIdAndUserId(int ticketId, int userId);

    // Find specific transaction by user ID and event ID
    Optional<Transaction> findTransactionByUserIdAndTicketEvent_Id(int userId, Integer eventId);

    // Find paginated transactions for a user
    @Query("""
        SELECT t 
        FROM Transaction t
        JOIN t.ticket tk
        JOIN tk.event e
        WHERE t.user.id = :userId
    """)
    Page<Transaction> findAllTransactionsWithUserId(int userId, Pageable pageable);

    // Query to calculate daily revenue
    @Query(value = """
        SELECT 
            SUM(t.total_price) AS dailyRevenue, 
            COUNT(t.id) AS ticketCount, 
            TO_CHAR(t.created_at, 'YYYY-MM-DD') AS date 
        FROM transaction t 
        JOIN ticket tk ON t.ticket_id = tk.id 
        JOIN event e ON tk.event_id = e.id 
        WHERE e.user_id = :userId 
        AND t.created_at BETWEEN :start AND :end 
        GROUP BY TO_CHAR(t.created_at, 'YYYY-MM-DD')
    """, nativeQuery = true)
    List<Object[]> getDailyRevenue(Integer userId, OffsetDateTime start, OffsetDateTime end);

    // Query to calculate monthly revenue
    @Query(value = """
        SELECT 
            SUM(t.total_price) AS monthlyRevenue, 
            COUNT(t.id) AS ticketCount, 
            TO_CHAR(t.created_at, 'YYYY-MM') AS month 
        FROM transaction t 
        JOIN ticket tk ON t.ticket_id = tk.id 
        JOIN event e ON tk.event_id = e.id 
        WHERE e.user_id = :userId 
        AND t.created_at BETWEEN :start AND :end 
        GROUP BY TO_CHAR(t.created_at, 'YYYY-MM')
    """, nativeQuery = true)
    List<Object[]> getMonthlyRevenue(Integer userId, OffsetDateTime start, OffsetDateTime end);
}
