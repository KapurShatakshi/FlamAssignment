package com.example.edgeviewer;

import java.util.concurrent.atomic.AtomicReference;

public class ProcessedFrameHolder {
    // Use AtomicReference to safely hold the latest frame bytes
    // This is safe for cross-thread access.
    private static final AtomicReference<byte[]> latestFrameBytes = new AtomicReference<>();
    private static int width = 0;
    private static int height = 0;

    public static void updateFrame(byte[] bytes, int w, int h) {
        latestFrameBytes.set(bytes);
        width = w;
        height = h;
    }

    public static byte[] getLatestFrame() {
        return latestFrameBytes.get();
    }

    public static int getWidth() { return width; }
    public static int getHeight() { return height; }
}