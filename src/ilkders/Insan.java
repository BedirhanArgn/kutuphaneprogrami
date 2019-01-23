/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilkders;

/**
 *
 * @author argunpc
 */
public class Insan {
    String ad;
    String soyadi;
    String id;
    String gunsayisi;

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGunsayisi(String gunsayisi) {
        this.gunsayisi = gunsayisi;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public String getId() {
        return id;
    }

    public String getGunsayisi() {
        return gunsayisi;
    }
public Insan() {
this.ad="";
this.soyadi="";
this.id="";
this.gunsayisi="";
}
    public Insan(String ad,String soyadi,String id,String gunsayisi ) {
    this.ad=ad;
    this.soyadi=soyadi;
    this.id=id;
    this.gunsayisi=gunsayisi;
    }
    
}
