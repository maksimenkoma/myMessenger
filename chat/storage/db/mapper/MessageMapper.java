package by.it_academy.jd2.m_jd2_88_22.chat.storage.db.mapper;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {

        Message message = new Message();

        message.setRecipient(rs.getString("fk_message_from"));
        message.setMessage(rs.getString("messages"));
        message.setDateTime(rs.getObject("datetime",LocalDateTime.class));

        return message;
    }
}
