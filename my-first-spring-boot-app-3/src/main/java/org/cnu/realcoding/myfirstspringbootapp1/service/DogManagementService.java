package org.cnu.realcoding.myfirstspringbootapp1.service;

import lombok.Getter;
import org.cnu.realcoding.myfirstspringbootapp1.domain.Dog;
import org.cnu.realcoding.myfirstspringbootapp1.exception.DogNotFoundException;
import org.cnu.realcoding.myfirstspringbootapp1.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        dogRepository.insertDog(dog);

    }

    public Dog getDogByName(String name) {
        Dog dog = dogRepository.findDog(name);

        if(dog == null){
            throw new DogNotFoundException();
        }

        return dog;
    }

    public List<Dog> getAllDogs(){
        return dogRepository.findAllDog();
    }
}
