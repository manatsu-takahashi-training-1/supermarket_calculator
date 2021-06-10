package com.gui;

import java.io.File;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

class SubtotalEntry {

    private static final int labelGridWidth = 5;
    private static final int labelWidth = 16;

    public JPanel     panel;
    public JLabel     icon;
    public JButton    resetButton;
    public JButton    decrementButton;
    public JButton    incrementButton;
    public JTextField subtotalLabel;

    public void setSubtotalLabelText(String productName, int quantity) {
        this.subtotalLabel.setText( String.format("  (%d点)    %s", quantity, productName) );
    }

    public SubtotalEntry(String productName) {

        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setBorder(BorderFactory.createLineBorder(CalculatorGUI.defaultBgColor));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridder = new GridBagConstraints();

        int gridCounter = 0;

        decrementButton = new JButton("-");
        decrementButton.addActionListener(new IncrementButtonHandler(productName, /* numIncrement = */ -1));
        gridder.fill = GridBagConstraints.HORIZONTAL;
        gridder.gridx = gridCounter++;
        gridder.gridy = 0;
        gridder.gridwidth = 1;
        panel.add(this.decrementButton, gridder);

        incrementButton = new JButton("+");
        incrementButton.addActionListener(new IncrementButtonHandler(productName));
        gridder.fill = GridBagConstraints.HORIZONTAL;
        gridder.gridx = gridCounter++;
        gridder.gridy = 0;
        gridder.gridwidth = 1;
        panel.add(this.incrementButton, gridder);

        try {
            icon = new JLabel(IconGenerator.generateMiniIcon(productName));
        } catch (Exception e) {
            ;
        }
        gridder.fill = GridBagConstraints.HORIZONTAL;
        gridder.gridx = gridCounter++;
        gridder.gridy = 0;
        gridder.gridwidth = 1;
        panel.add(this.icon, gridder);

        subtotalLabel = new JTextField(SubtotalEntry.labelWidth);
        subtotalLabel.setEditable(false);
        subtotalLabel.setBackground(Color.lightGray);
        subtotalLabel.setBorder(null);
        setSubtotalLabelText(productName, 1);
        gridder.gridx = gridCounter;
        gridCounter += SubtotalEntry.labelGridWidth;
        gridder.gridy = 0;
        gridder.gridwidth = SubtotalEntry.labelGridWidth;
        panel.add(this.subtotalLabel, gridder);

        resetButton = null;
        try {
            resetButton = new JButton(IconGenerator.generateMiniIcon("りせっと"));
        } catch (Exception e) {
            ;
        }
        resetButton.setBackground(Color.lightGray);
        resetButton.setBorder(null);
        resetButton.addActionListener(new ResetButtonHandler(productName));

        gridder.gridx = gridCounter++;
        gridder.gridy = 0;
        gridder.gridwidth = 1;
        panel.add(this.resetButton, gridder);

    }

}

