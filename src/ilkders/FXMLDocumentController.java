/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilkders;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.xml.soap.Node;

/**
 *
 * @author argunpc
 */
public class FXMLDocumentController implements Initializable {
    private Label label;
    @FXML
    private TextField textfield;
    @FXML
    private TextField textfiedl2;
    @FXML
    private AnchorPane textfield4;
    @FXML
    private Label yazari;
    @FXML
    private Label yayinadi;
    @FXML
    private TextField textfield3;
    @FXML
    private Label basimtarihi;
    @FXML
    private Label türü;
    @FXML
    private TextField textfield5;
    @FXML
    private Label raf;
    @FXML
    private TextField textfield6;
    @FXML
    private Button buttonkaydet;
    @FXML
    private Button buttonsil;
    @FXML
    private Button buttonupdate;
    @FXML
    private TableView<Book> booktable;
    private Connection conn;
    @FXML
    private DatePicker date;
    @FXML
    private TableColumn<Book, String> yayinevi;
    public  Vector<String> kitapliste=new Vector<String>();
    @FXML
    private Button listbutton;
    @FXML
    private TableColumn<Book, String> kitapadi;
    @FXML
    private TableColumn<Book, String> yazaradi;
    @FXML
    private TableColumn<Book, String> kitaprafi;
    @FXML
    private TableColumn<Book, String> bastar;
    @FXML
    private TableColumn<Book, String> turu;
    String kitapi;
    String kitap_adi;
    String yazarii;
    String yayinevi1;
    String t;
    String turu1;
    String rafi;
    Stage window;
    public int kitapsayi;
    private Stage primaryStage;

            @FXML
    private TableColumn<Book, String> kitapid;
    @FXML
    private Label id;
    @FXML
    private Label adi1;
    @FXML
    private TextField textfieldid;
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       conn=DatabaseConnection.BaglantiKur();
       if(conn==null)
        {
        	System.out.println("Baglanti kurulmadi");
        }
    } 
    
    
    @FXML
    private void kaydet(ActionEvent event) throws SQLException {
        ResultSet rs=null;
        PreparedStatement preparedStatement=null;
        kitapi=textfieldid.getText();
        kitap_adi=textfield.getText();
        yazarii=textfiedl2.getText();
         yayinevi1=textfield3.getText();
        LocalDate tarih=date.getValue(); 
         t=tarih.toString();
         turu1=textfield5.getText();
         rafi=textfield6.getText();
        if(kitapi.isEmpty()||kitap_adi.isEmpty()||yazarii.isEmpty()||yayinevi1.isEmpty()||t.isEmpty()||turu1.isEmpty()||rafi.isEmpty()) {
         Alert a=new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Uyari");
        a.setContentText("Tum Alanlari doldurunuz");
        a.showAndWait();
        }
       int flag=kontrol();
        if(flag==1) {
         String sqlsorgu="INSERT INTO kitap(kitap_id,kitap_adi,kitap_yazar,yayinevi,basim_tarihi,kitap_turu,kitap_rafi) VALUES(?,?,?,?,?,?,?)";
         preparedStatement=conn.prepareStatement(sqlsorgu);
         preparedStatement.setString(1,kitapi);
         preparedStatement.setString(2,kitap_adi);
         preparedStatement.setString(3,yazarii);
         preparedStatement.setString(4,yayinevi1);
         preparedStatement.setString(5,t);
         preparedStatement.setString(6,turu1);
         preparedStatement.setString(7,rafi);
         preparedStatement.executeUpdate();
         Alert alert=new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText("Kitap Ekleme islemleri");
         alert.setContentText("Başarı ile Sonuçlandı");
         alert.showAndWait();
        }
        else if(flag==0) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hata");
            alert.setContentText("Ayni kitabi ekleyemezsiniz");
            alert.showAndWait();
            return;
        }
    }
    
    
