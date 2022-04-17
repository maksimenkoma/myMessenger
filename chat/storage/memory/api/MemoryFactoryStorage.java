package by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.*;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryFactoryStorage implements IFactoryStorage {



    private IUserStorage userStorage;
    private IMessageStorage messageStorage;
    private IAuditStorage auditStorage;

    public MemoryFactoryStorage(IUserStorage storageUserMemory,IMessageStorage storageMessageMemory,IAuditStorage storageAuditMemory) {
        this.userStorage= storageUserMemory;
        this.messageStorage= storageMessageMemory;
        this.auditStorage = storageAuditMemory;

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
        return StorageType.MEMORY.equals(storageType);
    }


}
