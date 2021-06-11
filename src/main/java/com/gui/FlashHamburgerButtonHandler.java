package com.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

class FlashHamburgerButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        CalculatorGUI.toggleTimeSale();
    }

}

