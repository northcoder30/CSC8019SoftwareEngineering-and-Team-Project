-- V11__populate_ebook_dummy_data.sql

INSERT INTO ebooks
    (
        title,
        author,
        publisher,
        total_quantity,
        available_quantity,
        price,
        description,
        category_id
) VALUES
    ('The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 30, 25, 12.99, 'A tale of wealth and love in the 1920s.', 1),
    ('1984', 'George Orwell', 'Penguin Books', 20, 0, 9.99, 'A dystopian novel about surveillance.', 1),
    ('To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott', 25, 20, 15.50, 'A story of justice in the South.', 1),
    ('The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown', 10, 5, 11.25, 'A tale of teenage rebellion.', 1),
    ('Pride and Prejudice', 'Jane Austen', 'Penguin Classics', 35, 30, 8.99, 'A romance about love and society.', 1),
    ('The Hobbit', 'J.R.R. Tolkien', 'Houghton Mifflin', 40, 35, 14.99, 'Bilbo’s adventure in a fantasy world.', 1),
    ('The Alchemist', 'Paulo Coelho', 'HarperOne', 22, 0, 16.50, 'A journey to follow one’s dreams.', 1),
    ('The Da Vinci Code', 'Dan Brown', 'Doubleday', 30, 25, 13.49, 'A thriller involving art and secrets.', 1),
    ('Dune', 'Frank Herbert', 'Ace Books', 25, 20, 19.99, 'A sci-fi epic on a desert planet.', 1),
    ('The Martian', 'Andy Weir', 'Crown Publishing', 30, 25, 14.99, 'An astronaut’s survival on Mars.', 1),
    ('Atomic Habits', 'James Clear', 'Avery', 20, 15, 21.99, 'Strategies for building habits.', 2),
    ('Thinking, Fast and Slow', 'Daniel Kahneman', 'Farrar, Straus', 18, 12, 28.50, 'Insights into decision-making.', 2),
    ('Clean Code', 'Robert C. Martin', 'Prentice Hall', 8, 6, 45.00, 'Best practices for software.', 3),
    ('A Brief History of Time', 'Stephen Hawking', 'Bantam Books', 12, 8, 18.75, 'An intro to cosmology.', 3),
    ('Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'Harper', 15, 10, 24.99, 'The history of Homo sapiens.', 4),
    ('Guns, Germs, and Steel', 'Jared Diamond', 'W.W. Norton', 15, 10, 23.99, 'Why civilizations rise and fall.', 4),
    ('Becoming', 'Michelle Obama', 'Crown Publishing', 18, 12, 29.99, 'Memoir of the former First Lady.', 5),
    ('Educated', 'Tara Westover', 'Random House', 15, 10, 22.00, 'A memoir of self-education.', 5),
    ('The Wright Brothers', 'David McCullough', 'Simon & Schuster', 10, 0, 26.75, 'Biography of aviation pioneers.', 5),
    ('Steve Jobs', 'Walter Isaacson', 'Simon & Schuster', 12, 8, 27.50, 'Biography of Apple’s co-founder.', 5);