package ca.sheridancollege.database;

import ca.sheridancollege.beans.Book;
import ca.sheridancollege.beans.Review;
import ca.sheridancollege.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public User findUserAccount(String email) {
        String query = "SELECT * FROM sec_user WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        try {
            return jdbc.queryForObject(query, params, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            // Handle exception (e.g., user not found)
            return null;
        }
    }

    // Method to add a new book
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author) VALUES (:title, :author)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", book.getTitle());
        parameters.addValue("author", book.getAuthor());
        jdbc.update(query, parameters);
    }

    // Method to retrieve all books
    public List<Book> findAllBooks() {
        String query = "SELECT * FROM books";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Book.class));
    }

    // Method to find a book by ID
    public Book findBookById(Long id) {
        String bookQuery = "SELECT * FROM books WHERE bookId = :bookId";
        MapSqlParameterSource bookParams = new MapSqlParameterSource();
        bookParams.addValue("bookId", id);

        // Fetch the book details
        Book book = jdbc.queryForObject(bookQuery, bookParams, new BeanPropertyRowMapper<>(Book.class));

        // Check if the book is found before fetching reviews
        if (book != null) {
            String reviewQuery = "SELECT * FROM reviews WHERE bookId = :bookId";
            MapSqlParameterSource reviewParams = new MapSqlParameterSource();
            reviewParams.addValue("bookId", id);

            // Fetch the reviews for this book
            List<Review> reviews = jdbc.query(reviewQuery, reviewParams, new BeanPropertyRowMapper<>(Review.class));
            book.setReviews(reviews); // Set the reviews for this book
        }

        return book;
    }

    // Method to add a new review
    public void addReview(Review review, Long userId) {
        String query = "INSERT INTO reviews (content, bookId, userId) VALUES (:content, :bookId, :userId)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("content", review.getContent());
        parameters.addValue("bookId", review.getBook().getBookId());
        parameters.addValue("userId", userId);
        jdbc.update(query, parameters);
    }

    // Method to find reviews for a book
    public List<Review> findReviewsByBookId(Long bookId) {
        String query = "SELECT * FROM reviews WHERE bookId = :bookId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("bookId", bookId);
        return jdbc.query(query, parameters, new BeanPropertyRowMapper<>(Review.class));
    }

    public List<String> getRolesByUserId(Long userId) {
        String query = "SELECT roleName FROM user_role JOIN sec_role ON user_role.roleId = sec_role.roleId WHERE userId = :userId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userId", userId);
        return jdbc.query(query, parameters, (rs, rowNum) -> rs.getString("roleName"));
    }

    public void addUser(User user) {
        String query = "INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES (:email, :encryptedPassword, :enabled)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail());
        params.addValue("encryptedPassword", user.getEncryptedPassword());
        params.addValue("enabled", user.getEnabled());

        jdbc.update(query, params);
    }
}
