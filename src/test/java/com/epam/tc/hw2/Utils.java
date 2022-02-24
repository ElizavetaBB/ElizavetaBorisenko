package com.epam.tc.hw2;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static final String PAGE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    public static final String HOME_PAGE_TITLE = "Home Page";
    public static final String LOGIN = "Roman";
    public static final String PASSWORD = "Jdi1234";
    public static final String USERNAME = "ROMAN IOVLEV";
    public static final List<String> headerItems = List.of(
            "HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
    );
    public static final int IMAGES_NUMBER = 4;
    public static final List<String> iconsText = List.of(
            "To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
    );
    public static final String FRAME_BUTTON = "Frame Button";
    public static final List<String> leftSectionItems = List.of(
            "Home", "Contact form", "Service", "Metals & Colors", "Elements packs"
    );
    public static final String DIFFERENT_ELEMENTS_PAGE_TITLE = "Different Elements";
    public static final List<String> checkboxesNames = List.of(
            "Water", "Wind"
    );
    public static final String RADIOBUTTON_NAME = "Selen";
    public static final String DROPDOWN_NAME = "Yellow";
    public static final List<String> checkboxesLog =
            checkboxesNames.stream().map(x -> String.format(".*%s.*true", x)).collect(Collectors.toList());
    public static final String radioLog = ".*metal.*" + RADIOBUTTON_NAME;
    public static final String dropdownLog = ".*Colors.*" + DROPDOWN_NAME;
}
