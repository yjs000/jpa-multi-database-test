package com.example.demo.common.util;

import java.lang.reflect.Method;
import java.util.List;

public class CmmnUtil {
    public static String fullField(Class<?> clazz, List<String> field) {
        String returnData = "";
        field = deepSerarch(clazz, field);
        field.add(field.get(0));
        field.remove(0);
        // Collections.reverse(field);
        String tmp = String.join("_", field);
        for (int i = 0; i < tmp.length(); i++) {
            if (i == 0) {
                returnData += (tmp.charAt(i) + "").toLowerCase();
                continue;
            }
            if (tmp.charAt(i - 1) == '_') {
                returnData += (tmp.charAt(i) + "").toUpperCase();
                continue;
            }
            returnData += tmp.charAt(i);
        }

        return returnData.replaceAll("_", "");
    }

    public static List<String> fields(Class<?> clazz, List<String> field) {
        field = deepSerarch(clazz, field);
        field.add(field.get(0));
        field.remove(0);
        return field;
    }

    private static List<String> deepSerarch(Class<?> clazz, List<String> field) {
        for (Method f : clazz.getDeclaredMethods()) {
            if (f.getName().toString().indexOf("set") == 0) {
                if (f.getParameters()[0].toString().indexOf("kr.co.neighbor21") != -1) {
                    field.add(f.getName().substring(3));
                    field = CmmnUtil.deepSerarch(f.getParameterTypes()[0], field);

                    for (String s : field) {
                        if (s.equals(f.getName().substring(3))) {
                            return field;
                        }
                    }

                } else {
                    if ((f.getName().toLowerCase().charAt(3) + f.getName().substring(4)).equals(field.get(0))) {
                        return field;
                    }
                }
            }
        }

        field.remove(field.size() - 1);
        return field;
    }
}
