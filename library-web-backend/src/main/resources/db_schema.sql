-- Создание таблицы книг
CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  publish_year INT NOT NULL
);

-- Создание таблицы читателей
CREATE TABLE readers (
  id SERIAL PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  gender VARCHAR(255) NOT NULL,
  age INT NOT NULL
);

-- Создание таблицы выдачи/сдачи книг
CREATE TABLE loans (
  id SERIAL PRIMARY KEY,
  loan_date DATE NOT NULL,
  return_date DATE,
  reader_id INT NOT NULL,
  book_id INT NOT NULL,
  FOREIGN KEY (reader_id) REFERENCES readers(id),
  FOREIGN KEY (book_id) REFERENCES books(id)
);


-- Добавление книги
INSERT INTO books (title, author, publish_year) 
VALUES 
  ('Война и мир', 'Лев Толстой', 1869),
  ('Преступление и наказание', 'Фёдор Достоевский', 1866),
  ('Мастер и Маргарита', 'Михаил Булгаков', 1967);

-- Добавление читателей
INSERT INTO readers (full_name, gender, age) 
VALUES 
  ('Иван Иванов', 'Мужской', 25),
  ('Мария Петрова', 'Женский', 30),
  ('Алексей Сидоров', 'Мужской', 28);

-- Добавление займов
INSERT INTO loans (loan_date, return_date, reader_id, book_id) 
VALUES 
  ('2023-04-01', '2023-04-15', 1, 1), -- Иван Иванов взял "Войну и мир"
  ('2023-04-05', '2023-04-20', 2, 2), -- Мария Петрова взяла "Преступление и наказание"
  ('2023-04-10', NULL, 3, 3); -- Алексей Сидоров взял "Мастера и Маргариту" (не вернул)


