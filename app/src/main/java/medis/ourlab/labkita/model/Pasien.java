package medis.ourlab.labkita.model;

/**
 * Created by tri on 10/3/2016.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lincoln on 07/01/16.
 */
public class Pasien implements Serializable {
    String name;
    Integer id_order,id_pasien,usia_pasien,gender, testlab; //gender 1=male 2=female

    ArrayList<Tests_lab> tests_lab;



    public Pasien() {
    }

    public Pasien(Integer id_order,
                  String name,
                  Integer id_pasien,
                  ArrayList<Tests_lab> tests_lab,
                  Integer usia_pasien,
                  Integer gender, //1=male 2=female
                  Integer testlab
    ) {

        this.name = name;
        this.id_pasien = id_pasien;

        this.tests_lab = tests_lab;

        this.usia_pasien = usia_pasien;
        this.gender = gender;

        this.id_order = id_order;

        this.testlab=testlab;
    }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public Integer getIdPasien(){return id_pasien;}
    public void setIdPasien(Integer id_pasien){this.id_pasien=id_pasien;}

    public ArrayList<Tests_lab> getTestsLab(){return tests_lab;}
    public void setTestsLab(ArrayList<Tests_lab> tests){this.tests_lab=tests;}

    public Integer getUsiaPasien(){return usia_pasien;}
    public void setUsiaPasien(Integer usia_pasien){this.usia_pasien=usia_pasien;}
    public Integer getGender(){return gender;}
    public void setGender(Integer gender){this.gender=gender;}

    public Integer getTestLabId(){return testlab;}
    public void setTestLabId(Integer testlab){this.testlab=testlab;}


}
