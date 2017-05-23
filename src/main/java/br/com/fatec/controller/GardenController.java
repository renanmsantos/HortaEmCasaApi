package br.com.fatec.controller;

import br.com.fatec.domain.Garden;
import br.com.fatec.service.GardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/garden")
public class GardenController {

    @Autowired
    GardenService gardenService;

    @RequestMapping(method= RequestMethod.GET, value="/all")
    public List<Garden> getAllGardens(){
        return gardenService.getAllGardens();
    }

    @RequestMapping(method=RequestMethod.GET, value="/{gardenId}")
    public Garden getGardenById( @PathVariable Long gardenId ){
        Garden garden = gardenService.getGardenById(gardenId);
        return garden;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Garden newGarden(@RequestBody @Valid Garden garden){
        gardenService.newGarden(garden);
        return garden;
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{gardenId}")
    public Garden updateGarden(@PathVariable Long gardenId, @RequestBody @Valid Garden garden ){
        garden.setGardenId(gardenId);
        gardenService.updateGarden(garden);
        return garden;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{gardenId}")
    public void deleleGardenById( @PathVariable Long gardenId ){
        gardenService.deleteGardenById(gardenId);
    }

}
