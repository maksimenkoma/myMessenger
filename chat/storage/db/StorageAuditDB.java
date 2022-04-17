package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.mapper.AuditMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StorageAuditDB implements IAuditStorage {

    private DataSource ds;


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StorageAuditDB(DataSource dataSource) {

        this.ds = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.ds);

    }



    @Override
    public void saveAudit(Audit audit) {

        if (audit == null) {
            throw new IllegalArgumentException("Аудит не может быть null");
        }

        String sql = "INSERT INTO exam.audit_users(text, author, dt_create)\n" +
                     "\tVALUES (:text, :author , :dt_create);";

        Map<String, Object> params = new HashMap<>();

        params.put("text", audit.getText());
        params.put("author", audit.getAuthor().getLogin());
        params.put("dt_create", audit.getDt_create());

        try {
            namedParameterJdbcTemplate.update(sql, params);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнение SQL", e);
        }
    }


    @Override
    public List<Audit> readAudit() {
        return readAudit(null);
    }


    @Override
    public List<Audit> readAudit(Pageable pageable) {
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

        List<Audit> data = new ArrayList<>();

        String sql = "SELECT audit_users.id,audit_users.dt_create,audit_users.text,\n" +
                     "user_table.login, user_table.firstName, user_table.lastName\n" +
                     "user_table.middleName, user_table.dateBirth\n" +
                     "FROM exam.audit_users\n" +
                     "LEFT JOIN exam.user_table ON audit_users.author=user_table.login;";

        if (limit != null) {
            sql += "\n LIMIT " + limit;
        }
        if (offset != null) {
            sql += "\n OFFSET " + offset;
        }
        sql += ";";

        try {
            return namedParameterJdbcTemplate.query(sql, new AuditMapper());


        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнение SQL", e);
        }
    }

}
