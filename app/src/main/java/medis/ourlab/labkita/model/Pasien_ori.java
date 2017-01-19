package medis.ourlab.labkita.model;

/**
 * Created by tri on 10/3/2016.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lincoln on 07/01/16.
 */
public class Pasien_ori implements Serializable {
    String name,alamat,alamat_lab,nama_member,invoice,nama_lab;
    Integer id_order,id_pasien,id_lab,id_member,id_invoice,usia_pasien,gender; //gender 1=male 2=female
    Double longitude_pasien,latitude_pasien,longitude_lab,latitude_lab;
    ArrayList<Tests_lab> tests_lab;
    ArrayList<Tests_medis> tests_medis;


    public Pasien_ori() {
    }

    public Pasien_ori(Integer id_order,
                      String name,
                      Double longitude_pasien,
                      Double latitude_pasien,
                      String alamat,
                      String alamat_lab,
                      String nama_member,
                      String invoice,
                      Integer id_pasien,
                      Integer id_lab,
                      Integer id_member,
                      Integer id_invoice,
                      ArrayList<Tests_lab> tests_lab,
                      Integer usia_pasien,
                      Integer gender,
                      String nama_lab,
                      Double longitude_lab,
                      Double latitude_lab) {

        this.name = name;
        this.longitude_pasien = longitude_pasien;
        this.latitude_pasien= latitude_pasien;
        this.alamat= alamat;
        this.alamat_lab= alamat_lab;
        this.nama_member= nama_member;
        this.invoice= invoice;
        this.id_pasien= id_pasien;
        this.id_lab= id_lab;
        this.id_member= id_member;
        this.id_invoice= id_invoice;
        this.tests_lab= tests_lab;
        //this.tests_medis= tests_medis;
        this.usia_pasien= usia_pasien;
        this.gender= gender;
        this.nama_lab= nama_lab;
        this.longitude_lab=longitude_lab;
        this.latitude_lab=latitude_lab;
        this.id_order=id_order;
    }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public Double getlongitude_pasien(){return longitude_pasien;}
    public void setlongitude_pasien(Double longitude_pasien){this.longitude_pasien=longitude_pasien;}
    public Double getlatitude_pasien(){return latitude_pasien;}
    public void setlatitude_pasien(Double latitude_pasien){this.latitude_pasien=latitude_pasien;}
    public String getAlamat(){return alamat;}
    public void setAlamat(String alamat){this.alamat=alamat;}
    public String getAlamatLab(){return alamat_lab;}
    public void setAlamatLab(String alamat_lab){this.alamat_lab=alamat_lab;}
    public String getNama_member(){return nama_member;}
    public void setNama_member(String nama_member){this.nama_member=nama_member;}
    public String getInvoice(){return invoice;}
    public void setInvoice(String invoice){this.invoice=invoice;}
    public Integer getIdPasien(){return id_pasien;}
    public void setIdPasien(Integer id_pasien){this.id_pasien=id_pasien;}
    public Integer getIdLab(){return id_lab;}
    public void setIdLab(String idLab){this.id_lab=id_lab;}
    public Integer getIdMember(){return id_member;}
    public void setIdMember(Integer id_member){this.id_member=id_member;}
    public Integer getIdInvoice(){return id_invoice;}
    public void setIdInvoice(Integer id_invoice){this.id_invoice=id_invoice;}
    public ArrayList<Tests_lab> getTestsLab(){return tests_lab;}
    public void setTestsLab(ArrayList<Tests_lab> tests){this.tests_lab=tests;}
    public ArrayList<Tests_medis> getTestsMedis(){return tests_medis;}
    public void setTestsMedis(ArrayList<Tests_medis> tests){this.tests_medis=tests;}
    public Integer getUsiaPasien(){return usia_pasien;}
    public void setUsiaPasien(Integer usia_pasien){this.usia_pasien=usia_pasien;}
    public Integer getGender(){return gender;}
    public void setGender(Integer gender){this.gender=gender;}
    public String getNamaLab(){return nama_lab;}
    public void setNamaLab(String nama_lab){this.nama_lab=nama_lab;}
    public Double getlongitude_lab(){return longitude_lab;}
    public void setlongitude_lab(Double longitude_lab){this.longitude_lab=longitude_lab;}
    public Double getlatitude_lab(){return latitude_lab;}
    public void setlatitude_lab(Double latitude_lab){this.latitude_lab=latitude_lab;}
    public Integer getIdOrder(){return id_order;}
    public void setIdOrder(Integer id_order){this.id_order=id_order;}
}
