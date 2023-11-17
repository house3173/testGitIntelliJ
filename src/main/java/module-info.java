module com.example.guithongbao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guithongbao to javafx.fxml;
    exports com.example.guithongbao;
}