package by.it_academy.jd2.m_jd2_88_22.chat.storage.db.mapper;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuditMapper implements RowMapper<Audit> {
    @Override
    public Audit mapRow(ResultSet rs, int rowNum) throws SQLException {

        Audit audit = new Audit();

        audit.setId(rs.getLong("id"));
        audit.setDt_create(rs.getObject("dt_create", LocalDateTime.class));
        audit.setText(rs.getString("text"));

        audit.setAuthor(User.Builder.builder()
                .setLogin(rs.getString("author"))
                .setFirstName(rs.getString("firstName"))
                .setLastName(rs.getString("lastName"))
                .setMiddleName(rs.getString("middleName"))
                .setDateBirth(rs.getObject("dateBirth", LocalDate.class))
                .build());

        return audit;
    }
}
