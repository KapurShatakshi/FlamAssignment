"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
document.addEventListener("DOMContentLoaded", () => {
    // Find the stats element
    const statsElement = document.getElementById('frame-stats');
    // Find the iframe element by its ID
    const iframeElement = document.getElementById('live-stream-iframe');
    let frameCount = 0;
    let startTime = Date.now();
    let fps = 0;
    const updateFrame = () => __awaiter(void 0, void 0, void 0, function* () {
        // 1. Update the iframe source to force a re-fetch of the latest frame
        //    We add a timestamp to the URL to prevent the browser from caching.
        if (iframeElement) {
            iframeElement.src = `http://localhost:8080/frame.jpg?t=${Date.now()}`;
        }
        // 2. Calculate FPS (same logic)
        frameCount++;
        if (frameCount % 10 === 0) {
            const currentTime = Date.now();
            const elapsedTime = (currentTime - startTime) / 1000;
            fps = Math.round(frameCount / elapsedTime);
        }
        // 3. Update the stats text
        if (statsElement) {
            statsElement.innerText = `Frame: ${frameCount} | Resolution: 640x480 | FPS: ${fps} (Live)`;
        }
    });
    // Start the continuous refresh loop
    setInterval(updateFrame, 100);
});
