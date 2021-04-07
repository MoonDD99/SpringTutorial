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

    public Dog findDog(String name){
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("name").is(name)),
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
