package com.kidusmt.android.json_parsing;

/**
 * Created by KidusMT on 8/9/2017.
 */

public class Trainer {
    private String name;
    private String description;
    private String country;
    private String professional_course;
    private String image;

    public Trainer(){

    }

    public Trainer(String name, String description, String country, String professional_course, String image) {
        super();
        this.name = name;
        this.description = description;
        this.country = country;
        this.professional_course = professional_course;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfessional_course() {
        return professional_course;
    }

    public void setProfessional_course(String professional_course) {
        this.professional_course = professional_course;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
