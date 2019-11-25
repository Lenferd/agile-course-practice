package ru.unn.agile.QuadraticEquasion.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;
import ru.unn.agile.QuadraticEquasion.model.QuadraticEquasion;

public class QuadraticEquasionViewModel {
    private BooleanProperty btnCalcDisabled = new SimpleBooleanProperty();
    private StringProperty txtCoeffA = new SimpleStringProperty();
    private StringProperty txtCoeffB = new SimpleStringProperty();
    private StringProperty txtCoeffC = new SimpleStringProperty();
    private StringProperty lblResult = new SimpleStringProperty();
    private StringProperty lblError = new SimpleStringProperty();

    public QuadraticEquasionViewModel() {
        btnCalcDisabled.setValue(true);
        txtCoeffA.setValue("");
        txtCoeffB.setValue("");
        txtCoeffC.setValue("");
        lblResult.setValue("");
        lblError.setValue("");

        txtCoeffA.addListener((observable, oldValue, newValue) -> {
            inputValueA(newValue);
        });
        txtCoeffB.addListener((observable, oldValue, newValue) -> {
            inputValueB(newValue);
        });
        txtCoeffC.addListener((observable, oldValue, newValue) -> {
            inputValueC(newValue);
        });
    }

    private void inputValueA(final String newValue) {
        txtCoeffAProperty().setValue(newValue);
        btnCalcDisabled.setValue(!txtFieldsNotIsEmpty() || !canConvertToCoeffsDouble());
    }

    private void inputValueB(final String newValue) {
        txtCoeffBProperty().setValue(newValue);
        btnCalcDisabled.setValue(!txtFieldsNotIsEmpty() || !canConvertToCoeffsDouble());
    }

    private void inputValueC(final String newValue) {
        txtCoeffCProperty().setValue(newValue);
        btnCalcDisabled.setValue(!txtFieldsNotIsEmpty() || !canConvertToCoeffsDouble());
    }

    public BooleanProperty isCalculateButtonDisabled() {
        return btnCalcDisabled;
    }

    public StringProperty txtCoeffAProperty() {
        return txtCoeffA;
    }

    public StringProperty txtCoeffBProperty() {
        return txtCoeffB;
    }

    public StringProperty txtCoeffCProperty() {
        return txtCoeffC;
    }

    public StringProperty lblResultProperty() {
        return lblResult;
    }

    private boolean txtFieldsNotIsEmpty() {
        return !txtCoeffAProperty().get().isEmpty()
                && !txtCoeffBProperty().get().isEmpty()
                && !txtCoeffCProperty().get().isEmpty();
    }

    public void calculate() {
        QuadraticEquasion qe;
        lblError.setValue("");
        try {
            qe = new QuadraticEquasion(Double.parseDouble(txtCoeffA.get()),
                    Double.parseDouble(txtCoeffB.get()),
                    Double.parseDouble(txtCoeffC.get()));
        } catch (IllegalArgumentException e) {
            lblError.setValue("Incorrect Input Data");
            return;
        }

        ComplexNumber[] result = qe.solve();
        StringBuilder res = new StringBuilder();
        res.append("X1 = ").append(result[0]);
        if (result.length > 1) {
            res.append("; X2 = ").append(result[1]);
        }
        lblResult.setValue(res.toString());
    }

    private boolean canConvertToDouble(final String value) {
        return value.matches("[+-]?([0-9]*[.])?[0-9]+");
    }

    private boolean canConvertToCoeffsDouble() {
        return canConvertToDouble(txtCoeffA.get())
                && canConvertToDouble(txtCoeffB.get())
                && canConvertToDouble(txtCoeffC.get());
    }

    public StringProperty lblErrorProperty() {
        return lblError;
    }
}
