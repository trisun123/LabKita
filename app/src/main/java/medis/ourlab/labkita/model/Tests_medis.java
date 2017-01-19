package medis.ourlab.labkita.model;

/**
 * Created by tri on 10/3/2016.
 */

import java.io.Serializable;

/**
 * Created by Lincoln on 07/01/16.
 */
public class Tests_medis implements Serializable {
    String id, name;
    double harga ;

    public Tests_medis() {
    }

    public Tests_medis(String id, String name, double harga) {
        this.id = id;
        this.name = name;
        this.harga = harga;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){this.name=name;};

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga){this.harga=harga;};
}
