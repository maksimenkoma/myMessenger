package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.List;


@Controller
@RequestMapping("/chats")
public class ChatsController {

    private IMessageService messageService;


    public ChatsController(IMessageService messageService) {

        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@SessionAttribute(name = "user", required = false) User user, Model model) {

        if (user == null) {
            throw new SecurityException("Ошибка безопасности");
        }

        List<Message> messages = this.messageService.getHistoryMessage(user);

        model.addAttribute("history", messages);

        return "chats";
    }
}