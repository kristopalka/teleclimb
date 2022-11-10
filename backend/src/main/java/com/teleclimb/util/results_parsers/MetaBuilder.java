package com.teleclimb.util.results_parsers;

import com.teleclimb.dto.model.Meta;

import java.text.DecimalFormat;

public class MetaBuilder {
    public static final String resultStr = "Wynik";
    public static final String placeStr = "Miejsce";
    public static final String separator = ";";
    public static final String empty = "-";

    public static Meta build(String roundName, String routeName, String name, Object value) {
        return new Meta(buildKey(roundName, routeName, name), valueToString(value));
    }

    private static String buildKey(String roundName, String routeName, String name) {
        return roundName + separator + routeName + separator + name;
    }

    private static String valueToString(Object o) {
        if (o == null) return empty;
        if (o instanceof Double) return new DecimalFormat("#.0###").format(o);
        return o.toString();
    }
}
