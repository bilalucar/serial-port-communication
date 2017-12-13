  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantaryetistirmeortami;

/**
 *
 * @author Bilal
 */
public class VeriModel {
    String sicaklik;
    String nem;
    int id;
    
    public VeriModel(int id, String sicaklik, String nem) {
        this.id = id;
        this.sicaklik = sicaklik;
        this.nem = nem;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getSicaklik() {
        return sicaklik;
    }

    public void setSicaklik(String sicakik) {
        this.sicaklik = sicakik;
    }

    public String getNem() {
        return nem;
    }

    public void setNem(String nem) {
        this.nem = nem;
    }
    
}
