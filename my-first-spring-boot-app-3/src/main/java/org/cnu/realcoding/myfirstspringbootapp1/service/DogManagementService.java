package org.cnu.realcoding.myfirstspringbootapp1.service;

import org.cnu.realcoding.myfirstspringbootapp1.domain.Dog;
import org.cnu.realcoding.myfirstspringbootapp1.exception.DogNotFoundException;
import org.cnu.realcoding.myfirstspringbootapp1.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        dogRepository.insertDog(dog);
    }

    public List<Dog> getDogByOwnerName(String ownerName){
        List<Dog> dog = dogRepository.findDogByOwnerName(ownerName);

        if(dog == null || dog.isEmpty()){
            throw new DogNotFoundException();
        }

        return dog;
    }

    public List<Dog> getDogByOwnerPhone(String ownerPhoneNum){
        List<Dog> dog = dogRepository.findDogByOwnerPhone(ownerPhoneNum);

        if(dog == null || dog.isEmpty()){
            throw new DogNotFoundException();
        }

        return dog;
    }

    public List<Dog> getDogByName(String name) {
        List<Dog> dog = dogRepository.findDog(name);

        if(dog == null || dog.isEmpty()){
            throw new DogNotFoundException();
        }
        return dog;
    }

    public List<Dog> getAllDogs(){
        return dogRepository.findAllDog();
    }
}
