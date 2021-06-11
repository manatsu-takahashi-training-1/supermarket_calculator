package com.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

class ResetButtonHandler implements ActionListener {

    private final String productName;

    public ResetButtonHandler (String productName) {
        this.productName = productName;
    }

    public void actionPerformed(ActionEvent event) {
        CalculatorGUI.removeSubtotalEntry(this.productName);
    }

}

