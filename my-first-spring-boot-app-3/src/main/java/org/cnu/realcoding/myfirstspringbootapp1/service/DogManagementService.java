package org.cnu.realcoding.myfirstspringbootapp1.service;

import org.cnu.realcoding.myfirstspringbootapp1.domain.Dog;
import org.cnu.realcoding.myfirstspringbootapp1.exception.DogNotFoundException;
import org.cnu.realcoding.myfirstspringbootapp1.exception.SameDogExistException;
import org.cnu.realcoding.myfirstspringbootapp1.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        List<Dog> dogList = dogRepository.findDog(dog.getName());
        List<Dog> ownerNameList = dogRepository.findDogByOwnerName(dog.getOwnerName());
        List<Dog> ownerPhoneNumList = dogRepository.findDogByOwnerPhone(dog.getOwnerPhoneNumber());

        if(dogList != null || ownerNameList != null || ownerPhoneNumList != null){
            throw new SameDogExistException();
        }

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

    public Dog getOneDogByName(String name){
        Dog dog = dogRepository.findOneDog(name);

        if(dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }

    public List<Dog> getAllDogs(){
        return dogRepository.findAllDog();
    }

    public void renewalInfo(Dog dog) {
        Dog prevDog = getOneDogByName(dog.getName());
        for(int i=0; i<dog.getMedicalRecord().size(); i++){
            prevDog.getMedicalRecord().add(dog.getMedicalRecord().get(i));
        }
        List<String> newMedicalRecord = prevDog.getMedicalRecord();
        dog.setMedicalRecord(newMedicalRecord);
        dogRepository.changeInfo(dog);
    }

    public void renewalKind(String name, String kind) {
        Dog prevDog = getOneDogByName(name);
        prevDog.setKind(kind);
        dogRepository.changeInfo(prevDog);
    }

    public void plusMedicalRecords(String name, String medicalRecord) {
        Dog dog = getOneDogByName(name);
        dog.getMedicalRecord().add(medicalRecord);
        dogRepository.changeInfo(dog);
    }
}
