package by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.*;
import org.springframework.stereotype.Component;

@Component("dbFactoryStorage")
public class DBFactoryStorage implements IFactoryStorage {


    private IUserStorage userStorage;
    private IMessageStorage messageStorage;
    private IAuditStorage auditStorage;


    public DBFactoryStorage(IUserStorage storageUserDB,IMessageStorage storageMessageDB,IAuditStorage storageAuditDB) {

        this.userStorage= storageUserDB;
        this.messageStorage= storageMessageDB;
        this.auditStorage = storageAuditDB;

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
        return StorageType.SQL.equals(storageType);
    }

}


