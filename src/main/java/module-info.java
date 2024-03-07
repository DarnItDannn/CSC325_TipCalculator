module com.example.csc325_tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc325_tipcalculator to javafx.fxml;
    exports com.example.csc325_tipcalculator;
}