public int kontrol() throws SQLException {
int sayac=0;
    String sql="SELECT kitap_id FROM kitap";
ResultSet rs=null;
Statement st=null;
st=conn.createStatement();
rs=st.executeQuery(sql);
while(rs.next()) {
    String id2=rs.getString("kitap_id");
    kitapliste.add(id2);
    kitapsayi=kitapliste.size();
}
    for (int i = 0; i<kitapliste.size(); i++) {
        if(kitapliste.get(i).equals(kitapi)) {
        sayac++;
        }
    }
    if(sayac==0) {
    return 1;
    }
    else {
      return 0;  
    }
}  

    @FXML
    private void sil(ActionEvent event) throws SQLException {        
           booktable.getItems().remove(booktable.getSelectionModel().getSelectedItem());
           ObservableList<Book> gecici=FXCollections.observableArrayList(); 
     for (int i = 0; i < booktable.getItems().size(); i++) {
            gecici.add(booktable.getItems().get(i));
        }
     sifirla();
    //Book'lar listemizenmiş oldu.
         for (int i = 0; i < gecici.size(); i++) {      
         String kitapdi=gecici.get(i).getKitap_id();
         String kitapname=gecici.get(i).getKitap_adi();
         String kitapyazar=gecici.get(i).getKitap_yazar();
         String yayev=gecici.get(i).getYayinevi();
         String bastarih=gecici.get(i).getBasim_tarihi();
         String tur=gecici.get(i).getKitap_turu();
         String raf=gecici.get(i).getKitap_rafi();
         String sqlsorgu2="INSERT INTO kitap(kitap_id,kitap_adi,kitap_yazar,yayinevi,basim_tarihi,kitap_turu,kitap_rafi) VALUES(?,?,?,?,?,?,?)";
         PreparedStatement preparedStatement2=null;
         preparedStatement2=conn.prepareStatement(sqlsorgu2);
         preparedStatement2.setString(1,kitapdi);
         preparedStatement2.setString(2,kitapname);
         preparedStatement2.setString(3,kitapyazar);
         preparedStatement2.setString(4,yayev);
         preparedStatement2.setString(5,bastarih);
         preparedStatement2.setString(6,tur);
         preparedStatement2.setString(7,raf);
         preparedStatement2.executeUpdate();
        }
    }
    
    
    @FXML
    private void update(ActionEvent event) throws IOException {
Stage st;
Parent r;

st=(Stage)buttonupdate.getScene().getWindow();
  r= FXMLLoader.load(getClass().getResource("update.fxml"));
Scene scene=new Scene(r);
st.setScene(scene);
       st.show();
        
        
      /*  
        Parent pane=FXMLLoader.load(getClass().getResource("update.fxml"));
  Scene scene = new Scene( pane );
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
  window.show();*/
    }   

    
    @FXML
    private void listele(ActionEvent event) throws SQLException {
    booktable.getItems().clear();
    ObservableList<Book> list=FXCollections.observableArrayList(); 
    initcol();
    String sql="SELECT * FROM kitap";
    ResultSet rs=null;
    PreparedStatement st=null;
    st=conn.prepareStatement(sql);
    rs=st.executeQuery();

   while(rs.next()) {
           kitapi=rs.getString("kitap_id");
         kitap_adi=rs.getString("kitap_adi");
         yazarii=rs.getString("kitap_yazar");
         String yayinevi=rs.getString("yayinevi");
       String basimtarihi=rs.getString("basim_tarihi");
       String t=basimtarihi.toString();
        String tp=rs.getString("kitap_turu");
        String kitaprafi=rs.getString("kitap_rafi");
        list.add(new Book(kitapi,kitap_adi,yazarii,yayinevi,t,tp,kitaprafi));
        }
   if(list.isEmpty()) {
    Alert alert=new Alert(Alert.AlertType.WARNING);
    alert.setContentText("Gösterilecek veri yok");
    alert.setHeaderText("Error");
    alert.showAndWait();
   }
   booktable.getItems().addAll(list);
    }
    
    
    public void sifirla() throws SQLException {
    String sqlsorgu="DELETE FROM kitap";
    PreparedStatement st=null;
    st=conn.prepareCall(sqlsorgu);
    st.executeUpdate();
    }
    
    
    
    private void initcol() {
     kitapid.setCellValueFactory(new PropertyValueFactory<>("kitap_id"));
    kitapadi.setCellValueFactory(new PropertyValueFactory<>("kitap_adi"));
    yazaradi.setCellValueFactory(new PropertyValueFactory<>("kitap_yazar"));
    yayinevi.setCellValueFactory(new PropertyValueFactory<>("yayinevi"));
    bastar.setCellValueFactory(new PropertyValueFactory<>("basim_tarihi"));
    turu.setCellValueFactory(new PropertyValueFactory<>("kitap_turu"));
    kitaprafi.setCellValueFactory(new PropertyValueFactory<>("kitap_rafi"));
    
    }
  
}