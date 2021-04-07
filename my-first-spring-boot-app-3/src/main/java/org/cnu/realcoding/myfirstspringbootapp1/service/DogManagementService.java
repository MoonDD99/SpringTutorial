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

    public void renewalInfo(Dog dog) {
        Dog prevDog = getDogByName(dog.getName());
        for(int i=0; i<dog.getMedicalRecord().size(); i++){
            prevDog.getMedicalRecord().add(dog.getMedicalRecord().get(i));
        }
        List<String> newMedicalRecord = prevDog.getMedicalRecord();
        dog.setMedicalRecord(newMedicalRecord);
        dogRepository.changeInfo(dog);
    }

    public void renewalKind(String name, String kind) {
        Dog prevDog = getDogByName(name);
        prevDog.setKind(kind);
        dogRepository.changeInfo(prevDog);
    }

    public void plusMedicalRecords(String name, String medicalRecord) {
        Dog dog = getDogByName(name);
        dog.getMedicalRecord().add(medicalRecord);
        dogRepository.changeInfo(dog);
    }
}
