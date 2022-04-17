package by.it_academy.jd2.m_jd2_88_22.chat.storage.db.mapper;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = User.Builder.builder().
                setLogin(rs.getString("login")).
                setPassword(rs.getString("password")).
                setFirstName(rs.getString("firstName")).
                setLastName(rs.getString("lastName")).
                setMiddleName(rs.getString("middleName")).
                setDateBirth(LocalDate.parse(rs.getString("dateBirth"))).build();

        return user;
    }


}
