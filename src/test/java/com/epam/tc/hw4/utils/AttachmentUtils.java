package com.epam.tc.hw4.utils;

import io.qameta.allure.Attachment;

public class AttachmentUtils {

    @Attachment(type = "image/png", value = "{attachmentName}")
    public static byte[] attachPngImage(String attachmentName, byte[] source) {
        return source;
    }

}
