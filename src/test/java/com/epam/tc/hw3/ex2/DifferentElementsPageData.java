package com.epam.tc.hw3.ex2;

import java.util.List;
import java.util.stream.Collectors;

public class DifferentElementsPageData {

    public static final String DIFFERENT_ELEMENTS_PAGE_TITLE = "Different Elements";
    public static final List<String> CHECKBOXES_NAMES = List.of(
            "Water", "Wind"
    );
    public static final String RADIO_BUTTON_NAME = "Selen";
    public static final String DROPDOWN_NAME = "Yellow";
    public static final List<String> CHECKBOXES_LOG =
            CHECKBOXES_NAMES.stream().map(x -> String.format(".*%s.*true", x)).collect(Collectors.toList());
    public static final String RADIO_LOG = ".*metal.*" + RADIO_BUTTON_NAME;
    public static final String DROPDOWN_LOG = ".*Colors.*" + DROPDOWN_NAME;

}
