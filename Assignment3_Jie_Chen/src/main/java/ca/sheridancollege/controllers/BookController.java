package ca.sheridancollege.controllers;

import ca.sheridancollege.beans.Book;
import ca.sheridancollege.beans.User;
import ca.sheridancollege.beans.Review;
import ca.sheridancollege.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private DatabaseAccess da;

    @GetMapping("/books")
    public String viewBooks(Model model) {
        List<Book> books = da.findAllBooks();
        model.addAttribute("books", books);
        return "view-books";
    }

    @GetMapping("/book/{id}")
    public String viewBookDetails(@PathVariable Long id, Model model) {
        Book book = da.findBookById(id);
        model.addAttribute("book", book);
        return "view-book";
    }

    @GetMapping("/add-book")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute Book book) {
        da.addBook(book);
        return "redirect:/";
    }

    @GetMapping("/book/{id}/add-review")
    public String addReviewForm(@PathVariable Long id, Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("bookId", id);
        return "user/add-review";
    }

    @PostMapping("/book/{id}/submit-review")
    public String submitReview(@PathVariable Long id, @RequestParam String content, Principal principal) {
        Review review = new Review();
        review.setContent(content);

        Book book = da.findBookById(id);
        review.setBook(book);

        User user = da.findUserAccount(principal.getName());
        Long userId = user != null ? user.getUserId() : null;

        if (userId != null) {
            da.addReview(review, userId);
        }

        return "redirect:/book/" + id;
    }
}
