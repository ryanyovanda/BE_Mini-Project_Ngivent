-- Insert Indonesian-themed events (10 events per organizer)
INSERT INTO event (user_id, title, description, start_time, end_time, location, created_at, updated_at)
VALUES
-- Events by organizer 1
(1, 'Jakarta Fair', 'A vibrant festival showcasing Indonesian culture and trade.', '2024-11-15 10:00:00', '2024-11-20 22:00:00', 'Jakarta International Expo, Kemayoran', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Bali Kite Festival', 'Traditional kite competitions on the beaches of Bali.', '2024-11-17 08:00:00', '2024-11-18 18:00:00', 'Sanur Beach, Bali', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Java Jazz Festival', 'A celebration of music with international and Indonesian artists.', '2024-11-25 12:00:00', '2024-11-27 23:00:00', 'Jakarta Convention Center', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Yogyakarta Batik Festival', 'A festival dedicated to Indonesia’s iconic Batik art.', '2024-12-01 09:00:00', '2024-12-03 20:00:00', 'Malioboro Street, Yogyakarta', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Komodo Island Cleanup', 'An environmental event to protect Komodo Island.', '2024-11-30 07:00:00', '2024-11-30 17:00:00', 'Komodo National Park, Flores', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Events by organizer 2
(2, 'Solo Keroncong Festival', 'An event dedicated to the traditional Keroncong music.', '2024-11-20 17:00:00', '2024-11-22 21:00:00', 'Solo, Central Java', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Toraja Cultural Week', 'A celebration of Torajan traditions and culture.', '2024-11-28 08:00:00', '2024-12-02 20:00:00', 'Rantepao, Toraja', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Bandung Coffee Festival', 'Showcasing the best coffee from Java and Sumatra.', '2024-11-21 09:00:00', '2024-11-23 18:00:00', 'Bandung, West Java', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Sumatra Culinary Festival', 'Explore the rich culinary heritage of Sumatra.', '2024-11-26 10:00:00', '2024-11-29 22:00:00', 'Medan, North Sumatra', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Borneo Rainforest Marathon', 'A run through the lush rainforest of Kalimantan.', '2024-12-03 05:00:00', '2024-12-03 12:00:00', 'Kalimantan Rainforest, Borneo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Events by organizer 3
(3, 'Papua Diving Experience', 'A diving event in the beautiful waters of Raja Ampat.', '2024-11-18 06:00:00', '2024-11-22 16:00:00', 'Raja Ampat, Papua', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Maluku Spice Festival', 'A festival highlighting Maluku’s spice heritage.', '2024-11-24 10:00:00', '2024-11-26 20:00:00', 'Ambon, Maluku', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Aceh Tsunami Memorial', 'A tribute to the resilience of the Acehnese people.', '2024-11-29 08:00:00', '2024-11-30 18:00:00', 'Banda Aceh, Aceh', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Jakarta Marathon', 'Annual marathon through the streets of Jakarta.', '2024-12-01 05:00:00', '2024-12-01 12:00:00', 'Jakarta, Indonesia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Lombok Sasak Festival', 'Experience the traditions of the Sasak people.', '2024-12-02 09:00:00', '2024-12-03 20:00:00', 'Lombok, West Nusa Tenggara', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert tickets for events
INSERT INTO ticket (event_id, available_seat, sold_seat, name, price, is_released, is_closed, created_at, updated_at)
VALUES
(1, 100, 70, 'General Admission', 50000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 50, 30, 'Beach View Pass', 150000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 200, 150, 'Standard Ticket', 75000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 120, 90, 'Batik Workshop Pass', 100000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 80, 50, 'Cleanup Volunteer', 0, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(6, 100, 80, 'Music Lover Pass', 100000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 60, 40, 'Toraja Experience', 125000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 150, 120, 'Coffee Connoisseur', 75000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 100, 70, 'Foodie Pass', 50000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 50, 25, 'Marathon Entry', 200000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(11, 30, 20, 'Diving Package', 1000000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 80, 60, 'Spice Tour', 75000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 100, 90, 'Memorial Pass', 0, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, 500, 300, 'Marathon Entry', 150000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, 70, 40, 'Sasak Experience', 125000, TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert transactions for events
INSERT INTO transaction (user_id, ticket_id, ticket_price, total_price, created_at, updated_at)
VALUES
(4, 1, 50000, 50000, '2024-11-16 10:00:00', '2024-11-16 10:00:00'),
(5, 2, 150000, 150000, '2024-11-18 08:30:00', '2024-11-18 08:30:00'),
(6, 3, 75000, 75000, '2024-11-26 12:00:00', '2024-11-26 12:00:00'),
(4, 6, 100000, 100000, '2024-11-21 17:00:00', '2024-11-21 17:00:00'),
(5, 9, 50000, 50000, '2024-11-28 11:00:00', '2024-11-28 11:00:00'),
(4, 1, 50000, 50000, '2024-11-15 11:00:00', '2024-11-15 11:00:00'),
(5, 2, 150000, 150000, '2024-11-18 09:15:00', '2024-11-18 09:15:00'),
(6, 3, 75000, 150000, '2024-11-26 13:45:00', '2024-11-26 13:45:00'), -- Bought 2 tickets
(4, 4, 100000, 100000, '2024-12-01 10:30:00', '2024-12-01 10:30:00'),
(5, 5, 0, 0, '2024-11-30 08:00:00', '2024-11-30 08:00:00'), -- Free ticket
(6, 6, 100000, 300000, '2024-11-22 19:00:00', '2024-11-22 19:00:00'), -- Bought 3 tickets
(4, 7, 125000, 125000, '2024-11-28 09:00:00', '2024-11-28 09:00:00'),
(5, 8, 75000, 150000, '2024-11-22 14:30:00', '2024-11-22 14:30:00'), -- Bought 2 tickets
(6, 9, 50000, 50000, '2024-11-26 12:15:00', '2024-11-26 12:15:00'),
(4, 10, 200000, 200000, '2024-12-03 07:00:00', '2024-12-03 07:00:00'),
(5, 11, 1000000, 1000000, '2024-11-19 06:45:00', '2024-11-19 06:45:00'),
(6, 12, 75000, 75000, '2024-11-24 11:30:00', '2024-11-24 11:30:00'),
(4, 13, 0, 0, '2024-11-29 09:30:00', '2024-11-29 09:30:00'), -- Free ticket
(5, 14, 150000, 300000, '2024-12-01 05:30:00', '2024-12-01 05:30:00'), -- Bought 2 tickets
(6, 15, 125000, 125000, '2024-12-02 10:00:00', '2024-12-02 10:00:00'),
(4, 3, 75000, 75000, '2024-11-25 12:45:00', '2024-11-25 12:45:00'),
(5, 6, 100000, 200000, '2024-11-22 19:30:00', '2024-11-22 19:30:00'), -- Bought 2 tickets
(6, 9, 50000, 100000, '2024-11-27 15:15:00', '2024-11-27 15:15:00'), -- Bought 2 tickets
(4, 14, 150000, 450000, '2024-12-01 06:15:00', '2024-12-01 06:15:00'), -- Bought 3 tickets
(5, 10, 200000, 200000, '2024-12-03 08:15:00', '2024-12-03 08:15:00'),
(6, 2, 150000, 450000, '2024-11-18 10:30:00', '2024-11-18 10:30:00'), -- Bought 3 tickets
(4, 7, 125000, 125000, '2024-11-29 10:15:00', '2024-11-29 10:15:00'),
(5, 5, 0, 0, '2024-11-30 08:30:00', '2024-11-30 08:30:00'), -- Free ticket
(6, 11, 1000000, 2000000, '2024-11-20 07:15:00', '2024-11-20 07:15:00'), -- Bought 2 tickets
(4, 12, 75000, 225000, '2024-11-24 11:45:00', '2024-11-24 11:45:00'), -- Bought 3 tickets
(5, 1, 50000, 100000, '2024-11-15 12:15:00', '2024-11-15 12:15:00'), -- Bought 2 tickets
(6, 8, 75000, 75000, '2024-11-23 16:00:00', '2024-11-23 16:00:00'),
(4, 13, 0, 0, '2024-11-29 10:45:00', '2024-11-29 10:45:00'), -- Free ticket
(5, 15, 125000, 250000, '2024-12-02 12:00:00', '2024-12-02 12:00:00'), -- Bought 2 tickets
    (4, 1, 50000, 50000, '2024-12-01 10:00:00', '2024-12-01 10:00:00'),
    (4, 2, 150000, 150000, '2024-12-02 11:30:00', '2024-12-02 11:30:00'),
    (4, 3, 75000, 225000, '2024-12-03 12:45:00', '2024-12-03 12:45:00'),
    (4, 4, 100000, 100000, '2024-12-04 09:00:00', '2024-12-04 09:00:00'),
    (4, 5, 0, 0, '2024-12-05 08:00:00', '2024-12-05 08:00:00'),
    (5, 6, 100000, 300000, '2024-12-06 10:30:00', '2024-12-06 10:30:00'),
    (5, 7, 125000, 125000, '2024-12-07 14:00:00', '2024-12-07 14:00:00'),
    (5, 8, 75000, 150000, '2024-12-08 13:45:00', '2024-12-08 13:45:00'),
    (5, 9, 50000, 50000, '2024-12-09 10:15:00', '2024-12-09 10:15:00'),
    (5, 10, 200000, 200000, '2024-12-10 15:30:00', '2024-12-10 15:30:00'),
    (6, 11, 1000000, 1000000, '2024-12-11 06:00:00', '2024-12-11 06:00:00'),
    (6, 12, 75000, 225000, '2024-12-12 12:00:00', '2024-12-12 12:00:00'),
    (6, 13, 0, 0, '2024-12-13 09:15:00', '2024-12-13 09:15:00'),
    (6, 14, 150000, 300000, '2024-12-14 11:30:00', '2024-12-14 11:30:00'),
    (6, 15, 125000, 125000, '2024-12-15 10:45:00', '2024-12-15 10:45:00'),
    (4, 8, 75000, 75000, '2024-12-16 12:15:00', '2024-12-16 12:15:00'),
    (5, 3, 75000, 150000, '2024-12-17 09:45:00', '2024-12-17 09:45:00'),
    (6, 5, 0, 0, '2024-12-18 08:30:00', '2024-12-18 08:30:00'),
    (4, 9, 50000, 100000, '2024-12-19 14:00:00', '2024-12-19 14:00:00'),
    (5, 11, 1000000, 1000000, '2024-12-20 07:30:00', '2024-12-20 07:30:00'),
    (6, 7, 125000, 250000, '2024-12-21 11:45:00', '2024-12-21 11:45:00'),
    (4, 10, 200000, 200000, '2024-12-22 09:30:00', '2024-12-22 09:30:00'),
    (5, 14, 150000, 300000, '2024-12-23 08:15:00', '2024-12-23 08:15:00'),
    (6, 13, 0, 0, '2024-12-24 12:00:00', '2024-12-24 12:00:00'),
    (4, 1, 50000, 100000, '2024-12-25 10:30:00', '2024-12-25 10:30:00'),
    (5, 2, 150000, 300000, '2024-12-26 11:45:00', '2024-12-26 11:45:00'),
    (6, 3, 75000, 225000, '2024-12-27 13:15:00', '2024-12-27 13:15:00'),
    (4, 4, 100000, 200000, '2024-12-28 14:45:00', '2024-12-28 14:45:00'),
    (5, 5, 0, 0, '2024-12-29 09:15:00', '2024-12-29 09:15:00'),
    (6, 6, 100000, 300000, '2024-12-30 10:30:00', '2024-12-30 10:30:00'),
    (4, 7, 125000, 125000, '2024-12-31 12:15:00', '2024-12-31 12:15:00');