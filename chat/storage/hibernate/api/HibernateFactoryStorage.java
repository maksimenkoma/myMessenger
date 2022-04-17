package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.*;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateFactoryStorage implements IFactoryStorage {

    private IUserStorage userStorage;
    private IMessageStorage messageStorage;
    private IAuditStorage auditStorage;


    public HibernateFactoryStorage(IUserStorage storageUserHibernate,IMessageStorage storageMessageHibernate,IAuditStorage storageAuditHibernate) {

        this.userStorage= storageUserHibernate;
        this.messageStorage= storageMessageHibernate;
        this.auditStorage = storageAuditHibernate;

    }

    @Override
    public IUserStorage getUserStorage() {
        return this.userStorage;
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return this.messageStorage;
    }

    @Override
    public IAuditStorage getAuditStorage() {
        return this.auditStorage;
    }

    @Override
    public boolean isSupportedType(StorageType storageType) {
        return StorageType.HIBERNATE.equals(storageType);
    }

}
