package br.com.fatec.service;

import br.com.fatec.domain.Garden;
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

    @Autowired
    private GardenService gardenService;

    @Transactional
    public void newUser(User user) {

        Garden gardenReference = gardenService.getGardenById(user.getGarden().getGardenId());
        user.setGarden(gardenReference);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserDateCreated(new Date());

        entityManager.persist(user);
    }

    @Transactional
    public void updateUser(User userUpdated) {

        User userOrigin = getUserById(userUpdated.getUserId());
        Garden gardenReference = gardenService.getGardenById(userUpdated.getGarden().getGardenId());

        userOrigin.setGarden(gardenReference);
        userOrigin.setUserName(userUpdated.getUserName());
        userOrigin.setUserLogin(userUpdated.getUserLogin());
        userOrigin.setUserMail(userUpdated.getUserMail());

        userOrigin.setUserDateUpdated(new Date());

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
