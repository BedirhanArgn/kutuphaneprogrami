/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilkders;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author argunpc
 */

public class UpdateController  implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn;
    private Stage thisStage;     
    @FXML
    private TextField textisim;
    @FXML
    private TextField textsoyisim;
    @FXML
    public TableColumn<Insan, String> tableisim;
    @FXML
    public TableColumn<Insan, String> tablesoyisim;
    @FXML
    public TableColumn<Insan, String> tableodunc;
    @FXML
    public TableColumn<Insan, String> tablealmasuresi;
    @FXML
    private TextField textid;
    @FXML
    private TextField textgunsayisi;
    @FXML
    private Button buttonodunc;
    @FXML
    public TableView<Insan> insantable;


    String isim;
    String soyadi;
    String kitapid;
    String oduncgun;
    Vector<String>kitapidtut=new Vector<String>();
    Vector<String>kitapisimtut=new Vector<String>();
    @FXML
    private Button buttonlistele;
    @FXML
    private Button geri;
    @FXML
    private Button kitapiad;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    conn=DatabaseConnection.BaglantiKur();
       if(conn==null)
        {
        	System.out.println("Baglanti kurulmadi");
        }        
        
    }       

    @FXML
    private void kayitekle(ActionEvent event) throws SQLException {
        isim=textisim.getText();
        soyadi=textsoyisim.getText();
        kitapid=textid.getText();
        oduncgun=textgunsayisi.getText();
        int flag=0;
       String sql="INSERT INTO insan(insan_adi,insan_soyadi,kitap_idsi,gunsayisi) VALUES(?,?,?,?)";
     PreparedStatement preparedStatement=null;
     preparedStatement=conn.prepareStatement(sql);
     preparedStatement.setString(1, isim);
     preparedStatement.setString(2, soyadi);
     preparedStatement.setString(3, kitapid);
       preparedStatement.setString(4, oduncgun);
      int kontrol= idkontol();
       kitapidogren();
       for (int i = 0; i<kitapidtut.size(); i++) {
        if(kitapid.equals(kitapidtut.get(i))) {
                     flag++;
        }
        }
        if(flag<=0) {
        Alert al= new Alert(Alert.AlertType.ERROR);
        al.setContentText("Böyle bir kitap bulanamadi");
        al.showAndWait();
        }
        else {
        if(kontrol==1) {
           preparedStatement.executeUpdate();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Kişi başariyla Eklenmiştir");
        alert.setHeaderText("Bilgi");  
        alert.showAndWait();    
        }
    }}
       public int kitapidogren() throws SQLException{
       String sql="SELECT * FROM kitap";
    ResultSet rs=null;
    PreparedStatement st=null;
    st=conn.prepareStatement(sql);
    rs=st.executeQuery();
    int sayi=0;
while(rs.next()) {
         String  kitapi=rs.getString("kitap_id");
         String kitapisim=rs.getString("kitap_adi");
             sayi++;
            kitapidtut.add(kitapi);
        }
           return sayi;
       }
       
   private int idkontol() throws SQLException {
    String sql="SELECT * FROM insan";
    ResultSet rs=null;
    PreparedStatement st=null;
    int sayac=0;
    st=conn.prepareStatement(sql);
    rs=st.executeQuery();
       while(rs.next()) {
       String kit=rs.getString("kitap_idsi");
       if(kitapid.equals(kit)) {
       sayac++;
       }
       }
        if(sayac>=1) {
       Alert a=new Alert(Alert.AlertType.ERROR);
       a.setContentText("Kitap alınmış");
       a.setHeaderText("UYARI");
       a.showAndWait();
       return 0;
       }
       else {
       return 1;
       }
       }

    @FXML
    private void listele(ActionEvent event) throws SQLException {
  insantable.getItems().clear();
        ObservableList<Insan> liste=FXCollections.observableArrayList(); 
      tableisim.setCellValueFactory(new PropertyValueFactory<>("ad")); //Insan sınıfının değişkenleri ile doldur name kısmını;
    tablesoyisim.setCellValueFactory(new PropertyValueFactory<>("soyadi"));
    tableodunc.setCellValueFactory(new PropertyValueFactory<>("id"));
    tablealmasuresi.setCellValueFactory(new PropertyValueFactory<>("gunsayisi"));   
    String sql="SELECT * FROM insan";
    ResultSet rs=null;
    PreparedStatement st=null;
    st=conn.prepareStatement(sql);
    rs=st.executeQuery();
    String insanadi;
    String insansoyadi;
     String  kitapids;
      String gunsayisi;
    while(rs.next()) {
          insanadi=rs.getString("insan_adi");
          insansoyadi=rs.getString("insan_soyadi");
          kitapids=rs.getString("kitap_idsi");
          gunsayisi=rs.getString("gunsayisi");
        liste.add(new Insan(insanadi,insansoyadi,kitapids,gunsayisi));
        }
   if(liste.isEmpty()) {
    Alert alert=new Alert(Alert.AlertType.WARNING);
    alert.setContentText("Gösterilecek veri yok");
    alert.setHeaderText("Error");
    alert.showAndWait();
   }
   insantable.getItems().addAll(liste);
    }

    @FXML
    private void geridon(ActionEvent event) throws IOException {
   Stage st;
  Parent r;
st=(Stage)geri.getScene().getWindow();
  r= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
Scene scene=new Scene(r);
st.setScene(scene);
       st.show();
    
    
    
    }

    @FXML
    private void iadeet(ActionEvent event) throws SQLException {
        insantable.getItems().remove(insantable.getSelectionModel().getSelectedItem());
        ObservableList<Insan> gecici=FXCollections.observableArrayList(); 
     for(int i = 0; i < insantable.getItems().size(); i++) {
              gecici.add(insantable.getItems().get(i));
        }
     sifirla();
     for (int i = 0; i < gecici.size(); i++) {      
         String isim=gecici.get(i).getAd();
         String soyadi=gecici.get(i).getSoyadi();
         String odunckitap=gecici.get(i).getId();
         String oduncsuresi=gecici.get(i).getGunsayisi();
         String sqlsorgu2="INSERT INTO insan(insan_adi,insan_soyadi,kitap_idsi,gunsayisi) VALUES(?,?,?,?)";
         PreparedStatement preparedStatement2=null;
         preparedStatement2=conn.prepareStatement(sqlsorgu2);
         preparedStatement2.setString(1,isim);
         preparedStatement2.setString(2,soyadi);
         preparedStatement2.setString(3,odunckitap);
         preparedStatement2.setString(4,oduncsuresi);
         preparedStatement2.executeUpdate();
        }
    }
    public void sifirla() throws SQLException {
    String sqlsorgu="DELETE FROM insan";
    PreparedStatement st=null;
    st=conn.prepareCall(sqlsorgu);
    st.executeUpdate();
    }
    
 
       
      
    }

  
   
    
    
    

