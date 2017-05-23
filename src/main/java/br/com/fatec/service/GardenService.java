package br.com.fatec.service;

import br.com.fatec.domain.Garden;
import br.com.fatec.domain.enuns.GardenStatus;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static br.com.fatec.domain.QGarden.garden;

@Service
public class GardenService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void newGarden(Garden garden) {

        garden.setGardenStatus(GardenStatus.ACTIVE);
        garden.setGardenDateCreated(new Date());

        entityManager.persist(garden);
    }

    @Transactional
    public void updateGarden(Garden gardenUpdated) {

        Garden gardenOrigin = getGardenById(gardenUpdated.getGardenId());

        gardenOrigin.setGardenWidth(gardenUpdated.getGardenWidth());
        gardenOrigin.setGardenHeight(gardenUpdated.getGardenHeight());
        gardenOrigin.setGardenLength(gardenUpdated.getGardenLength());

        /** TODO : Fazer o update da lista de de Types **/

        gardenOrigin.setGardenDateUpdated(new Date());

        entityManager.merge(gardenOrigin);

    }

    @Transactional
    public void deleteGardenById(Long idGarden) {

        Garden garden = getGardenById(idGarden);
        garden.setGardenStatus(GardenStatus.DISABLED);

        entityManager.merge(garden);
    }

    public List<Garden> getAllGardens() {
        return new JPAQuery(entityManager)
                .from(garden)
                .where(garden.gardenStatus.eq(GardenStatus.ACTIVE))
                .list(garden);
    }

    public Garden getGardenById(Long idGarden) {
        return entityManager.find(Garden.class, idGarden);
    }
}

