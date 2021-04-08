package org.cnu.realcoding.myfirstspringbootapp1.repository;

import org.cnu.realcoding.myfirstspringbootapp1.domain.Dog;
import org.cnu.realcoding.myfirstspringbootapp1.exception.DogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    public Dog findOneDog(String name) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(name)),
                Dog.class
        );
    }
    public List<Dog> findDog(String name){
        return mongoTemplate.find(
                Query.query(Criteria.where("name").is(name)),
                Dog.class
        );
    }
    public List<Dog> findDogByOwnerName(String ownerName){
        return mongoTemplate.find(
                Query.query(Criteria.where("ownerName").is(ownerName)),
                Dog.class
        );
    }

    public List<Dog> findDogByOwnerPhone(String ownerPhoneNum){
        return mongoTemplate.find(
                Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNum)),
                Dog.class
        );
    }
    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }

    public List<Dog> findAllDog(){
        return mongoTemplate.findAll(Dog.class);
    }

    public void changeInfo(Dog dog) {
        mongoTemplate
                .findAndReplace(
                        Query.query(Criteria.where("name").is(dog.getName())),
                        dog
                );
    }
}
