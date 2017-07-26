package br.com.fatec.service;

import br.com.fatec.domain.Plant;
import br.com.fatec.domain.enuns.PlantStatus;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static br.com.fatec.domain.QPlant.plant;

@Service
public class PlantService {


    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void newPlant(Plant plant) {

        plant.setPlantStatus(PlantStatus.ACTIVE);
        plant.setPlantDateCreated(new Date());

        entityManager.persist(plant);
    }

    @Transactional
    public void updatePlant(Plant plantUpdated) {

        Plant plantOrigin = getPlantById(plantUpdated.getPlantId());

        plantOrigin.setPlantName(plantUpdated.getPlantName());
        plantOrigin.setPlantDescription(plantUpdated.getPlantDescription());
        plantOrigin.setPlantHowToPlant(plantUpdated.getPlantHowToPlant());
        plantOrigin.setPlantWeekPeriodicityWater(plantUpdated.getPlantWeekPeriodicityWater());
        plantOrigin.setPlantWeekPeriodicityCare(plantUpdated.getPlantWeekPeriodicityCare());

        plantOrigin.setPlantDateUpdated(new Date());

        entityManager.merge(plantOrigin);

    }

    @Transactional
    public void deletePlantById(Long idPlant) {

        Plant plant = getPlantById(idPlant);
        plant.setPlantStatus(PlantStatus.DISABLED);

        entityManager.merge(plant);
    }

    public List<Plant> getAllPlants() {
        return new JPAQuery(entityManager)
                .from(plant)
                .where(plant.plantStatus.eq(PlantStatus.ACTIVE))
                .list(plant);
    }

    public Plant getPlantById(Long idPlant) {
        return entityManager.find(Plant.class, idPlant);
    }
}

