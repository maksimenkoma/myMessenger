package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.mapper.MessageMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StorageMessageDB implements IMessageStorage {


    private DataSource ds;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StorageMessageDB(DataSource dataSource) {

        this.ds = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.ds);
    }

    @Override
    public void saveMessage(Message message, User userActive) {


        String sql = "INSERT INTO exam.message_table (recipient, messages, dateTime, fk_message_from)" +
                     "VALUES (:recipient,:message,:dateTime,:fk_message_from)";

        Map<String, Object> params = new HashMap<>();
        params.put("recipient", message.getRecipient());
        params.put("message", message.getMessage());
        params.put("dateTime", Timestamp.valueOf(message.getDateTime()));
        params.put("fk_message_from", userActive.getLogin());

        try {
            namedParameterJdbcTemplate.update(sql, params);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка записи в базу");
        }
    }


    @Override
    public List<Message> getHistoryMessage(User userActive) {


        return getHistoryMessage(userActive, null);


    }


    @Override
    public List<Message> getHistoryMessage(User userActive, Pageable pageable) {
        Integer limit = null;
        Integer offset = null;

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                limit = pageable.getSize();
            }
            if (limit != null && pageable.getPage() > 0) {
                offset = (pageable.getPage() - 1) * limit;
            }
        }


        String sql = "SELECT\n" +
                     "    *\n" +
                     "FROM\n" +
                     "    exam.message_table\n" +
                     "WHERE\n" +
                     "    recipient = :login";


        if (limit != null) {

            sql += "\n LIMIT " + limit;
        }
        if (offset != null) {
            sql += "\n OFFSET " + offset;
        }
        sql += " ;";

        try {
            return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("login", userActive.getLogin()),
                    new MessageMapper());

        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

}
