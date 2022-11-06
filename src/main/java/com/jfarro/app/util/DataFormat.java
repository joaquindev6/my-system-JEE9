package com.jfarro.app.util;

public class DataFormat {

    public static String formatTextMayusMinus(String[] datas) {
        String data = "";
        for (String d: datas) {
            data += " " + (d.substring(0, 1).toUpperCase() + d.substring(1).toLowerCase());
        }
        return data.trim();
    }
}
