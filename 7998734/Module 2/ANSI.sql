-- =========================================================================
-- DATABASE SETUP 
-- =========================================================================
CREATE DATABASE IF NOT EXISTS community_event_portal;
USE community_event_portal;

-- -------------------------------------------------------------------------
-- TABLE CREATION
-- -------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    city VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    city VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status ENUM('upcoming', 'completed', 'cancelled') NOT NULL,
    organizer_id INT,
    FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
);

CREATE TABLE IF NOT EXISTS Sessions (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    title VARCHAR(150) NOT NULL,
    speaker_name VARCHAR(100) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE IF NOT EXISTS Registrations (
    registration_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    registration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE IF NOT EXISTS Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comments TEXT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE IF NOT EXISTS Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    resource_name VARCHAR(150) NOT NULL,
    resource_type ENUM('pdf', 'image', 'link') NOT NULL,
    url VARCHAR(255) NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

-- -------------------------------------------------------------------------
-- SAMPLE DATA HYDRATION (SAMPLE RECORDS)
-- -------------------------------------------------------------------------
INSERT IGNORE INTO Users (user_id, full_name, email, city, registration_date) VALUES 
(1, 'Alice Johnson', 'alice@example.com', 'New York', '2026-01-15'),
(2, 'Bob Smith', 'bob@example.com', 'Los Angeles', '2026-02-20'),
(3, 'Charlie Brown', 'charlie@example.com', 'New York', '2026-05-10'),
(4, 'Diana Prince', 'diana@example.com', 'Chicago', '2026-05-28'),
(5, 'Evan Wright', 'evan@example.com', 'Houston', '2026-05-30');

INSERT IGNORE INTO Events (event_id, title, description, city, start_date, end_date, status, organizer_id) VALUES 
(1, 'Tech Innovators Meetup', 'A meetup for local tech enthusiasts.', 'New York', '2026-07-10 10:00:00', '2026-07-10 16:00:00', 'upcoming', 1),
(2, 'AI & ML Conference', 'Deep dive into next-gen artificial intelligence.', 'Los Angeles', '2026-05-15 09:00:00', '2026-05-17 17:00:00', 'completed', 2),
(3, 'Cloud Computing Bootcamp', 'Hands-on architectural cloud patterns training.', 'Chicago', '2026-08-22 09:00:00', '2026-08-23 18:00:00', 'upcoming', 1),
(4, 'Cybersecurity Summit', 'Evaluating critical web application risk threats.', 'Houston', '2026-09-05 13:00:00', '2026-09-05 17:00:00', 'cancelled', 4);

INSERT IGNORE INTO Sessions (session_id, event_id, title, speaker_name, start_time, end_time) VALUES 
(1, 1, 'Introduction to SQL', 'John Doe', '2026-07-10 10:30:00', '2026-07-10 11:30:00'),
(2, 1, 'Advanced Indexing Patterns', 'Jane Doe', '2026-07-10 13:00:00', '2026-07-10 14:30:00'),
(3, 2, 'Neural Network Optimizations', 'Dr. Alan Turing', '2026-05-15 10:00:00', '2026-05-15 12:00:00'),
(4, 2, 'Generative Models in Practice', 'Dr. Alan Turing', '2026-05-16 11:00:00', '2026-05-16 13:00:00'),
(5, 3, 'AWS Foundations Masterclass', 'Sarah Jenkins', '2026-08-22 10:00:00', '2026-08-22 11:30:00');

INSERT IGNORE INTO Registrations (registration_id, user_id, event_id, registration_date) VALUES 
(1, 1, 1, '2026-06-01'),
(2, 3, 1, '2026-06-01'),
(3, 2, 2, '2026-04-10'),
(4, 4, 3, '2026-05-29'),
(5, 1, 3, '2026-05-29');

INSERT IGNORE INTO Feedback (feedback_id, user_id, event_id, rating, comments, submitted_at) VALUES 
(1, 2, 2, 5, 'Absolutely brilliant technical breakdowns!', '2026-05-18 10:00:00'),
(2, 1, 1, 2, 'The acoustic balance in the room layout was poor.', '2026-07-11 09:30:00'),
(3, 3, 2, 4, 'Solid introductory material covered.', '2026-05-18 14:00:00');

INSERT IGNORE INTO Resources (resource_id, event_id, resource_name, resource_type, url) VALUES 
(1, 1, 'SQL Cheat Sheet Workbook', 'pdf', 'http://portal.com/res/sql.pdf'),
(2, 2, 'Deep Learning Slides Bundle', 'pdf', 'http://portal.com/res/dl_slides.pdf'),
(3, 2, 'Conference Review Gallery', 'image', 'http://portal.com/res/gallery.png');


-- =========================================================================
-- EXERCISE 1: User Upcoming Events
-- =========================================================================
SELECT u.user_id, u.full_name, e.title AS event_title, e.city, e.start_date
FROM Registrations r
JOIN Users u ON r.user_id = u.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming' AND e.city = u.city
ORDER BY e.start_date ASC;


-- =========================================================================
-- EXERCISE 2: Top Rated Events
-- =========================================================================
SELECT e.event_id, e.title, ROUND(AVG(f.rating), 2) AS average_rating
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY average_rating DESC;


-- =========================================================================
-- EXERCISE 3: Inactive Users
-- =========================================================================
SELECT user_id, full_name, email, registration_date
FROM Users
WHERE user_id NOT IN (
    SELECT DISTINCT user_id
    FROM Registrations
    WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)
);


-- =========================================================================
-- EXERCISE 4: Peak Session Hours
-- =========================================================================
SELECT event_id, COUNT(session_id) AS sessions_in_peak_hours
FROM Sessions
WHERE TIME(start_time) >= '10:00:00' AND TIME(end_time) <= '12:00:00'
GROUP BY event_id;


-- =========================================================================
-- EXERCISE 5: Most Active Cities
-- =========================================================================
SELECT e.city, COUNT(DISTINCT r.user_id) AS unique_user_registrations
FROM Registrations r
JOIN Events e ON r.event_id = e.event_id
GROUP BY e.city
ORDER BY unique_user_registrations DESC
LIMIT 5;


-- =========================================================================
-- EXERCISE 6: Event Resource Summary
-- =========================================================================
SELECT e.event_id, e.title,
       SUM(CASE WHEN r.resource_type = 'pdf' THEN 1 ELSE 0 END) AS total_pdfs,
       SUM(CASE WHEN r.resource_type = 'image' THEN 1 ELSE 0 END) AS total_images,
       SUM(CASE WHEN r.resource_type = 'link' THEN 1 ELSE 0 END) AS total_links
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;


-- =========================================================================
-- EXERCISE 7: Low Feedback Alerts
-- =========================================================================
SELECT u.full_name, e.title AS event_name, f.rating, f.comments
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;


-- =========================================================================
-- EXERCISE 8: Sessions per Upcoming Event
-- =========================================================================
SELECT e.event_id, e.title, COUNT(s.session_id) AS total_scheduled_sessions
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title;


-- =========================================================================
-- EXERCISE 9: Organizer Event Summary
-- =========================================================================
SELECT u.user_id AS organizer_id, u.full_name AS organizer_name, e.status, COUNT(e.event_id) AS total_events
FROM Events e
JOIN Users u ON e.organizer_id = u.user_id
GROUP BY u.user_id, u.full_name, e.status
ORDER BY organizer_name, e.status;


-- =========================================================================
-- EXERCISE 10: Feedback Gap
-- =========================================================================
SELECT DISTINCT event_id, title
FROM Events
WHERE event_id IN (SELECT event_id FROM Registrations)
  AND event_id NOT IN (SELECT event_id FROM Feedback);


-- =========================================================================
-- EXERCISE 11: Daily New User Count
-- =========================================================================
SELECT registration_date, COUNT(user_id) AS newly_registered_users
FROM Users
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
GROUP BY registration_date
ORDER BY registration_date DESC;


-- =========================================================================
-- EXERCISE 12: Event with Maximum Sessions
-- =========================================================================
SELECT event_id, COUNT(session_id) AS session_count
FROM Sessions
GROUP BY event_id
HAVING COUNT(session_id) = (
    SELECT COUNT(session_id)
    FROM Sessions
    GROUP BY event_id
    ORDER BY COUNT(session_id) DESC
    LIMIT 1
);


-- =========================================================================
-- EXERCISE 13: Average Rating per City
-- =========================================================================
SELECT e.city, ROUND(AVG(f.rating), 2) AS city_average_rating
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY e.city;


-- =========================================================================
-- EXERCISE 14: Most Registered Events
-- =========================================================================
SELECT e.event_id, e.title, COUNT(r.registration_id) AS registration_count
FROM Registrations r
JOIN Events e ON r.event_id = e.event_id
GROUP BY e.event_id, e.title
ORDER BY registration_count DESC
LIMIT 3;


-- =========================================================================
-- EXERCISE 15: Event Session Time Conflict
-- =========================================================================
SELECT s1.event_id,
       s1.title AS first_session, s1.start_time AS start_time_A, s1.end_time AS end_time_A,
       s2.title AS second_session, s2.start_time AS start_time_B, s2.end_time AS end_time_B
FROM Sessions s1
JOIN Sessions s2 ON s1.event_id = s2.event_id AND s1.session_id < s2.session_id
WHERE s1.start_time < s2.end_time AND s2.start_time < s1.end_time;


-- =========================================================================
-- EXERCISE 16: Unregistered Active Users
-- =========================================================================
SELECT user_id, full_name, registration_date
FROM Users
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
  AND user_id NOT IN (SELECT DISTINCT user_id FROM Registrations);


-- =========================================================================
-- EXERCISE 17: Multi-Session Speakers
-- =========================================================================
SELECT speaker_name, COUNT(session_id) AS assigned_sessions_count
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1;


-- =========================================================================
-- EXERCISE 18: Resource Availability Check
-- =========================================================================
SELECT event_id, title, status
FROM Events
WHERE event_id NOT IN (SELECT DISTINCT event_id FROM Resources);


-- =========================================================================
-- EXERCISE 19: Completed Events with Feedback Summary
-- =========================================================================
SELECT e.event_id, e.title,
       COUNT(DISTINCT r.registration_id) AS total_attendee_registrations,
       ROUND(AVG(f.rating), 2) AS calculated_average_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title;


-- =========================================================================
-- EXERCISE 20: User Engagement Index
-- =========================================================================
SELECT u.user_id, u.full_name,
       (SELECT COUNT(DISTINCT r.event_id) FROM Registrations r WHERE r.user_id = u.user_id) AS unique_events_registered,
       (SELECT COUNT(f.feedback_id) FROM Feedback f WHERE f.user_id = u.user_id) AS feedback_reviews_submitted
FROM Users u;


-- =========================================================================
-- EXERCISE 21: Top Feedback Providers
-- =========================================================================
SELECT u.user_id, u.full_name, COUNT(f.feedback_id) AS submitted_feedback_count
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
GROUP BY u.user_id, u.full_name
ORDER BY submitted_feedback_count DESC
LIMIT 5;


-- =========================================================================
-- EXERCISE 22: Duplicate Registrations Check
-- =========================================================================
SELECT user_id, event_id, COUNT(registration_id) AS user_registration_entry_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(registration_id) > 1;


-- =========================================================================
-- EXERCISE 23: Registration Trends
-- =========================================================================
SELECT DATE_FORMAT(registration_date, '%Y-%m') AS transaction_calendar_month,
       COUNT(registration_id) AS volume_metrics_count
FROM Registrations
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY DATE_FORMAT(registration_date, '%Y-%m')
ORDER BY transaction_calendar_month DESC;


-- =========================================================================
-- EXERCISE 24: Average Session Duration per Event
-- =========================================================================
SELECT e.event_id, e.title,
       ROUND(AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)), 1) AS computed_average_minutes
FROM Sessions s
JOIN Events e ON s.event_id = e.event_id
GROUP BY e.event_id, e.title;


-- =========================================================================
-- EXERCISE 25: Events Without Sessions
-- =========================================================================
SELECT event_id, title, city, status
FROM Events
WHERE event_id NOT IN (SELECT DISTINCT IFNULL(event_id, 0) FROM Sessions);