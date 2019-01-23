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
public class Book {
    String kitap_id;
    String kitap_adi;
  String kitap_yazar;
  String yayinevi;
  String basim_tarihi;
  String kitap_turu;
  String kitap_rafi;
      public Book(String kitap_id,String kitap_adi,String kitap_yazar,String yayinevi,String basimtarihi,String kitap_turu,String kitap_rafi) {
    this.kitap_id=kitap_id;
    this.kitap_adi=kitap_adi;
    this.kitap_yazar=kitap_yazar;
    this.yayinevi=yayinevi;
    this.basim_tarihi=basimtarihi;
    this.kitap_turu=kitap_turu;
    this.kitap_rafi=kitap_rafi;
    }
      public Book(){}
    public String getKitap_adi() {
        return kitap_adi;
    }

    public String getKitap_id() {
        return kitap_id;
    }
    

    public void setKitap_id(String kitap_id) {
        this.kitap_id = kitap_id;
    }
    public String getKitap_yazar() {
        return kitap_yazar;
    }

    public String getYayinevi() {
        return yayinevi;
    }

    public String getBasim_tarihi() {
        return basim_tarihi;
    }

    public String getKitap_turu() {
        return kitap_turu;
    }

    public String getKitap_rafi() {
        return kitap_rafi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public void setKitap_yazar(String kitap_yazar) {
        this.kitap_yazar = kitap_yazar;
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    public void setBasim_tarihi(String basim_tarihi) {
        this.basim_tarihi = basim_tarihi;
    }

    public void setKitap_turu(String kitap_turu) {
        this.kitap_turu = kitap_turu;
    }

    public void setKitap_rafi(String kitap_rafi) {
        this.kitap_rafi = kitap_rafi;
    }
  

    
}
