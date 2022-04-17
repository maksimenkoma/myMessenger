package by.it_academy.jd2.m_jd2_88_22.chat.view.api;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import org.springframework.stereotype.Service;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public interface IMessageService {


    void sendMessage(Message message, User userActive);

    List<Message> getHistoryMessage(User userActive);

    List<Message> getHistoryMessage(User userActive, Pageable pageable);





}
