package com.epam.tc.hw2.ex2;

import java.util.List;
import java.util.stream.Collectors;

public class DifferentElementsPageData {

    public static String propertiesPath = "src/test/resources/hw2/test.properties";
    public static String differentElementsPageTitle = "Different Elements";
    public static List<String> checkboxesNames = List.of(
            "Water", "Wind"
    );
    public static String radioButtonName = "Selen";
    public static String dropdownName = "Yellow";
    public static List<String> checkboxesLog =
            checkboxesNames.stream().map(x -> String.format(".*%s.*true", x)).collect(Collectors.toList());
    public static String radioLog = ".*metal.*" + radioButtonName;
    public static String dropdownLog = ".*Colors.*" + dropdownName;

}
