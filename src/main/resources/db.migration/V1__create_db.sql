DROP TABLE IF EXISTS note;

CREATE TABLE IF NOT EXISTS note (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(200) NOT NULL CHECK (LENGTH(title) > 1),
    content VARCHAR(2000) NOT NULL CHECK (LENGTH(content) > 1)
);