package com.example;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

public class CalculatorGUI extends JFrame implements ActionListener {

    static JFrame frame;
 
    static JTextField totalAmountDisplay;

    static JPanel panel1;
    static JPanel panel2;
 
    static CalculatorMock calculator;

    static Map<String, Integer> cart;

    static Map<String, JTextField> subtotalLabelMap;

    public CalculatorGUI() {
        this.calculator = new CalculatorMock();
        this.cart = new HashMap<>();
        this.subtotalLabelMap = new HashMap<>();
    }

    private static void setSubtotalLabelText(String productName, int quantity) {
        subtotalLabelMap.get(productName).setText(
            String.format("(%2d個)    %s", quantity, productName)
        );
    }

    public static void main(String args[]) {

        CalculatorGUI c = new CalculatorGUI();
 
        frame = new JFrame("Super Market Calculator");
 
        //sets look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
 
        totalAmountDisplay = new JTextField(25);
        totalAmountDisplay.setText("0");
        totalAmountDisplay.setEditable(false);
 
//         JButton b_apple = null;
//         try {
//             b_apple = new JButton("りんご", new ImageIcon(ImageIO.read(new File("./icons/apple.png"))));
//         } catch (Exception e) {
//             ;
//         }

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(/* number of rows = */ 4, /* number of columns = */ 3));
        panel1.setBackground(Color.gray);

        List<JButton> productButtonList = List.of(
            new JButton("りんご"),
            new JButton("みかん"),
            new JButton("ぶどう"),
            new JButton("のり弁"),
            new JButton("しゃけ弁"),
            new JButton("タバコ"),
            new JButton("メンソールタバコ"),
            new JButton("ライター"),
            new JButton("お茶"),
            new JButton("コーヒー")
        );
        for (JButton b: productButtonList) {
            b.addActionListener(c);
            panel1.add(b);
        }
 
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(productButtonList.size() + 1, 1));
        panel2.setBackground(Color.gray);
        panel2.add(totalAmountDisplay);

        frame.add(panel1, BorderLayout.LINE_START);
        frame.add(panel2, BorderLayout.LINE_END);
 
        frame.setSize(780, 400);
        frame.show();

    }

    public void actionPerformed(ActionEvent e) {

        String productName = e.getActionCommand();

        if (this.cart.containsKey(productName)) {
            final int quantity = this.cart.get(productName) + 1;
            this.cart.put(productName, quantity);
            setSubtotalLabelText(productName, quantity);
        } else {
            this.cart.put(productName, 1);
            JTextField subtotalLabel = new JTextField(25);
            this.subtotalLabelMap.put(productName, subtotalLabel);
            panel2.add(subtotalLabel);
            setSubtotalLabelText(productName, /* quantity = */ 1);
        }
        
        totalAmountDisplay.setText(String.valueOf(this.calculator.calculate(this.cart)));

        frame.validate();

//         JTextField f = new JTextField(25);
//         f.setText(this.cart.toString());
//         panel1.add(f);
//         panel1.repaint();
//         frame.repaint();
//         panel1.validate();
//         frame.validate();
 
//         // if the value is a number
//         if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
//             // if operand is present then add to second no
//             if (!s1.equals(""))
//                 s2 = s2 + s;
//             else
//                 s0 = s0 + s;
//  
//             // set the value of text
//             totalAmountDisplay.setText(s0 + s1 + s2);
//         }
//         else if (s.charAt(0) == 'C') {
//             // clear the one letter
//             s0 = s1 = s2 = "";
//  
//             // set the value of text
//             totalAmountDisplay.setText(s0 + s1 + s2);
//         }
//         else if (s.charAt(0) == '=') {
//  
//             double te;
//  
//             // store the value in 1st
//             if (s1.equals("+"))
//                 te = (Double.parseDouble(s0) + Double.parseDouble(s2));
//             else if (s1.equals("-"))
//                 te = (Double.parseDouble(s0) - Double.parseDouble(s2));
//             else if (s1.equals("/"))
//                 te = (Double.parseDouble(s0) / Double.parseDouble(s2));
//             else
//                 te = (Double.parseDouble(s0) * Double.parseDouble(s2));
//  
//             // set the value of text
//             totalAmountDisplay.setText(s0 + s1 + s2 + "=" + te);
//  
//             // convert it to string
//             s0 = Double.toString(te);
//  
//             s1 = s2 = "";
//         }
//         else {
//             // if there was no operand
//             if (s1.equals("") || s2.equals(""))
//                 s1 = s; //operatorの変更
//             // else evaluate
//             else {
//                 double te;
//  
//                 // store the value in 1st
//                 if (s1.equals("+"))
//                     te = (Double.parseDouble(s0) + Double.parseDouble(s2));
//                 else if (s1.equals("-"))
//                     te = (Double.parseDouble(s0) - Double.parseDouble(s2));
//                 else if (s1.equals("/"))
//                     te = (Double.parseDouble(s0) / Double.parseDouble(s2));
//                 else
//                     te = (Double.parseDouble(s0) * Double.parseDouble(s2));
//  
//                 // convert it to string
//                 s0 = Double.toString(te);
//  
//                 // place the operator
//                 s1 = s;
//  
//                 // make the operand blank
//                 s2 = "";
//             }
//  
//             // set the value of text
//             totalAmountDisplay.setText(s0 + s1 + s2);
//         }

    }

}
