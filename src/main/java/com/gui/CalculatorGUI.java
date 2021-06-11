package com.gui;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

import com.example.CalcInterface;
import com.example.SelectCalcType;

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

    private static Map<String, JButton> productButtonMap;

    private static Timer flashButtonTimer;

    //} GUI

    //CUI {

    private static boolean isTimeSale = false;

    public static Map<String, Integer> cart;

    public static Map<String, SubtotalEntry> subtotalEntryMap;

    //Maps a Japanese product name to its English version.
    //This is needed since the names of the products are internally handled in English.
    private static Map<String, String> nameMap;

    //} CUI

    static {

        //CUI
        {

            CalculatorGUI.productButtonMap = new HashMap<>();

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

            try {
                CalculatorGUI.productButtonMap.put("りんご", new JButton("<html>りんご<br/>(100円)</html>", IconGenerator.generateIcon("りんご")));
                CalculatorGUI.productButtonMap.put("みかん", new JButton("<html>みかん<br/>(40円)</html>", IconGenerator.generateIcon("みかん")));
                CalculatorGUI.productButtonMap.put("ぶどう", new JButton("<html>ぶどう<br/>(150円)</html>", IconGenerator.generateIcon("ぶどう")));
                CalculatorGUI.productButtonMap.put("のり弁", new JButton("<html>のり弁<br/>(350円)</html>", IconGenerator.generateIcon("のり弁")));
                CalculatorGUI.productButtonMap.put("しゃけ弁", new JButton("<html>しゃけ弁<br/>(400円)</html>", IconGenerator.generateIcon("しゃけ弁")));
                CalculatorGUI.productButtonMap.put("タバコ", new JButton("<html>タバコ<br/>(420円)</html>", IconGenerator.generateIcon("タバコ")));
                CalculatorGUI.productButtonMap.put("メンソールタバコ", new JButton("<html>メンソールタバコ<br/>(440円)</html>", IconGenerator.generateIcon("メンソールタバコ")));
                CalculatorGUI.productButtonMap.put("ライター", new JButton("<html>ライター<br/>(100円)</html>", IconGenerator.generateIcon("ライター")));
                CalculatorGUI.productButtonMap.put("お茶", new JButton("<html>お茶<br/>(80円)</html>", IconGenerator.generateIcon("お茶")));
                CalculatorGUI.productButtonMap.put("コーヒー", new JButton("<html>コーヒー<br/>(100円)</html>", IconGenerator.generateIcon("コーヒー")));
            } catch (Exception e) {
                ;
            }

            final int numPureProductButton = CalculatorGUI.productButtonMap.size();

            for (JButton productButton: CalculatorGUI.productButtonMap.values()) {
                productButton.addActionListener(new ProductButtonHandler());
                CalculatorGUI.panel1.add(productButton);
            }

            JButton flashHamburgerButton = new JButton("<html>光のハンバーガー<br/>(？？？円)</html>", IconGenerator.generateIcon("光のハンバーガー"));
            flashHamburgerButton.addActionListener(new FlashHamburgerButtonHandler());
            CalculatorGUI.productButtonMap.put("光のハンバーガー", flashHamburgerButton);
            CalculatorGUI.panel1.add(flashHamburgerButton);

            JButton allResetButton = null;
            try {
                allResetButton = new JButton(IconGenerator.generateIcon("りせっと"));
            } catch (Exception e) {
                ;
            }
            allResetButton.addActionListener(new AllResetButtonHandler());
            CalculatorGUI.productButtonMap.put("りせっと", allResetButton);
            CalculatorGUI.panel1.add(allResetButton);

            for (JButton b: CalculatorGUI.productButtonMap.values()) {
                b.setOpaque(true); //needed to make `setBackground()` work (ref: |https://stackoverflow.com/questions/4990952/why-does-setbackground-to-jbutton-does-not-work|)
                b.setBackground(null);
            }

            //} panel1
     
            //panel2
            CalculatorGUI.panel2 = new JPanel();
            CalculatorGUI.panel2.setLayout(new GridLayout(numPureProductButton + 1, 1));
            CalculatorGUI.panel2.setBackground(CalculatorGUI.defaultBgColor);
            CalculatorGUI.panel2.add(totalAmountDisplay);

            CalculatorGUI.frame.add(panel1, BorderLayout.LINE_START);
            CalculatorGUI.frame.add(panel2, BorderLayout.CENTER);
     
            ActionListener timerAction = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    for (JButton b: CalculatorGUI.productButtonMap.values()) {
                        final Color currentColor = b.getBackground();
                        if (currentColor == Color.red) {
                            b.setBackground(Color.blue);
                        } else if (currentColor == Color.blue) {
                            b.setBackground(Color.orange);
                        } else if (currentColor == Color.orange) {
                            b.setBackground(Color.green);
                        } else {
                            b.setBackground(Color.red);
                        }
                    }
                    CalculatorGUI.redraw();
                }
            };
            CalculatorGUI.flashButtonTimer = new Timer(/* 60sec / 90bpm * 1000 = */ 666, timerAction);
            CalculatorGUI.flashButtonTimer.setRepeats(true);

        }

    }

    public static void recalculateTotalAmountDisplay() {
        Map<String, Integer> purchasedProductMap = new HashMap<>(); //same as `CalculatorGUI.cart` but has the English version of a product name as its key
        for (String oldProductName: CalculatorGUI.cart.keySet()) {
            final String newProductName = CalculatorGUI.nameMap.get(oldProductName);
            purchasedProductMap.put(newProductName, CalculatorGUI.cart.get(oldProductName));
        }
        totalAmountDisplay.setText(
            String.format(
                "%,8d 円",
                (CalculatorGUI.isTimeSale ? SelectCalcType.FLASH_HUM : SelectCalcType.NORMAL).getCalcInterface().calculate(purchasedProductMap)
            )
        );
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

    public static void toggleTimeSale() {

        if (CalculatorGUI.isTimeSale) {
            CalculatorGUI.isTimeSale = false;
            CalculatorGUI.flashButtonTimer.stop();
            for (JButton b: CalculatorGUI.productButtonMap.values()) {
                b.setBackground(null);
            }
        } else {
            CalculatorGUI.isTimeSale = true;
            flashButtonTimer.start();
        }
        BGM.toggle();
        CalculatorGUI.recalculateTotalAmountDisplay();
    }

    public static void redraw() {
        CalculatorGUI.frame.repaint();
        CalculatorGUI.frame.validate();
    }

    public static void main(String args[]) {
        CalculatorGUI.frame.show();
    }

}

