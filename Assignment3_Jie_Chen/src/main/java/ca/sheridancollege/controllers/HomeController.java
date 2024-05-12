package ca.sheridancollege.controllers;

import ca.sheridancollege.database.DatabaseAccess;
import ca.sheridancollege.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private DatabaseAccess da;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", da.findAllBooks());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Display the registration form
    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    // Process the registration form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        User newUser = new User();
        newUser.setEmail(username);
        newUser.setEncryptedPassword(passwordEncoder.encode(password));
        newUser.setEnabled(true);

        da.addUser(newUser);

        return "redirect:/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "permission-denied";
    }
}
