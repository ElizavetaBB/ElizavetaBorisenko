package com.epam.tc.hw5.context;

import java.util.HashMap;
import java.util.Map;

public final class TestContext {

    private static Map<String, Object> context = new HashMap<>();
    private static TestContext instance;

    private TestContext() {}

    public void setObject(final String key, Object value) {
        context.put(key, value);
    }

    public <T> T getObject(final String key, Class<T> clazz) {
        return (clazz.cast(context.get(key)));
    }

    public void clearContext() {
        context.clear();
        instance = null;
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
}
