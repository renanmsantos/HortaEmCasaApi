package br.com.fatec.controller;

import br.com.fatec.domain.Garden;
import br.com.fatec.domain.Plant;
import br.com.fatec.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    PlantService plantService;

    @RequestMapping(method= RequestMethod.GET, value="/all")
    public List<Plant> getAllPlants(){
        return plantService.getAllPlants();
    }

    @RequestMapping(method=RequestMethod.GET, value="/{plantId}")
    public Plant getGardenById( @PathVariable Long plantId ){
        Plant plant = plantService.getPlantById(plantId);
        return plant;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Plant newPlant(@RequestBody @Valid Plant plant){
        plantService.newPlant(plant);
        return plant;
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{plantId}")
    public Plant updatePlant(@PathVariable Long plantId, @RequestBody @Valid Plant plant ){
        plant.setPlantId(plantId);
        plantService.updatePlant(plant);
        return plant;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{plantId}")
    public void delelePlantById( @PathVariable Long plantId ){
        plantService.deletePlantById(plantId);
    }

}
