package com.gui;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

import com.example.Calculator;
import com.gui.CalculatorMock;

public class CalculatorGUI extends JFrame {

    //GUI {

    private static JFrame frame;
 
    private static JPanel panel1; //left panel
    public static JPanel panel2; //right panel
 
    private static JTextField totalAmountDisplay;

    private static final String windowTitle  = "SuperMarket Calculator";
    private static final int    windowWidth  = 1000;
    private static final int    windowHeight = 400;

    public static final Color defaultBgColor = Color.gray;

    //} GUI

    //CUI {

    private static Calculator calculator;
//     private static CalculatorMock calculator;

    public static Map<String, Integer> cart;

    public static Map<String, SubtotalEntry> subtotalEntryMap;

    //Maps a Japanese product name to its English version.
    //This is needed since the names of the products are internally handled in English.
    private static Map<String, String> nameMap;

    //} CUI

    static {

        //CUI
        {

            CalculatorGUI.calculator = new Calculator();
//             CalculatorGUI.calculator = new CalculatorMock();

            CalculatorGUI.cart = new HashMap<>();
            CalculatorGUI.subtotalEntryMap = new HashMap<>();

            CalculatorGUI.nameMap = new HashMap<>();
            nameMap.put("りんご",           "apple");
            nameMap.put("みかん",           "orange");
            nameMap.put("ぶどう",           "grape");
            nameMap.put("のり弁",           "noriBento");
            nameMap.put("しゃけ弁",         "salmonBento");
            nameMap.put("タバコ",           "cigarette");
            nameMap.put("メンソールタバコ", "mentholCigarette");
            nameMap.put("ライター",         "lighter");
            nameMap.put("お茶",             "tea");
            nameMap.put("コーヒー",         "coffee");
            nameMap.put("光のハンバーガー", "flashHamburger");

        }

        //GUI
        {

            //sets look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
     
            CalculatorGUI.frame = new JFrame(windowTitle);
            CalculatorGUI.frame.setSize(CalculatorGUI.windowWidth, windowHeight);
     
            CalculatorGUI.totalAmountDisplay = new JTextField(10);
            CalculatorGUI.totalAmountDisplay.setEditable(false);
            CalculatorGUI.totalAmountDisplay.setFont(new Font("SansSerif", Font.BOLD, 20));
            CalculatorGUI.recalculateTotalAmountDisplay();

            //panel1 {
     
            CalculatorGUI.panel1 = new JPanel();
            CalculatorGUI.panel1.setLayout(new GridLayout(/* number of rows = */ 4, /* number of columns = */ 3));
            CalculatorGUI.panel1.setBackground(CalculatorGUI.defaultBgColor);

            List<JButton> productButtonList = null;
            try {
                productButtonList = List.of(
                    new JButton("<html>りんご<br/>(100円)</html>",              IconGenerator.generateIcon("りんご")),
                    new JButton("<html>みかん<br/>(40円)</html>",               IconGenerator.generateIcon("みかん")),
                    new JButton("<html>ぶどう<br/>(150円)</html>",              IconGenerator.generateIcon("ぶどう")),
                    new JButton("<html>のり弁<br/>(350円)</html>",              IconGenerator.generateIcon("のり弁")),
                    new JButton("<html>しゃけ弁<br/>(400円)</html>",            IconGenerator.generateIcon("しゃけ弁")),
                    new JButton("<html>タバコ<br/>(420円)</html>",              IconGenerator.generateIcon("タバコ")),
                    new JButton("<html>メンソールタバコ<br/>(440円)</html>",    IconGenerator.generateIcon("メンソールタバコ")),
                    new JButton("<html>ライター<br/>(100円)</html>",            IconGenerator.generateIcon("ライター")),
                    new JButton("<html>お茶<br/>(80円)</html>",                 IconGenerator.generateIcon("お茶")),
                    new JButton("<html>コーヒー<br/>(100円)</html>",            IconGenerator.generateIcon("コーヒー")),
                    new JButton("<html>光のハンバーガー<br/>(？？？円)</html>", IconGenerator.generateIcon("光のハンバーガー"))
                );
            } catch (Exception e) {
                ;
            }

            for (JButton productButton: productButtonList) {
                productButton.addActionListener(new ProductButtonHandler());
                CalculatorGUI.panel1.add(productButton);
            }

            JButton allResetButton = null;
            try {
                allResetButton = new JButton(IconGenerator.generateIcon("りせっと"));
            } catch (Exception e) {
                ;
            }
            allResetButton.addActionListener(new AllResetButtonHandler());
            CalculatorGUI.panel1.add(allResetButton);

            //} panel1
     
            //panel2
            CalculatorGUI.panel2 = new JPanel();
            CalculatorGUI.panel2.setLayout(new GridLayout(productButtonList.size() + 1, 1));
            CalculatorGUI.panel2.setBackground(CalculatorGUI.defaultBgColor);
            CalculatorGUI.panel2.add(totalAmountDisplay);

            CalculatorGUI.frame.add(panel1, BorderLayout.LINE_START);
            CalculatorGUI.frame.add(panel2, BorderLayout.CENTER);
     
        }

    }

    public static void recalculateTotalAmountDisplay() {
        Map<String, Integer> purchasedProductMap = new HashMap<>(); //same as `CalculatorGUI.cart` but has the English version of a product name as its key
        for (String oldProductName: CalculatorGUI.cart.keySet()) {
            final String newProductName = CalculatorGUI.nameMap.get(oldProductName);
            purchasedProductMap.put(newProductName, CalculatorGUI.cart.get(oldProductName));
        }
        totalAmountDisplay.setText(String.format("%,8d 円", CalculatorGUI.calculator.calculate(purchasedProductMap)));
    }
    
    public static void removeSubtotalEntry(String productName) {
        CalculatorGUI.cart.remove(productName);
        CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(productName).panel);
        CalculatorGUI.subtotalEntryMap.remove(productName);
        CalculatorGUI.recalculateTotalAmountDisplay();
        CalculatorGUI.redraw();
    }

    public static void removeAllSubtotalEntry() {
        CalculatorGUI.cart.clear();
        for (final String key: CalculatorGUI.subtotalEntryMap.keySet()) {
            CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(key).panel);
        }
        CalculatorGUI.subtotalEntryMap.clear();
        CalculatorGUI.recalculateTotalAmountDisplay();
        CalculatorGUI.redraw();
    }

    public static void redraw() {
        CalculatorGUI.frame.repaint();
        CalculatorGUI.frame.validate();
    }

    public static void main(String args[]) {
        CalculatorGUI.frame.show();
    }

}

