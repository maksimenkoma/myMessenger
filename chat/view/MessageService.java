package by.it_academy.jd2.m_jd2_88_22.chat.view;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class MessageService implements IMessageService {


    private IMessageStorage messageStorage;

    public MessageService(IMessageStorage messageStorage) {

        this.messageStorage = messageStorage;
    }


    @Override
    public void sendMessage(Message message, User userActive) {

        messageStorage.saveMessage(message, userActive);

    }

    @Override
    public List<Message> getHistoryMessage(User userActive) {

        return getHistoryMessage(userActive, null);
    }


    @Override
    public List<Message> getHistoryMessage(User userActive, Pageable pageable) {

        List<Message> messages = messageStorage.getHistoryMessage(userActive, pageable);


        return messages;
    }

}
