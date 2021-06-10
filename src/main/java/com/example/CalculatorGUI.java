
/*-------------------------------------*/

/* README */

// entry


/*-------------------------------------*/

/* import */

package com.example;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

/*-------------------------------------*/

/* CalculatorGUI */

public class CalculatorGUI extends JFrame {

    /* fields */

    //GUI {

    static JFrame frame;
 
    static JPanel panel1; //left panel
    static JPanel panel2; //right panel
 
    static JTextField totalAmountDisplay;

    private static final String windowTitle  = "SuperMarket Calculator";
    private static final int    windowWidth  = 1000;
    private static final int    windowHeight = 400;

    static Color defaultBgColor = Color.gray;

    //} GUI

    //CUI {

    private static Calculator calculator;

    static Map<String, Integer> cart;

    static Map<String, SubtotalEntry> subtotalEntryMap;

    //Maps a Japanese product name to its English version.
    //This is needed since the names of the products are internally handled in English.
    private static Map<String, String> nameMap;

    //} CUI

    static {

        //CUI
        {

            CalculatorGUI.calculator = new Calculator();
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
    }

    public static void redraw() {
        CalculatorGUI.frame.repaint();
        CalculatorGUI.frame.validate();
    }

    public static void main(String args[]) {
        CalculatorGUI.frame.show();
    }

}

/*-------------------------------------*/

/* SubtotalEntry */

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
            resetButton = new JButton(new ImageIcon(ImageIO.read(new File("./icons/reset_button_2.png"))));
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

/*-------------------------------------*/

/* IconGenerator */

class IconGenerator {

    private static final String iconDirectory = "./icons/";

    static Map<String, String> iconPathMap; //maps a product name to its icon path
    static Map<String, String> miniIconPathMap; //ditto but with smaller icons

    static {

        IconGenerator.iconPathMap     = new HashMap<>();
        IconGenerator.miniIconPathMap = new HashMap<>();

        iconPathMap.put("りんご",           "apple.png");
        iconPathMap.put("みかん",           "orange.png");
        iconPathMap.put("ぶどう",           "grapes.png");
        iconPathMap.put("のり弁",           "nori_ben.png");
        iconPathMap.put("しゃけ弁",         "sake_ben.png");
        iconPathMap.put("タバコ",           "cigarette.png");
        iconPathMap.put("メンソールタバコ", "menthol_cigarette.png");
        iconPathMap.put("ライター",         "lighter.png");
        iconPathMap.put("お茶",             "tea.png");
        iconPathMap.put("コーヒー",         "coffee.png");
        iconPathMap.put("光のハンバーガー", "hamburger.png");

        iconPathMap.put("りせっと",         "all_reset_2.png");

        miniIconPathMap.put("りんご",           "apple_22.png");
        miniIconPathMap.put("みかん",           "orange_22.png");
        miniIconPathMap.put("ぶどう",           "grapes_22.png");
        miniIconPathMap.put("のり弁",           "nori_ben_22.png");
        miniIconPathMap.put("しゃけ弁",         "sake_ben_22.png");
        miniIconPathMap.put("タバコ",           "cigarette_22.png");
        miniIconPathMap.put("メンソールタバコ", "menthol_cigarette_22.png");
        miniIconPathMap.put("ライター",         "lighter_22.png");
        miniIconPathMap.put("お茶",             "tea_22.png");
        miniIconPathMap.put("コーヒー",         "coffee_22.png");
        miniIconPathMap.put("光のハンバーガー", "hamburger_22.png");

    }

    private static ImageIcon __generateIcon(String productName, Map<String, String> iconPathMap) {
        ImageIcon ret = null;
        try {
            final String iconPath = IconGenerator.iconDirectory + iconPathMap.get(productName);
            ret = new ImageIcon(ImageIO.read(new File(iconPath)));
        } catch (Exception e) {
            ;
        }
        return ret;
    }

    public static ImageIcon generateIcon(String productName) {
        return IconGenerator.__generateIcon(productName, IconGenerator.iconPathMap);
    }

    public static ImageIcon generateMiniIcon(String productName) {
        return IconGenerator.__generateIcon(productName, IconGenerator.miniIconPathMap);
    }

}

/*-------------------------------------*/

/* AllResetButtonHandler */

class AllResetButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        CalculatorGUI.cart.clear();
        for (final String key: CalculatorGUI.subtotalEntryMap.keySet()) {
            CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(key).panel);
        }
        CalculatorGUI.subtotalEntryMap.clear();
        CalculatorGUI.recalculateTotalAmountDisplay();
        CalculatorGUI.redraw();
    }

}

/*-------------------------------------*/

/* ResetButtonHandler */

class ResetButtonHandler implements ActionListener {

    private String productName;

    public ResetButtonHandler (String productName) {
        this.productName = productName;
    }

    public void actionPerformed(ActionEvent event) {
        CalculatorGUI.removeSubtotalEntry(this.productName);
        CalculatorGUI.recalculateTotalAmountDisplay();
        CalculatorGUI.redraw();
    }

}

/*-------------------------------------*/

/* IncrementButtonHandler */

class IncrementButtonHandler implements ActionListener {

    private String productName;
    private int numIncrement;

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
        }
        CalculatorGUI.recalculateTotalAmountDisplay();
        CalculatorGUI.redraw();
    }

}

/*-------------------------------------*/

/* ProductButtonHandler */

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

/*-------------------------------------*/

