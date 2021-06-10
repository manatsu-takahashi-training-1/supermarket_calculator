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
 
    static Calculator calculator;

    static Map<String, Integer> cart;

    static Map<String, subtotalEntry> subtotalEntryMap;

    static Map<String, String> iconPathMap;

    public CalculatorGUI() {
        this.calculator = new Calculator();
        this.cart = new HashMap<>();
        this.subtotalEntryMap = new HashMap<>();

        this.iconPathMap = new HashMap<>();
//         iconPathMap.put("りんご", "./icons/apple_22.png");
//         iconPathMap.put("みかん", "./icons/orange_22.png");
//         iconPathMap.put("ぶどう", "./icons/grapes_22.png");
//         iconPathMap.put("のり弁", "./icons/nori_ben_22.png");
//         iconPathMap.put("しゃけ弁", "./icons/sake_ben_22.png");
//         iconPathMap.put("タバコ", "./icons/cigarette_22.png");
//         iconPathMap.put("メンソールタバコ", "./icons/menthol_cigarette_22.png");
//         iconPathMap.put("ライター", "./icons/lighter_22.png");
//         iconPathMap.put("お茶", "./icons/tea_22.png");
//         iconPathMap.put("コーヒー", "./icons/coffee_22.png");
//         iconPathMap.put("光のハンバーガー", "./icons/hamburger_22.png");

        iconPathMap.put("apple", "./icons/apple_22.png");
        iconPathMap.put("orange", "./icons/orange_22.png");
        iconPathMap.put("grape", "./icons/grapes_22.png");
        iconPathMap.put("noriBento", "./icons/nori_ben_22.png");
        iconPathMap.put("salmonBento", "./icons/sake_ben_22.png");
        iconPathMap.put("cigarette", "./icons/cigarette_22.png");
        iconPathMap.put("mentholCigarette", "./icons/menthol_cigarette_22.png");
        iconPathMap.put("lighter", "./icons/lighter_22.png");
        iconPathMap.put("tea", "./icons/tea_22.png");
        iconPathMap.put("coffee", "./icons/coffee_22.png");
        iconPathMap.put("flashHamburger", "./icons/hamburger_22.png");
    }

    private static class subtotalEntry {
        public JPanel panel;
        public JLabel icon;
        public JButton resetButton;
        public JButton decrementButton;
        public JButton incrementButton;
        public JTextField subtotalLabel;
    }

    private static class AllResetButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CalculatorGUI.totalAmountDisplay.setText(String.format("%,8d 円", 0));
            CalculatorGUI.cart.clear();
            for (final String key: CalculatorGUI.subtotalEntryMap.keySet()) {
                CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(key).panel);
            }
            CalculatorGUI.subtotalEntryMap.clear();
            CalculatorGUI.frame.repaint();
        }
    }

    private class ResetButton implements ActionListener {
        private String productName;
        public ResetButton (String productName) {
            this.productName = productName;
        }
        public void actionPerformed(ActionEvent e) {
            final String productName = this.productName;
            CalculatorGUI.cart.remove(productName);
            CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(productName).panel);
            CalculatorGUI.subtotalEntryMap.remove(productName);
            setTotalAmountDisplay();
            CalculatorGUI.frame.repaint();
            CalculatorGUI.frame.validate();
        }
    }


    private class IncrementButton implements ActionListener {
        private String productName;
        private int incrementRange;
        public IncrementButton(String productName) {
            this.productName = productName;
            this.incrementRange = 1;
        }
        public IncrementButton(String productName, int incrementRange) {
            this.productName = productName;
            this.incrementRange = incrementRange;
        }
        public void actionPerformed(ActionEvent e) {
            String productName = this.productName;
            int quantity = CalculatorGUI.cart.get(productName);
            if (quantity + this.incrementRange <= 0) {
                CalculatorGUI.cart.remove(productName);
                CalculatorGUI.panel2.remove(CalculatorGUI.subtotalEntryMap.get(productName).panel);
                CalculatorGUI.subtotalEntryMap.remove(productName);
            } else {
                quantity += this.incrementRange;
                CalculatorGUI.cart.put(productName, quantity);
                setSubtotalLabelText(productName, quantity);
            }
            setTotalAmountDisplay();
            CalculatorGUI.frame.repaint();
            CalculatorGUI.frame.validate();
        }
    }

    private void setTotalAmountDisplay() {
        totalAmountDisplay.setText(String.format("%,8d 円", this.calculator.calculate(this.cart)));
    }

    private static void setSubtotalLabelText(String productName, int quantity) {
        subtotalEntryMap.get(productName).subtotalLabel.setText(
            String.format("  (%d点)    %s", quantity, productName)
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
        totalAmountDisplay.setText(String.format("%,8d 円", 0));
        totalAmountDisplay.setEditable(false);
        totalAmountDisplay.setFont(new Font("SansSerif", Font.BOLD, 20));
 
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(/* number of rows = */ 4, /* number of columns = */ 3));
        panel1.setBackground(Color.gray);

        List<JButton> productButtonList = null;
        try {
            productButtonList = List.of(
//                 new JButton("<html>りんご<br/>(100円)</html>", new ImageIcon(ImageIO.read(new File("./icons/apple.png")))),
//                 new JButton("<html>みかん<br/>(40円)</html>", new ImageIcon(ImageIO.read(new File("./icons/orange.png")))),
//                 new JButton("<html>ぶどう<br/>(150円)</html>", new ImageIcon(ImageIO.read(new File("./icons/grapes.png")))),
//                 new JButton("<html>のり弁<br/>(350円)</html>", new ImageIcon(ImageIO.read(new File("./icons/nori_ben.png")))),
//                 new JButton("<html>しゃけ弁<br/>(400円)</html>", new ImageIcon(ImageIO.read(new File("./icons/sake_ben.png")))),
//                 new JButton("<html>タバコ<br/>(420円)</html>", new ImageIcon(ImageIO.read(new File("./icons/cigarette.png")))),
//                 new JButton("<html>メンソールタバコ<br/>(440円)</html>", new ImageIcon(ImageIO.read(new File("./icons/menthol_cigarette.png")))),
//                 new JButton("<html>ライター<br/>(100円)</html>", new ImageIcon(ImageIO.read(new File("./icons/lighter.png")))),
//                 new JButton("<html>お茶<br/>(80円)</html>", new ImageIcon(ImageIO.read(new File("./icons/tea.png")))),
//                 new JButton("<html>コーヒー<br/>(100円)</html>", new ImageIcon(ImageIO.read(new File("./icons/coffee.png")))),
//                 new JButton("<html>光のハンバーガー<br/>(？？？円)</html>", new ImageIcon(ImageIO.read(new File("./icons/hamburger.png"))))

                new JButton("<html>apple<br/>(100円)</html>", new ImageIcon(ImageIO.read(new File("./icons/apple.png")))),
                new JButton("<html>orange<br/>(40円)</html>", new ImageIcon(ImageIO.read(new File("./icons/orange.png")))),
                new JButton("<html>grape<br/>(150円)</html>", new ImageIcon(ImageIO.read(new File("./icons/grapes.png")))),
                new JButton("<html>noriBento<br/>(350円)</html>", new ImageIcon(ImageIO.read(new File("./icons/nori_ben.png")))),
                new JButton("<html>salmonBento<br/>(400円)</html>", new ImageIcon(ImageIO.read(new File("./icons/sake_ben.png")))),
                new JButton("<html>cigarette<br/>(420円)</html>", new ImageIcon(ImageIO.read(new File("./icons/cigarette.png")))),
                new JButton("<html>mentholCigarette<br/>(440円)</html>", new ImageIcon(ImageIO.read(new File("./icons/menthol_cigarette.png")))),
                new JButton("<html>lighter<br/>(100円)</html>", new ImageIcon(ImageIO.read(new File("./icons/lighter.png")))),
                new JButton("<html>tea<br/>(80円)</html>", new ImageIcon(ImageIO.read(new File("./icons/tea.png")))),
                new JButton("<html>coffee<br/>(100円)</html>", new ImageIcon(ImageIO.read(new File("./icons/coffee.png"))))
                // new JButton("<html>flashHamburger<br/>(？？？円)</html>", new ImageIcon(ImageIO.read(new File("./icons/hamburger.png"))))
            );
        } catch (Exception e) {
            ;
        }

        for (JButton b: productButtonList) {
            b.addActionListener(c);
            panel1.add(b);
        }

        JButton resetButton = null;
        try {
            resetButton = new JButton(new ImageIcon(ImageIO.read(new File("./icons/all_reset_2.png"))));
        } catch (Exception e) {
            ;
        }
        resetButton.addActionListener(new AllResetButton());
        panel1.add(resetButton);
 
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(productButtonList.size() + 1, 1));
        panel2.setBackground(Color.gray);
        panel2.add(totalAmountDisplay);

        frame.add(panel1, BorderLayout.LINE_START);
        frame.add(panel2, BorderLayout.CENTER);
 
        frame.setSize(1000, 400);
        frame.show();

    }

    public void actionPerformed(ActionEvent e) {

        String productName = e.getActionCommand();

        var pattern = java.util.regex.Pattern.compile("(?<=<html>)[^<]+");
        var matcher = pattern.matcher(productName);
        if (matcher.find()) {
            productName = matcher.group();
        }

        if (this.cart.containsKey(productName)) {
            final int quantity = this.cart.get(productName) + 1;
            this.cart.put(productName, quantity);
            setSubtotalLabelText(productName, quantity);
        } else {
            this.cart.put(productName, 1);

            subtotalEntry entry = new subtotalEntry();

            entry.panel = new JPanel();
            entry.panel.setBackground(Color.lightGray);
            entry.panel.setBorder(BorderFactory.createLineBorder(Color.gray));
            entry.panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            int gridCounter = 0;

            entry.decrementButton = new JButton("-");
            entry.decrementButton.addActionListener(new IncrementButton(productName, /* incrementRange = */ -1));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = gridCounter++;
            c.gridy = 0;
            c.gridwidth = 1;
            entry.panel.add(entry.decrementButton, c);

            entry.incrementButton = new JButton("+");
            entry.incrementButton.addActionListener(new IncrementButton(productName));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = gridCounter++;
            c.gridy = 0;
            c.gridwidth = 1;
            entry.panel.add(entry.incrementButton, c);

            try {
                entry.icon = new JLabel(new ImageIcon(ImageIO.read(new File(iconPathMap.get(productName)))));
            } catch (Exception ex) {
                ;
            }
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = gridCounter++;
            c.gridy = 0;
            c.gridwidth = 1;
            entry.panel.add(entry.icon, c);

            entry.subtotalLabel = new JTextField(15);
            entry.subtotalLabel.setEditable(false);
            entry.subtotalLabel.setBackground(Color.lightGray);
            entry.subtotalLabel.setBorder(null);
            final int labelWidth = 5;
            c.gridx = gridCounter;
            gridCounter += labelWidth;
            c.gridy = 0;
            c.gridwidth = labelWidth;
            entry.panel.add(entry.subtotalLabel, c);

            entry.resetButton = null;
            try {
                entry.resetButton = new JButton(new ImageIcon(ImageIO.read(new File("./icons/reset_button_2.png"))));
            } catch (Exception ex) {
                ;
            }
            entry.resetButton.setBackground(Color.lightGray);
            entry.resetButton.setBorder(null);
            entry.resetButton.addActionListener(new ResetButton(productName));

            c.gridx = gridCounter++;
            c.gridy = 0;
            c.gridwidth = 1;
            entry.panel.add(entry.resetButton, c);

            panel2.add(entry.panel);
            this.subtotalEntryMap.put(productName, entry);

            setSubtotalLabelText(productName, /* quantity = */ 1);
        }
        
        setTotalAmountDisplay();

        frame.validate();

    }

}

