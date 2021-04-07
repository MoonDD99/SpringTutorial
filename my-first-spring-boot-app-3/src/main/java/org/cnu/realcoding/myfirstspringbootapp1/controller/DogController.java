package org.cnu.realcoding.myfirstspringbootapp1.controller;

import org.cnu.realcoding.myfirstspringbootapp1.domain.Dog;
import org.cnu.realcoding.myfirstspringbootapp1.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class DogController {

    @Autowired // dogManagementService를 사용하기위해 (없으면 객체화만되어있다.)
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDog(@RequestBody Dog dog){
        //외부로부터 받은 정보를 전달
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){
        return dogManagementService.getAllDogs();
    }


    @GetMapping("/dogs/{name}")
    public Dog getDogByName(@PathVariable String name){
        return dogManagementService.getDogByName(name);
        //localhost:8003/dogs?name=moon-> @GetMapping(/dogs?name=moon) -> @RequestParam
    }

    @PutMapping("/dogs")
    public void renewalDogInfo(@RequestBody Dog dog){
        dogManagementService.renewalInfo(dog);
    }

    @PutMapping("/dogs/{name}/{kind}")
    public void renewalDogKind(
            @PathVariable String name,
            @PathVariable String kind){
        dogManagementService.renewalKind(name,kind);
    }

    @PutMapping("/dogs/")
    public void addDogMedicalRecords(
            @RequestParam("name") String name,
            @RequestParam("medicalRecord") String medicalRecord){
        dogManagementService.plusMedicalRecords(name, medicalRecord);
    }

}
