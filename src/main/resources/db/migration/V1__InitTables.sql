-- Migration: V1__Init_tables.sql

-- Create users table
CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    referrer_id BIGINT REFERENCES users(user_id),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    pin VARCHAR(255) NOT NULL,
    profile_picture_url VARCHAR(100),
    is_onboarding_finished BOOLEAN NOT NULL DEFAULT FALSE,
    referral_code VARCHAR(255),
    used_referral_code TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

-- Create vouchers table
CREATE TABLE vouchers (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    type VARCHAR(20) NOT NULL,
    value INT NOT NULL,
    is_used BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create events table
CREATE TABLE event (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(user_id) ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_time TIMESTAMP WITH TIME ZONE NOT NULL,
    location TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

-- Create categories table
CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);


-- Create tickets table
CREATE TABLE ticket (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT REFERENCES event(id) ON DELETE CASCADE,
    available_seat INT NOT NULL,
    sold_seat INT NOT NULL DEFAULT 0,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    is_released BOOLEAN NOT NULL DEFAULT FALSE,
    is_closed BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

-- Create transactions table
CREATE TABLE transaction (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    ticket_id BIGINT NOT NULL REFERENCES ticket(id) ON DELETE CASCADE,
    ticket_price DOUBLE PRECISION NOT NULL,
    total_price DOUBLE PRECISION NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

-- Add indexes for performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_event_user_id ON event(user_id);
CREATE INDEX idx_ticket_event_id ON ticket(event_id);
CREATE INDEX idx_transaction_user_id ON transaction(user_id);
CREATE INDEX idx_transaction_ticket_id ON transaction(ticket_id);



-- create table users (
--     user_id bigserial constraint users_pk primary key,
--     email varchar(50) not null constraint users_email_unique unique,
--     password text not null,
--     pin varchar(50) not null,
--     profile_picture_url varchar(100),
--     referral_code VARCHAR(50) CONSTRAINT users_referral_code_unique UNIQUE,
--     used_referral_code VARCHAR(255),
--     is_onboarding_finished boolean default false not null,
--     created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
--     updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
--     deleted_at timestamp with time zone
-- );
--
-- create unique index users_email_uindex on users (email);
--
-- create unique index users_user_id_uindex on users (user_id desc);
--
--
-- -- Drop table
--
-- -- DROP TABLE public.categories;
--
-- CREATE TABLE public.categories (
-- 	category_id bigserial NOT NULL,
-- 	"name" varchar(100) NOT NULL,
-- 	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
-- 	updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
-- 	deleted_at timestamptz NULL,
-- 	CONSTRAINT categories_pkey PRIMARY KEY (category_id)
-- );
--
--
-- -- public."event" definition
--
-- -- Drop table
--
-- -- DROP TABLE public."event";
--
-- CREATE TABLE public."event" (
-- 	id serial4 NOT NULL,
-- 	user_id int4 NULL,
-- 	title varchar(255) NOT NULL,
-- 	description text NULL,
-- 	start_time timestamptz NOT NULL,
-- 	end_time timestamptz NOT NULL,
-- 	"location" text NOT NULL,
-- 	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
-- 	updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
-- 	deleted_at timestamptz NULL,
-- 	CONSTRAINT event_pkey PRIMARY KEY (id)
-- );
--
--
-- -- public.ticket definition
--
-- -- Drop table
--
-- -- DROP TABLE public.ticket;
--
-- CREATE TABLE public.ticket (
-- 	id serial4 NOT NULL,
-- 	event_id int4 NULL,
-- 	available_seat int4 NOT NULL,
-- 	sold_seat int4 DEFAULT 0 NOT NULL,
-- 	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
-- 	updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
-- 	deleted_at timestamptz NULL,
-- 	"name" varchar(255) NOT NULL,
-- 	price numeric(10, 2) NOT NULL,
-- 	is_released bool DEFAULT false NOT NULL,
-- 	is_closed bool DEFAULT false NOT NULL,
-- 	CONSTRAINT ticket_pkey PRIMARY KEY (id),
-- 	CONSTRAINT ticket_event_id_fkey FOREIGN KEY (event_id) REFERENCES public."event"(id) ON DELETE SET NULL
-- );
--
-- INSERT INTO public."event" (user_id,title,description,start_time,end_time,"location",created_at,updated_at,deleted_at) VALUES
-- 	 (1,'Jakarta International Jazz Festival','A grand music event featuring local and international jazz artists.','2024-08-15 18:00:00+07','2024-08-15 23:00:00+07','Jakarta International Expo, Jakarta','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Surabaya Food Carnival','A festival showcasing the best of Surabaya’s culinary delights.','2024-09-12 10:00:00+07','2024-09-12 20:00:00+07','Taman Bungkul, Surabaya','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (3,'Bali Yoga Retreat','A rejuvenating yoga retreat in the heart of Bali.','2024-11-05 06:00:00+07','2024-11-07 16:00:00+07','Ubud, Bali','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Bandung Tech Conference','A tech conference bringing together Indonesia’s brightest minds in technology.','2024-12-20 09:00:00+07','2024-12-20 18:00:00+07','Trans Convention Centre, Bandung','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Medan Cultural Night','An evening celebrating the diverse culture of Medan through music, dance, and food.','2024-10-25 18:00:00+07','2024-10-25 22:00:00+07','Santika Premiere Hotel, Medan','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (3,'Yogyakarta Batik Festival','Experience the art and beauty of batik from Yogyakarta.','2024-11-18 10:00:00+07','2024-11-18 21:00:00+07','Kraton Yogyakarta, Yogyakarta','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Makassar Film Screening','A screening of award-winning Indonesian films.','2024-09-08 17:00:00+07','2024-09-08 21:00:00+07','Losari Beach, Makassar','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Semarang Historical Walk','A guided walk exploring Semarang’s colonial past.','2025-02-14 08:00:00+07','2025-02-14 11:00:00+07','Old Town, Semarang','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (3,'Jakarta Theatre Night','A night of captivating performances by Indonesian theatre groups.','2024-12-10 19:00:00+07','2024-12-10 22:00:00+07','Taman Ismail Marzuki, Jakarta','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Palembang River Festival','A festival along the Musi River featuring water sports and local food.','2025-01-17 10:00:00+07','2025-01-17 20:00:00+07','Benteng Kuto Besak, Palembang','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL);
-- INSERT INTO public."event" (user_id,title,description,start_time,end_time,"location",created_at,updated_at,deleted_at) VALUES
-- 	 (2,'Manado Diving Competition','A diving competition in the crystal-clear waters of Bunaken.','2024-11-27 07:00:00+07','2024-11-27 15:00:00+07','Bunaken National Park, Manado','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (3,'Denpasar Art Exhibition','An exhibition of contemporary art from Bali and beyond.','2025-03-01 08:00:00+07','2025-03-01 16:00:00+07','Art Center, Denpasar','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Solo Wayang Show','A traditional shadow puppet performance in Solo.','2024-10-03 19:00:00+07','2024-10-03 21:00:00+07','Museum Radya Pustaka, Solo','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Balikpapan Eco Run','A fun run promoting environmental awareness.','2024-08-22 05:00:00+07','2024-08-22 08:00:00+07','Manggar Beach, Balikpapan','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (3,'Malang Coffee Festival','Discover the best coffee blends from Malang and surrounding areas.','2025-01-30 09:00:00+07','2025-01-30 18:00:00+07','Kampung Coklat, Malang','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Pekanbaru Music Parade','A parade showcasing various music genres.','2025-02-20 14:00:00+07','2025-02-20 20:00:00+07','Sultan Syarif Kasim II Park, Pekanbaru','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Pontianak Lantern Festival','An enchanting night with beautiful lanterns on display.','2024-08-31 18:00:00+07','2024-08-31 22:00:00+07','Kapuas River Bank, Pontianak','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (3,'Aceh Traditional Dance Competition','A showcase of Aceh’s traditional dances.','2025-03-10 10:00:00+07','2025-03-10 14:00:00+07','Taman Budaya, Banda Aceh','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Kupang Culinary Festival','Taste the best NTT has to offer.','2024-09-20 09:00:00+07','2024-09-20 21:00:00+07','Kupang Waterfront, Kupang','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Padang Literature Day','A day celebrating Padang’s rich literary history.','2024-12-05 09:00:00+07','2024-12-05 17:00:00+07','Taman Budaya Padang, Padang','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL);
-- INSERT INTO public."event" (user_id,title,description,start_time,end_time,"location",created_at,updated_at,deleted_at) VALUES
-- 	 (3,'Cirebon Classical Music Evening','A relaxing evening with classical music performances.','2025-01-09 18:00:00+07','2025-01-09 20:00:00+07','Keraton Kasepuhan, Cirebon','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Samarinda Kite Festival','A fun-filled day with colorful kites soaring in the sky.','2024-11-22 10:00:00+07','2024-11-22 16:00:00+07','Lamin Etam Field, Samarinda','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Banjarmasin Floating Market Fair','Experience the unique floating market culture.','2024-12-13 07:00:00+07','2024-12-13 12:00:00+07','Lok Baintan, Banjarmasin','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (3,'Ambon Music Festival','A celebration of Ambon’s music heritage.','2025-02-05 13:00:00+07','2025-02-05 19:00:00+07','Ambon City Center, Ambon','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (1,'Jayapura Marathon','A thrilling marathon in the beautiful landscapes of Papua.','2024-11-08 03:30:00+07','2024-11-08 08:00:00+07','Jayapura City, Jayapura','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL),
-- 	 (2,'Jambi Book Fair','A book fair featuring local and national authors.','2025-03-15 09:00:00+07','2025-03-15 17:00:00+07','Taman Budaya Jambi, Jambi','2024-12-13 11:25:39.000593+07','2024-12-13 11:25:39.000593+07',NULL);
--
-- INSERT INTO public.ticket (event_id,available_seat,sold_seat,created_at,updated_at,deleted_at,"name",price,is_released,is_closed) VALUES
-- 	 (1,500,200,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Jakarta International Jazz Festival Ticket',300000.00,true,false),
-- 	 (2,300,150,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Surabaya Food Carnival Ticket',100000.00,true,false),
-- 	 (3,100,60,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Bali Yoga Retreat Ticket',2000000.00,true,false),
-- 	 (4,400,300,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Bandung Tech Conference Ticket',500000.00,true,false),
-- 	 (5,350,140,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Medan Cultural Night Ticket',150000.00,true,false),
-- 	 (6,500,450,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Yogyakarta Batik Festival Ticket',120000.00,true,false),
-- 	 (7,200,120,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Makassar Film Screening Ticket',100000.00,true,false),
-- 	 (8,300,180,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Semarang Historical Walk Ticket',80000.00,true,false),
-- 	 (9,400,320,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Jakarta Theatre Night Ticket',250000.00,true,false),
-- 	 (10,450,400,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Palembang River Festival Ticket',120000.00,true,false);
-- INSERT INTO public.ticket (event_id,available_seat,sold_seat,created_at,updated_at,deleted_at,"name",price,is_released,is_closed) VALUES
-- 	 (11,350,100,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Manado Diving Competition Ticket',750000.00,true,false),
-- 	 (12,300,180,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Denpasar Art Exhibition Ticket',100000.00,true,false),
-- 	 (13,150,50,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Solo Wayang Show Ticket',50000.00,true,false),
-- 	 (14,500,250,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Balikpapan Eco Run Ticket',200000.00,true,false),
-- 	 (15,350,280,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Malang Coffee Festival Ticket',70000.00,true,false),
-- 	 (16,400,300,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Bandung Art Exhibition Ticket',120000.00,true,false),
-- 	 (17,450,400,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Surabaya Tech Expo Ticket',250000.00,true,false),
-- 	 (18,300,150,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Bali Cultural Festival Ticket',180000.00,true,false),
-- 	 (19,500,350,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Jakarta Fashion Show Ticket',350000.00,true,false),
-- 	 (20,300,180,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Yogyakarta Music Concert Ticket',150000.00,true,false);
-- INSERT INTO public.ticket (event_id,available_seat,sold_seat,created_at,updated_at,deleted_at,"name",price,is_released,is_closed) VALUES
-- 	 (21,200,70,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Medan Photography Exhibition Ticket',100000.00,true,false),
-- 	 (22,400,200,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Makassar Dance Festival Ticket',130000.00,true,false),
-- 	 (23,300,160,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Semarang Food Festival Ticket',90000.00,true,false),
-- 	 (24,500,250,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Jakarta Tech Startup Conference Ticket',500000.00,true,false),
-- 	 (25,400,180,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Palembang Music Night Ticket',120000.00,true,false),
-- 	 (26,450,220,'2024-12-13 11:35:22.070438+07','2024-12-13 11:35:22.070438+07',NULL,'Manado Cultural Show Ticket',140000.00,true,false);
