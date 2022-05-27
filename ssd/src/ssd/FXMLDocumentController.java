/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssd;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfMerk;
    @FXML
    private ComboBox<?> tfUkuran;
    @FXML
    private DatePicker tfTanggal;
    @FXML
    private TextField tfHarga;
    @FXML
    private TableView<ssd> tvBooks;
    @FXML
    private TableColumn<ssd, Integer> colId;
    @FXML
    private TableColumn<ssd, String> colMerk;
    @FXML
    private TableColumn<ssd, String> colUkuran;
    @FXML
    private TableColumn<ssd, String> colTanggal;
    @FXML
    private TableColumn<ssd, String> colHarga;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
 
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showBooks();
        ArrayList <String> list = new ArrayList<String>();
        list.add("128 Gb");
        list.add("256 Gb");
        list.add("512 Gb");
        list.add("1028 Gb");
        ObservableList items = FXCollections.observableArrayList(list);
        tfUkuran.setItems(items);
    }    
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssd", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<ssd> getBooksList(){
        ObservableList<ssd> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM ssd";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ssd x;
            while(rs.next()){
                x = new ssd(rs.getInt("id"), rs.getString("merk"), rs.getString("ukuran"), rs.getString("tanggal"),rs.getString("harga"));
                bookList.add(x);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }
    
    public void showBooks(){
        ObservableList<ssd> list = getBooksList();
        
        colId.setCellValueFactory(new PropertyValueFactory<ssd, Integer>("id"));
        colMerk.setCellValueFactory(new PropertyValueFactory<ssd, String>("merk"));
        colUkuran.setCellValueFactory(new PropertyValueFactory<ssd, String>("ukuran"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<ssd, String>("tanggal"));
        colHarga.setCellValueFactory(new PropertyValueFactory<ssd, String>("harga"));
        
        tvBooks.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO ssd VALUES (" + tfId.getText() + ",'" + tfMerk.getText() + "','" + tfUkuran.getValue().toString() + "',"
                + tfTanggal.getValue().toString() + "," + tfHarga.getText() + ")";
        executeQuery(query);
        showBooks();
    }
    
    private void updateRecord(){
        String query = "UPDATE  ssd SET merk  = '" + tfMerk.getText() + "', ukuran = '" + tfUkuran.getValue().toString() + "', tanggal = " +
                tfTanggal.getValue().toString() + ", harga = " + tfHarga.getText() + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    
    private void deleteButton(){
        String query = "DELETE FROM ssd WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
