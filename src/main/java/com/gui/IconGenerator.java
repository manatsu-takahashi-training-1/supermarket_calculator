package com.gui;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

class IconGenerator {

    private static final String iconDirectory = "./icons/";

    private static Map<String, String> iconPathMap; //maps a product name to its icon path
    private static Map<String, String> miniIconPathMap; //ditto but with smaller icons

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

        miniIconPathMap.put("りせっと",         "reset_button_2.png");

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

