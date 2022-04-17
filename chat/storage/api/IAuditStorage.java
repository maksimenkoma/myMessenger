package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IAuditStorage {

    void saveAudit(Audit audit);

    List<Audit> readAudit();

    List<Audit> readAudit(Pageable pageable);

}
