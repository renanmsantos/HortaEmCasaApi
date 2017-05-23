package br.com.fatec.controller;

import br.com.fatec.domain.Type;
import br.com.fatec.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @RequestMapping(method= RequestMethod.GET, value="/all")
    public List<Type> getAllTypes(){
        return typeService.getAllTypes();
    }

    @RequestMapping(method=RequestMethod.GET, value="/{typeId}")
    public Type getTypeById( @PathVariable Long typeId ){
        Type type = typeService.getTypeById(typeId);
        return type;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Type newType(@RequestBody @Valid Type type){
        typeService.newType(type);
        return type;
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{typeId}")
    public Type updateType(@PathVariable Long typeId, @RequestBody @Valid Type type ){
        type.setTypeId(typeId);
        typeService.updateType(type);
        return type;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{typeId}")
    public void deleleTypeById( @PathVariable Long typeId ){
        typeService.deleteTypeById(typeId);
    }

}
