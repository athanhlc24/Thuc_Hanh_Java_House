package student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TableView<Student> tbStudent;
    public TableColumn tId;
    public TableColumn tName;
    public TableColumn tEmail;
    public TableColumn tTel;

    public final static String connectionString = "jdbc:mysql://localhost:3306/java";
    public final static String user = "root";
    public final static String password = "";// nếu là macbook thì là ="root";



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tId.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
        tName.setCellValueFactory(new PropertyValueFactory<Student,Integer>("name"));
        tEmail.setCellValueFactory(new PropertyValueFactory<Student,Integer>("email"));
        tTel.setCellValueFactory(new PropertyValueFactory<Student,Integer>("tel"));
        ObservableList<Student> list = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(connectionString,user,password);
            Statement statement = conn.createStatement();
            String sql_txt = "select * from Student";
            ResultSet rs = statement.executeQuery(sql_txt);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String tel = rs.getString("tel");

                Student st = new Student(id,name,email,tel);
                list.add(st);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            tbStudent.setItems(list);
        }
    }
}
