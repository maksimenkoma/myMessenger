package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/signIn")
public class SignInController {


    private IUserService userService;

    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";

    @Autowired
    public SignInController(IUserService userService) {

        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "signIn";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@SessionAttribute(name = "user", required = false) User user,
                         @RequestParam(LOGIN) String login,
                         @RequestParam(PASSWORD) String password,
                         HttpSession session,
                         Model model) {

        User userActive = userService.checkLogin(login, password);


        if (userActive == null) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Логин или пароль неверен");
            return "signIn";
        } else {
            model.addAttribute("user", userActive);

            session.setAttribute("user", userActive);

        }



        String redirectUrl = "/message";

        return "redirect:" + redirectUrl;

    }
}
