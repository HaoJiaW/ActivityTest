package com.example.activitytest.ForAdapter;

public class Contract {

    private String person_name;
    private String person_number;

    public Contract(String person_name,String person_number){
        this.person_name=person_name;
        this.person_number=person_number;

    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public void setPerson_number(String person_number) {
        this.person_number = person_number;
    }

    public String getPerson_number() {
        return person_number;
    }
}