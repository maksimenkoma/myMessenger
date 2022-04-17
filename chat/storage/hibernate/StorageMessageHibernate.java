package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.MessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateDBInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class StorageMessageHibernate implements IMessageStorage {


    private HibernateDBInitializer hb;

    public StorageMessageHibernate(HibernateDBInitializer hb) {

        this.hb = hb;
    }

    @Autowired
    public void setHb(HibernateDBInitializer hb) {
        this.hb = hb;
    }

    @Override
    public void saveMessage(Message message, User activeUser) {

        MessageHibernate messageHibernate = messageHibernateChangeMessage(message);
        messageHibernate.setFk_message_from(activeUser.getLogin());
        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(messageHibernate);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Message> getHistoryMessage(User userActive) {
        return getHistoryMessage(userActive,null);
    }

    @Override
    public List<Message> getHistoryMessage(User userActive, Pageable pageable) {
        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageHibernate> query = cb.createQuery(MessageHibernate.class);
        Root<MessageHibernate> from = query.from(MessageHibernate.class);
        query.select(from).where(from.get("recipient").in(userActive.getLogin()));

        TypedQuery<MessageHibernate> qr = entityManager.createQuery(query);

        if (pageable != null && pageable.getPage() > 0) {
            qr.setFirstResult((pageable.getPage() - 1));

        }

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                qr.setMaxResults(pageable.getSize());
            }
        }
        List<MessageHibernate> resultList = qr.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        List<Message> messages = new ArrayList<>(resultList.size());

        for (MessageHibernate messageHibernate : resultList) {

            messages.add(messageChangeMessageHibernate(messageHibernate));
        }

        return messages;
    }


    public Message messageChangeMessageHibernate (MessageHibernate messageHibernate){

        Message message = new Message();
        message.setRecipient(messageHibernate.getFk_message_from());
        message.setMessage(messageHibernate.getMessages());
        message.setDateTime(messageHibernate.getDateTime());
        return message;
    }

    public MessageHibernate messageHibernateChangeMessage (Message message){

        MessageHibernate messageHibernate = new MessageHibernate();
        messageHibernate.setRecipient(message.getRecipient());
        messageHibernate.setMessages(message.getMessage());
        messageHibernate.setDateTime(message.getDateTime());
        return messageHibernate;
    }


}
