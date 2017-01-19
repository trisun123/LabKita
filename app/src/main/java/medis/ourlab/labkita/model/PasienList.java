package medis.ourlab.labkita.model;

/**
 * Created by tri on 10/3/2016.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lincoln on 07/01/16.
 */
public class PasienList implements Serializable {

    ArrayList<Pasien> pasiens;
    ArrayList<Tests_lab> tests_labs;

    Boolean processed_by_adapter_header =false;
    Boolean processed_by_adapter_child =false;
    Boolean masih_order;
Integer id_order,id_invoice,id_member,id_lab;
    String nama_lab,nama_member,alamat_lab,alamat_pasien,invoice, waktu;
    Double longitude_pasien,latitude_pasien,longitude_lab,latitude_lab, total_harga;

    public PasienList() {
    }


    public PasienList(Integer id_order,
                      Integer id_invoice,
                      Integer id_member,
                      Integer id_lab,
                      String nama_lab,
                      String nama_member,
                      String alamat_lab,
                      String alamat_pasien,
                      String invoice,
                      Double longitude_pasien,
                      Double latitude_pasien,
                      Double longitude_lab,
                      Double latitude_lab,
                      Double total_harga,
                      ArrayList<Pasien> pasiens,
                      String waktu,
                      ArrayList<Tests_lab> tests_labs) {

        this.pasiens= pasiens;
        this.id_order=id_order;
        this.id_member=id_member;
        this.id_lab=id_lab;
        this.id_invoice=id_invoice;
        this.nama_lab=nama_lab;
        this.nama_member=nama_member;
        this.alamat_lab=alamat_lab;
        this.alamat_pasien=alamat_pasien;
        this.invoice=invoice;
        this.longitude_lab=longitude_lab;
        this.latitude_lab=latitude_lab;
        this.longitude_pasien=longitude_pasien;
        this.latitude_pasien=latitude_pasien;
        this.total_harga=total_harga;
this.waktu=waktu;
        this.tests_labs=tests_labs;
    }

    public ArrayList<Pasien> getPasien(){return pasiens;}
    public void setPasien(ArrayList<Pasien> pasiens){this.pasiens=pasiens;}
    public Integer getIdOrder(){return id_order;}
    public void setIdOrder(Integer id_order){this.id_order=id_order;}
    public Integer getId_invoice(){return id_invoice;}
    public void setId_invoice(Integer id_invoice){this.id_invoice=id_invoice;}
    public Integer getId_member(){return id_member;}
    public void setId_member(Integer id_member){this.id_member=id_member;}
    public Integer getId_lab(){return id_lab;}
    public void setId_lab(Integer id_lab){this.id_lab=id_lab;}
    public String getNama_lab(){return nama_lab;}
    public void setNama_lab(String nama_lab){this.nama_lab=nama_lab;}
    public String getNama_member(){return nama_member;}
    public void setNama_member(String nama_member){this.nama_member=nama_member;}
    public String getAlamat_lab(){return alamat_lab;}
    public void setAlamat_lab(String alamat_lab){this.alamat_lab=alamat_lab;}
    public String getAlamat_pasien(){return alamat_pasien;}
    public void setAlamat_pasien(String alamat_pasien){this.alamat_pasien=alamat_pasien;}
    public String getInvoice(){return invoice;}
    public void setInvoice(String invoice){this.invoice=invoice;}
    public Double getLongitude_pasien(){return longitude_pasien;}
    public void setLongitude_pasien(Double longitude_pasien){this.longitude_pasien=longitude_pasien;}
    public Double getLatitude_pasien(){return latitude_pasien;}
    public void setLatitude_pasien(Double latitude_pasien){this.latitude_pasien=latitude_pasien;}
    public Double getLongitude_lab(){return longitude_lab;}
    public void setLongitude_lab(Double longitude_lab){this.longitude_lab=longitude_lab;}
    public Double getLatitude_lab(){return latitude_lab;}
    public void setLatitude_lab(Double latitude_lab){this.latitude_lab=latitude_lab;}
    public Boolean getProcessed_by_adapter_header(){return processed_by_adapter_header;}
    public void setProcessed_by_adapter_header(Boolean processed_by_adapter_header){this.processed_by_adapter_header = processed_by_adapter_header;}
    public Boolean getProcessed_by_adapter_child(){return processed_by_adapter_child;}
    public void setProcessed_by_adapter_child(Boolean processed_by_adapter_child){this.processed_by_adapter_child = processed_by_adapter_child;}
    public Boolean getMasih_order(){
        /*if(masih_order==null)
            return false;
        else*/
            return masih_order;
    }
    public void setMasih_order(Boolean masih_order){this.masih_order=masih_order;}
    public String getWaktu(){return waktu;}
    public void setWaktu(String waktu){this.waktu=waktu;}

    public ArrayList<Tests_lab> getTests_labs(){return tests_labs;}
    public void setTests_labs(ArrayList<Tests_lab> tests_labs){this.tests_labs=tests_labs;}
}
