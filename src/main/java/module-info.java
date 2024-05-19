module com.example.esercizioblackjack {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.esercizioblackjack to javafx.fxml;
    exports com.example.esercizioblackjack;
}