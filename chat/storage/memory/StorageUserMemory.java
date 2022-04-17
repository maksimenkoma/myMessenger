package by.it_academy.jd2.m_jd2_88_22.chat.storage.memory;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component

public class StorageUserMemory implements IUserStorage {


    private List<User> users = new ArrayList<>();
    private StorageAuditMemory storageAuditMemory;

    public StorageUserMemory(StorageAuditMemory storageAuditMemory) {
        this.storageAuditMemory = storageAuditMemory;
    }

    @Override
    public User checkUser(String login, String password) {

        User checkLogin = null;

        for (User user : users) {

            if (user.getLogin().equals(login)) {

                if (user.getPassword().equals(password)) {

                    checkLogin = user;
                }
            }
        }
        return checkLogin;
    }

    @Override
    public void saveUser(User user) {

        storageAuditMemory.saveAudit(new Audit("Регистрация", user, LocalDateTime.now()));
        this.users.add(user);
    }


    @Override
    public User getUser(User userRaw) {

        User checkUser = userRaw;

        for (User user : users) {

            if (user.getLogin().equals(userRaw.getLogin()) ||
                (userRaw.getLogin().equals("") || userRaw.getPassword().equals("") || userRaw.getFirstName().equals("")
                 || userRaw.getLastName().equals("") || userRaw.getDateBirth().equals(""))) {

                checkUser = null;

            }
        }
        return checkUser;
    }


    public List<User> getListUser(){

        return this.users;
    }
}
