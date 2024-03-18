package com.example.csc325_tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class HelloController {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        calculateTipAndTotal();
    }

    @FXML
    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                        tipPercentageLabel.setText(percent.format(tipPercentage));
                        calculateTipAndTotal();
                    }
                }
        );

        amountTextField.textProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                        calculateTipAndTotal();
                    }
                }
        );
    }

    private void calculateTipAndTotal() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }
}