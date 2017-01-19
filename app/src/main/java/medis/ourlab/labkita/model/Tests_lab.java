package medis.ourlab.labkita.model;

/**
 * Created by tri on 10/3/2016.
 */

import java.io.Serializable;

/**
 * Created by Lincoln on 07/01/16.
 */
public class Tests_lab implements Serializable {
    String name;
    Integer id,id_pasien,jml;
    double harga_lab,harga_medis ;

    public Tests_lab() {
    }

    public Tests_lab(Integer id_pasien,Integer id, String name, double harga_lab,double harga_medis, Integer jml) {
        this.id = id;
        this.id_pasien=id_pasien;
        this.name = name;
        this.harga_lab = harga_lab;
        this.harga_medis = harga_medis;
this.jml = jml;
    }

    public Integer  getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer  getIdPasien() {
        return id_pasien;
    }

    public void setIdPasien(Integer id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){this.name=name;};

    public double getHargaLab() {
        return harga_lab;
    }

    public void setHargaLab(double harga_lab){this.harga_lab=harga_lab;};

    public double getHargaMedis() {
        return harga_medis;
    }

    public void setHargaMedis(double harga_medis){this.harga_medis=harga_medis;};

    public Integer getJml() {return jml;}

    public void setJml(Integer jml){this.jml = jml;}
}
