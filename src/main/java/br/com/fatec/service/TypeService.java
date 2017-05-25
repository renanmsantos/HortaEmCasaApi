package br.com.fatec.service;

import br.com.fatec.domain.Plant;
import br.com.fatec.domain.Type;
import br.com.fatec.domain.enuns.TypeStatus;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.fatec.domain.QType.type;


@Service
public class TypeService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlantService plantService;

    @Transactional
    public void newType(Type type) {

        List<Plant> listReceived = type.getPlants();
        List<Plant> newList = new ArrayList<>();
        for (Plant item : listReceived){
            newList.add(plantService.getPlantById(item.getPlantId()));
        }
        type.setPlants(newList);

        type.setTypeStatus(TypeStatus.ACTIVE);
        type.setTypeDateCreated(new Date());

        entityManager.persist(type);
    }

    @Transactional
    public void updateType(Type typeUpdated) {

        Type typeOrigin = getTypeById(typeUpdated.getTypeId());

        List<Plant> listReceived = typeUpdated.getPlants();
        List<Plant> newList = new ArrayList<>();
        for (Plant item : listReceived){
            newList.add(plantService.getPlantById(item.getPlantId()));
        }
        typeOrigin.setPlants(newList);

        typeOrigin.setTypeName(typeUpdated.getTypeName());
        typeOrigin.setTypeDescription(typeUpdated.getTypeDescription());
        typeOrigin.setTypeWidth(typeUpdated.getTypeWidth());
        typeOrigin.setTypeHeight(typeUpdated.getTypeHeight());
        typeOrigin.setTypeLength(typeUpdated.getTypeLength());
        typeOrigin.setTypeHowToDo(typeUpdated.getTypeHowToDo());

        typeOrigin.setTypeDateUpdated(new Date());

        entityManager.merge(typeOrigin);

    }

    @Transactional
    public void deleteTypeById(Long idType) {

        Type type = getTypeById(idType);
        type.setTypeStatus(TypeStatus.DISABLED);

        entityManager.merge(type);
    }

    public List<Type> getAllTypes() {
        return new JPAQuery(entityManager)
                .from(type)
                .where(type.typeStatus.eq(TypeStatus.ACTIVE))
                .orderBy(type.typeName.asc())
                .list(type);
    }

    public Type getTypeById(Long idType) {
        return entityManager.find(Type.class, idType);
    }
}
