package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class StorageUserDB implements IUserStorage {

    private DataSource ds;

    private StorageAuditDB storageAuditDB;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StorageUserDB(DataSource dataSource,StorageAuditDB storageAuditDB) {

        this.ds = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.ds);
        this.storageAuditDB=storageAuditDB;
    }


    @Override
    public User checkUser(String login, String password) {

        String sql = "SELECT\n" +
                     "    *\n" +
                     "FROM\n" +
                     "    exam.user_table\n" +
                     "WHERE\n" +
                     "    login = :login AND password = :password ;";

        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);


        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());


        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения из базы");
        }
    }


    @Override
    public void saveUser(User user) {

        String sql = "INSERT INTO exam.user_table (login,password,firstName,lastName,middleName, dateBirth)" +
                     "VALUES (:login,:password,:firstName,:lastName,:middleName,:dateBirth); ";

        try {
            Map<String, Object> params = new HashMap<>();

            params.put("login", user.getLogin());
            params.put("password", user.getPassword());
            params.put("firstName", user.getFirstName());
            params.put("lastName", user.getLastName());
            params.put("middleName", user.getMiddleName());
            params.put("dateBirth", user.getDateBirth());


            namedParameterJdbcTemplate.update(sql, params);
            storageAuditDB.saveAudit(new Audit("Регистрация", user, LocalDateTime.now()));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выаолнения SQL");
        }
    }

    @Override
    public User getUser(User userRaw) {

        String sql = "SELECT\n" +
                     "    *\n" +
                     "FROM\n" +
                     "    exam.user_table\n" +
                     "WHERE\n" +
                     "    login = :login ;";

        try {
            List<User> users = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("login", userRaw.getLogin()), new UserMapper());
            if (users.isEmpty()) {
                return null;

            } else return users.get(0);


        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения из базы");
        }

    }

}
