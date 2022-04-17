package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.UserHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateDBInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class StorageUserHibernate implements IUserStorage {

    private StorageAuditHibernate storageAuditHibernate;
    private HibernateDBInitializer hb;


    public StorageUserHibernate(HibernateDBInitializer hb,StorageAuditHibernate storageAuditHibernate) {

        this.hb = hb;
        this.storageAuditHibernate=storageAuditHibernate;
    }

    @Autowired
    public void setHb(HibernateDBInitializer hb) {
        this.hb = hb;
    }

    @Override
    public void saveUser(User user) {

        EntityManager entityManagerMain = hb.getManager();
        entityManagerMain.getTransaction().begin();

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(UserChangeUserHibernate(user));
        entityManager.getTransaction().commit();
        entityManager.close();
        storageAuditHibernate.saveAudit(new Audit("Регистрация", user, LocalDateTime.now()));

        entityManagerMain.getTransaction().commit();
        entityManagerMain.close();


    }

    @Override
    public User getUser(User user) {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserHibernate> query = cb.createQuery(UserHibernate.class);
        Root<UserHibernate> from = query.from(UserHibernate.class);
        query.select(from);
        query.where(from.get("login").in(user.getLogin()));

        List<UserHibernate> resultList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        if (resultList.isEmpty()) {
            user = null;

        }

        return user;
    }


    @Override
    public User checkUser(String login, String password) {

        User user = new User();
        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserHibernate> query = cb.createQuery(UserHibernate.class);
        Root<UserHibernate> from = query.from(UserHibernate.class);
        query.select(from);
        query.where(from.get("login").in(login), from.get("password").in(password));

        List<UserHibernate> resultList = entityManager.createQuery(query).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();


        for (UserHibernate userHibernate : resultList) {

            user = UserHibernateChangeUser(userHibernate);

        }

        return user;
    }


    public User UserHibernateChangeUser(UserHibernate userHibernate) {

        User user = new User();
        user.setLogin(userHibernate.getLogin());
        user.setPassword(userHibernate.getPassword());
        user.setFirstName(userHibernate.getFirstName());
        user.setLastName(userHibernate.getLastName());
        user.setMiddleName(userHibernate.getMiddleName());
        user.setDateBirth(userHibernate.getDateBirth());


        return user;
    }

    public UserHibernate UserChangeUserHibernate(User user) {

        UserHibernate userHibernate = new UserHibernate();

        userHibernate.setLogin(user.getLogin());
        userHibernate.setPassword(user.getPassword());
        userHibernate.setFirstName(user.getFirstName());
        userHibernate.setLastName(user.getLastName());
        userHibernate.setMiddleName(user.getMiddleName());
        userHibernate.setDateBirth(user.getDateBirth());

        return userHibernate;
    }


}
