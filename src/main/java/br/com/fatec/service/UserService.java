package br.com.fatec.service;

import br.com.fatec.domain.User;
import br.com.fatec.domain.enuns.UserStatus;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static br.com.fatec.domain.QUser.user;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void newUser(User user) {

        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserDateCreated(new Date());

        entityManager.persist(user);
    }

    @Transactional
    public void updateUser(User userUpdated) {

        User userOrigin = getUserById(userUpdated.getUserId());

        userOrigin.setUserLogin(userUpdated.getUserLogin());
        userOrigin.setUserDateUpdated(new Date());
        userOrigin.setUserMail(userUpdated.getUserMail());

        entityManager.merge(userOrigin);

    }

    @Transactional
    public void deleteUserById(Long idUser) {

        User user = getUserById(idUser);
        user.setUserStatus(UserStatus.DISABLED);

        entityManager.merge(user);
    }

    public List<User> getAllUsers() {
        return new JPAQuery(entityManager)
                .from(user)
                .where(user.userStatus.eq(UserStatus.ACTIVE))
                .orderBy(user.userName.asc())
                .list(user);
    }

    public User getUserById(Long idUser) {
        return entityManager.find(User.class, idUser);
    }

}
