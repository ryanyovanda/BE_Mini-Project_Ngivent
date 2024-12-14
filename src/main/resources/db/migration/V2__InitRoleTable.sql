CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL REFERENCES users(user_id),
    role_id INTEGER NOT NULL REFERENCES roles(role_id),
    assigned_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

-- Seed data for roles table
INSERT INTO
    roles (name, created_at, updated_at)
VALUES
    ('ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (
        'ORGANIZER',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );

-- Seed data for users table
INSERT INTO users (user_id,email, password, pin, is_onboarding_finished, referral_code, created_at, updated_at)
 VALUES
 (1,'organizer1@example.com', 'password1', '1234', TRUE, 'ORG123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (2,'organizer2@example.com', 'password2', '5678', TRUE, 'ORG456', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (3,'organizer3@example.com', 'password3', '9012', TRUE, 'ORG789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (4,'user1@example.com', 'password4', '3456', TRUE, 'USR123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (5,'user2@example.com', 'password5', '7890', TRUE, 'USR456', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (6,'user3@example.com', 'password6', '1122', TRUE, 'USR789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Seed data for user_roles table
INSERT INTO
    user_roles (user_id, role_id, assigned_at)
VALUES
    (
        (SELECT user_id FROM users WHERE email = 'organizer1@example.com'),
        (SELECT role_id FROM roles WHERE name = 'ORGANIZER'),
        CURRENT_TIMESTAMP
    ),
    (
        (SELECT user_id FROM users WHERE email = 'organizer2@example.com'),
        (SELECT role_id FROM roles WHERE name = 'ORGANIZER'),
        CURRENT_TIMESTAMP
    ),
    (
        (SELECT user_id FROM users WHERE email = 'organizer3@example.com'),
        (SELECT role_id FROM roles WHERE name = 'ORGANIZER'),
        CURRENT_TIMESTAMP
    ),
    (
        (SELECT user_id FROM users WHERE email = 'user1@example.com'),
        (SELECT role_id FROM roles WHERE name = 'USER'),
        CURRENT_TIMESTAMP
    ),
    (
        (SELECT user_id FROM users WHERE email = 'user2@example.com'),
        (SELECT role_id FROM roles WHERE name = 'USER'),
        CURRENT_TIMESTAMP
    ),
    (
        (SELECT user_id FROM users WHERE email = 'user3@example.com'),
        (SELECT role_id FROM roles WHERE name = 'USER'),
        CURRENT_TIMESTAMP
    );

