package art.deerborg.bank.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
