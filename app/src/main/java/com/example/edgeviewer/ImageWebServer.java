package com.example.edgeviewer;

import android.util.Log;
import fi.iki.elonen.NanoHTTPD;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageWebServer extends NanoHTTPD {

    private static final String TAG = "ImageWebServer";
    private static final int PORT = 8080;

    // The server needs to know where to get the image
    // In our case, it's the static ProcessedFrameHolder class

    public ImageWebServer() throws IOException {
        super(PORT);
        // Start the server immediately when the class is initialized
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        Log.d(TAG, "Server running! Access at http://localhost:" + PORT + " or http://[Phone IP]:" + PORT);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();

        // 1. Check if the client is asking for the image
        if (uri.equals("/frame.jpg")) {
            byte[] imageBytes = ProcessedFrameHolder.getLatestFrame();

            if (imageBytes != null) {
                // 2. Serve the JPEG image bytes

                // Create the response object
                Response response = newFixedLengthResponse(
                        Response.Status.OK,
                        "image/jpeg",
                        new ByteArrayInputStream(imageBytes),
                        imageBytes.length
                );

                // --- CRITICAL FIX: ADD ANTI-CACHING HEADERS ---
                // These headers are necessary for the browser to show a live stream
                // and prevent it from using old, broken data.
                response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.addHeader("Pragma", "no-cache");
                response.addHeader("Expires", "0");

                return response;
            } else {
                // 3. Image not ready yet
                return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Frame not ready.");
            }
        }

        // 4. Default response for anything else
        return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not Found");
    }}