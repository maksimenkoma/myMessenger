package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FactoryStorage {

    private StorageType selectType;
    private List<IFactoryStorage> list;


    public FactoryStorage(List<IFactoryStorage> list, String storageType) {

        this.list = list;
        this.selectType = StorageType.valueOf(storageType);
    }


    public List<IFactoryStorage> getList() {
        return list;
    }

    public IUserStorage getUserStorage() {

        return getFactoryStorage(selectType).getUserStorage();

    }


    public IMessageStorage getMessageStorage() {

        return getFactoryStorage(selectType).getMessageStorage();
    }


    public IAuditStorage getAuditStorage() {

        return getFactoryStorage(selectType).getAuditStorage();

    }


    private IFactoryStorage getFactoryStorage(StorageType selectType) {

        for (IFactoryStorage iFactoryStorage : list) {
            if (iFactoryStorage.isSupportedType(selectType)) {
                return iFactoryStorage;
            }
        }
        return null;
    }

}