module com.example.trabalho2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trabalho2 to javafx.fxml;
    exports com.example.trabalho2;
}