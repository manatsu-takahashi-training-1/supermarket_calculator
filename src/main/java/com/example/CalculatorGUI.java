
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

    private static final String windowTitle = "SuperMarket Calculator";

    //} GUI

    //CUI {

    static Calculator calculator;

    static Map<String, Integer> cart;

    static Map<String, SubtotalEntry> subtotalEntryMap;

    //Maps a Japanese product name to its English version.
    //This is needed since the names of the products are internally handled in English.
    static Map<String, String> nameMap;

    static Map<String, String> iconPathMap; //maps a product name to its icon path

    //} CUI

    static {

        CalculatorGUI.frame = new JFrame(windowTitle);
 
        CalculatorGUI.calculator = new Calculator();
        CalculatorGUI.cart = new HashMap<>();
        CalculatorGUI.subtotalEntryMap = new HashMap<>();

        CalculatorGUI.iconPathMap = new HashMap<>();
        iconPathMap.put("りんご",           "./icons/apple_22.png");
        iconPathMap.put("みかん",           "./icons/orange_22.png");
        iconPathMap.put("ぶどう",           "./icons/grapes_22.png");
        iconPathMap.put("のり弁",           "./icons/nori_ben_22.png");
        iconPathMap.put("しゃけ弁",         "./icons/sake_ben_22.png");
        iconPathMap.put("タバコ",           "./icons/cigarette_22.png");
        iconPathMap.put("メンソールタバコ", "./icons/menthol_cigarette_22.png");
        iconPathMap.put("ライター",         "./icons/lighter_22.png");
        iconPathMap.put("お茶",             "./icons/tea_22.png");
        iconPathMap.put("コーヒー",         "./icons/coffee_22.png");
        iconPathMap.put("光のハンバーガー", "./icons/hamburger_22.png");

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

        //sets look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
 
        CalculatorGUI.totalAmountDisplay = new JTextField(25); //TODO
        CalculatorGUI.totalAmountDisplay.setEditable(false);
        CalculatorGUI.totalAmountDisplay.setFont(new Font("SansSerif", Font.BOLD, 20));
        CalculatorGUI.recalculateTotalAmountDisplay();
 
        CalculatorGUI.panel1 = new JPanel();
        CalculatorGUI.panel1.setLayout(new GridLayout(/* number of rows = */ 4, /* number of columns = */ 3));
        CalculatorGUI.panel1.setBackground(Color.gray);

        List<JButton> productButtonList = null;
        try {
            productButtonList = List.of(
                new JButton("<html>りんご<br/>(100円)</html>",              new ImageIcon(ImageIO.read(new File("./icons/apple.png")))), //TODO generates the string "<html>りんご<br/>... "
                new JButton("<html>みかん<br/>(40円)</html>",               new ImageIcon(ImageIO.read(new File("./icons/orange.png")))),
                new JButton("<html>ぶどう<br/>(150円)</html>",              new ImageIcon(ImageIO.read(new File("./icons/grapes.png")))),
                new JButton("<html>のり弁<br/>(350円)</html>",              new ImageIcon(ImageIO.read(new File("./icons/nori_ben.png")))),
                new JButton("<html>しゃけ弁<br/>(400円)</html>",            new ImageIcon(ImageIO.read(new File("./icons/sake_ben.png")))),
                new JButton("<html>タバコ<br/>(420円)</html>",              new ImageIcon(ImageIO.read(new File("./icons/cigarette.png")))),
                new JButton("<html>メンソールタバコ<br/>(440円)</html>",    new ImageIcon(ImageIO.read(new File("./icons/menthol_cigarette.png")))),
                new JButton("<html>ライター<br/>(100円)</html>",            new ImageIcon(ImageIO.read(new File("./icons/lighter.png")))),
                new JButton("<html>お茶<br/>(80円)</html>",                 new ImageIcon(ImageIO.read(new File("./icons/tea.png")))),
                new JButton("<html>コーヒー<br/>(100円)</html>",            new ImageIcon(ImageIO.read(new File("./icons/coffee.png")))),
                new JButton("<html>光のハンバーガー<br/>(？？？円)</html>", new ImageIcon(ImageIO.read(new File("./icons/hamburger.png"))))
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
            allResetButton = new JButton(new ImageIcon(ImageIO.read(new File("./icons/all_reset_2.png"))));
        } catch (Exception e) {
            ;
        }
        allResetButton.addActionListener(new AllResetButtonHandler());
        CalculatorGUI.panel1.add(allResetButton);
 
        CalculatorGUI.panel2 = new JPanel();
        CalculatorGUI.panel2.setLayout(new GridLayout(productButtonList.size() + 1, 1));
        CalculatorGUI.panel2.setBackground(Color.gray);
        CalculatorGUI.panel2.add(totalAmountDisplay);

        CalculatorGUI.frame.add(panel1, BorderLayout.LINE_START);
        CalculatorGUI.frame.add(panel2, BorderLayout.CENTER);
 
        CalculatorGUI.frame.setSize(1000, 400); //TODO

    }

    public static void recalculateTotalAmountDisplay() {
        Map<String, Integer> purchasedProductMap = new HashMap<>(); //same as `CalculatorGUI.cart` but has the English version of a product name as its key
        for (String oldProductName: CalculatorGUI.cart.keySet()) {
            final String newProductName = CalculatorGUI.nameMap.get(oldProductName);
            purchasedProductMap.put(newProductName, CalculatorGUI.cart.get(oldProductName));
        }
        totalAmountDisplay.setText(String.format("%,8d 円", CalculatorGUI.calculator.calculate(purchasedProductMap)));
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
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
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
            icon = new JLabel(new ImageIcon(ImageIO.read(new File(CalculatorGUI.iconPathMap.get(productName))))); //TODO implement iconGenerator
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
        subtotalLabel.setBackground(Color.lightGray); //TODO default bg color
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
        CalculatorGUI.cart.remove(this.productName);
        CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(this.productName).panel);
        CalculatorGUI.subtotalEntryMap.remove(this.productName);
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
            CalculatorGUI.cart.remove(this.productName); //TODO (can be grouped into a function)
            CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(this.productName).panel);
            CalculatorGUI.subtotalEntryMap.remove(this.productName);
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

