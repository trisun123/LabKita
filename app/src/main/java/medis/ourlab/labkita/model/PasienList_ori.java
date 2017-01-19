package medis.ourlab.labkita.model;

/**
 * Created by tri on 10/3/2016.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lincoln on 07/01/16.
 */
public class PasienList_ori implements Serializable {

    ArrayList<Pasien> pasiens;
Integer id_order;


    public PasienList_ori() {
    }

    public PasienList_ori(Integer id_order,
                          ArrayList<Pasien> pasiens) {

        this.pasiens= pasiens;
        this.id_order=id_order;

    }

    public ArrayList<Pasien> getPasien(){return pasiens;}
    public void setPasien(ArrayList<Pasien> pasiens){this.pasiens=pasiens;}
    public Integer getIdOrder(){return id_order;}
    public void setIdOrder(Integer id_order){this.id_order=id_order;}

}
