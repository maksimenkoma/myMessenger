package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/message")

public class MessageController {


    private IMessageService messageService;


    public MessageController(IMessageService messageService) {

        this.messageService = messageService;

    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "message";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String create(@SessionAttribute(name = "user", required = false) User user,
                         @RequestParam(name = "sender") String loginSender,
                         @RequestParam(name = "message") String message,
                         Model model) {

        try {
            this.messageService.sendMessage(new Message(loginSender, message, LocalDateTime.now()), user);
            model.addAttribute("success", true);

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", e.getMessage());
        }


        return "message";

    }
}
