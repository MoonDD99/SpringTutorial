package org.cnu.realcoding.myfirstspringbootapp1.controller;

import org.cnu.realcoding.myfirstspringbootapp1.domain.Dog;
import org.cnu.realcoding.myfirstspringbootapp1.exception.DogNotFoundException;
import org.cnu.realcoding.myfirstspringbootapp1.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    @Autowired // dogManagementService를 사용하기위해 (없으면 객체화만되어있다.)
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDog(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){
        return dogManagementService.getAllDogs();
    }

    @GetMapping("/dogs/name={name}")
    public List<Dog> getDogByName(@PathVariable String name){
        return dogManagementService.getDogByName(name);
    }

    @GetMapping("/dogs/ownerName={ownerName}")
    public List<Dog> getDogByOwnerName(@PathVariable String ownerName){
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    @GetMapping("/dogs/ownerPhone={ownerPhoneNum}")
    public List<Dog> getDogByOwnerPhone(@PathVariable String ownerPhoneNum){
        return dogManagementService.getDogByOwnerPhone(ownerPhoneNum);
    }

    @GetMapping("/dogs/name={name}/ownerName={ownerName}/ownerPhone={ownerPhoneNum}")
    public Dog getDogByAllInfo(@PathVariable String name,@PathVariable String ownerName,@PathVariable String ownerPhoneNum) throws Exception {
        List<Dog> nameList = dogManagementService.getDogByName(name);
        List<Dog> ownerNameList = dogManagementService.getDogByOwnerName(ownerName);
        List<Dog> ownerPhoneNumList = dogManagementService.getDogByOwnerPhone(ownerPhoneNum);

        nameList.retainAll(ownerNameList);
        nameList.retainAll(ownerPhoneNumList);

        Dog dog = null;
        if (nameList.size() == 1) dog = nameList.get(0);

        if (dog == null) throw new DogNotFoundException();

        return dog;
    }
}
