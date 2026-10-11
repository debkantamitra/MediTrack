package com.airtribe.meditrack.util;

public final class LazyIdGeneratorExample {
    private static LazyIdGeneratorExample instance;

    private LazyIdGeneratorExample() {
    }

    public static LazyIdGeneratorExample getInstance() {
        if (instance == null) {
            instance = new LazyIdGeneratorExample();
        }
        return instance;
    }
}
