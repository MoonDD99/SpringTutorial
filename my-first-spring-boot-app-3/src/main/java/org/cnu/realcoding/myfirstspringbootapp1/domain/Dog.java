package org.cnu.realcoding.myfirstspringbootapp1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //Both Getter and Setter
@NoArgsConstructor //Default constructor
@AllArgsConstructor // name kind age constructor
public class Dog {
    private String name;
    private String kind;
    private String ownerName;
    private String ownerPhoneNumber;
    private List<String> medicalRecord;

}
