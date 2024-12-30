import requests
import psycopg2
from psycopg2 import sql

DB_NAME = "ms-books"
DB_USER = "postgres"
DB_PASSWORD = "yassine"
DB_HOST = "localhost"
DB_PORT = "5433"

# Archive API details
BASE_URL = "https://archive.org/advancedsearch.php"
PARAMS = {
    "q": "collection:books",
    "fl[]": ["identifier", "title", "creator", "date", "subject", "isbn"],
    "output": "json",
    "rows": 100
}
ITEMS_PER_PAGE = 100

# Function to fetch books from the archive
def fetch_books(page):
    PARAMS["page"] = page
    response = requests.get(BASE_URL, params=PARAMS)
    response.raise_for_status()
    return response.json()

# Function to extract year from a date string
def extract_year(date_str):
    if date_str and isinstance(date_str, str):
        return date_str[:4]
    return None

# Function to insert books into the PostgreSQL database
def insert_books_and_themes(books):
    conn = psycopg2.connect(
        dbname=DB_NAME, user=DB_USER, password=DB_PASSWORD, host=DB_HOST, port=DB_PORT
    )
    cursor = conn.cursor()

    insert_book_query = sql.SQL(
        """
        INSERT INTO book (title, author, isbn, publication_year, image_url, is_available, is_deleted)
        VALUES (%s, %s, %s, %s, %s, %s, %s)
        RETURNING id_book
        """
    )

    insert_theme_query = sql.SQL(
        """
        INSERT INTO theme (name)
        VALUES (%s)
        RETURNING id_theme
        """
    )

    insert_book_theme_query = sql.SQL(
        """
        INSERT INTO book_theme (book_id, theme_id)
        VALUES (%s, %s)
        """
    )

    for book in books:
        cursor.execute(
            insert_book_query,
            (
                book.get("title"),
                book.get("creator"),
                book.get("isbn"),
                extract_year(book.get("publication_year")),
                book.get("image_url"),
                True,
                False,
            ),
        )
        book_id = cursor.fetchone()[0]

        # Insert themes and link to book
        themes = book.get("subjects", [])
        for theme in themes:
            if theme:  # Skip empty or None subjects
                cursor.execute(insert_theme_query, (theme,))
                theme_id = cursor.fetchone()

                if theme_id:  # If a new theme was inserted
                    theme_id = theme_id[0]
                else:  # Get existing theme id
                    cursor.execute("SELECT id_theme FROM theme WHERE name = %s", (theme,))
                    theme_id = cursor.fetchone()[0]

                # Insert into book_theme table
                cursor.execute(insert_book_theme_query, (book_id, theme_id))

    conn.commit()
    cursor.close()
    conn.close()

# Main script to fetch and save books
def main():
    page = 1
    while True:
        print(f"Fetching page {page}...")
        data = fetch_books(page)
        docs = data.get("response", {}).get("docs", [])

        if not docs:
            print("No more data to fetch.")
            break

        books = []
        for doc in docs:
            book = {
                "title": doc.get("title", "Unknown Title"),
                "creator": doc.get("creator", "Unknown Author"),
                "isbn": doc.get("isbn", [None])[0],  # Get the first ISBN if available
                "publication_year": doc.get("date"),
                "image_url": f"https://archive.org/services/img/{doc.get('identifier')}",
                "subjects": doc.get("subject", []) if isinstance(doc.get("subject"), list) else [doc.get("subject")]
            }
            books.append(book)

        insert_books_and_themes(books)
        print(f"Inserted {len(books)} books from page {page} into the database.")
        page += 1

if __name__ == "__main__":
    main()
