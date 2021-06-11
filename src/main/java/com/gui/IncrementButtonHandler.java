package com.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

class IncrementButtonHandler implements ActionListener {

    private final String productName;
    private final int    numIncrement;

    public IncrementButtonHandler(String productName) {
        this(productName, 1);
    }

    public IncrementButtonHandler(String productName, int numIncrement) {
        this.productName = productName;
        this.numIncrement = numIncrement;
    }

    public void actionPerformed(ActionEvent event) {
        int quantity = CalculatorGUI.cart.get(this.productName);
        if (quantity + this.numIncrement <= 0) { //if in decrement mode and the current quantity is 1
            CalculatorGUI.removeSubtotalEntry(this.productName);
        } else {
            quantity += this.numIncrement;
            CalculatorGUI.cart.put(this.productName, quantity);
            CalculatorGUI.subtotalEntryMap.get(productName).setSubtotalLabelText(productName, quantity);
            CalculatorGUI.recalculateTotalAmountDisplay();
            CalculatorGUI.redraw();
        }
    }

}

