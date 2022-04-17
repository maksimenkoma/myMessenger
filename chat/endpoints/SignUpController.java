package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IUserService;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;


@Controller
@RequestMapping("/signUp")

public class SignUpController {

    private IUserService userService;
    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String DATE = "date";


    public SignUpController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "signUp";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestParam(name = LOGIN) String login,
                         @RequestParam(name = PASSWORD) String password,
                         @RequestParam(name = FIRST_NAME) String firstName,
                         @RequestParam(name = LAST_NAME) String lastName,
                         @RequestParam(name = MIDDLE_NAME) String middleName,
                         @RequestParam(name = DATE) String date,
                         HttpSession session,
                         Model model) {

        User userRaw = User.Builder.builder().setLogin(login).setPassword(password).setFirstName(firstName).
                setLastName(lastName).setMiddleName(middleName).
                setDateBirth(LocalDate.parse(date)).build();

        if (userService.checkUsers(userRaw)) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Ошибка регистрации");
            return "signUp";


        } else userService.createUser(userRaw);

        model.addAttribute("user", userRaw);

        session.setAttribute("user", userRaw);

        String redirectUrl = "/message";

        return "redirect:" + redirectUrl;

    }
}