package com.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

class ProductButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent event) {

        //ex: "<html>りんご<br/>(100円)</html>" -> "りんご"
        String productName = event.getActionCommand();
        var pattern = java.util.regex.Pattern.compile("(?<=<html>)[^<]+");
        var matcher = pattern.matcher(productName);
        matcher.find();
        productName = matcher.group();

        if (CalculatorGUI.cart.containsKey(productName)) { //increments the quantity

            final int quantity = CalculatorGUI.cart.get(productName) + 1;
            CalculatorGUI.cart.put(productName, quantity);
            CalculatorGUI.subtotalEntryMap.get(productName).setSubtotalLabelText(productName, quantity);

        } else { //creates an entry

            CalculatorGUI.cart.put(productName, 1);

            final SubtotalEntry entry = new SubtotalEntry(productName);
            CalculatorGUI.panel2.add(entry.panel);
            CalculatorGUI.subtotalEntryMap.put(productName, entry);

        }
        
        CalculatorGUI.recalculateTotalAmountDisplay();

        CalculatorGUI.redraw();

    }

}

