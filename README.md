# 📄 Software Engineering Intern (R&D) Assessment: Real-Time Edge Detection Viewer

## I. Project Summary

This project implements a high‑performance, real‑time computer vision pipeline on Android by integrating **JNI (NDK)**, **OpenCV (C++)**, and **OpenGL ES 2.0+**. The app captures camera frames, processes them natively using OpenCV, and renders them as textures using OpenGL for smooth real‑time display.

**Final Output:** A real‑time **Canny Edge Detection Viewer**.
**Bonus Output:** A TypeScript‑based **Web Debug Viewer**.

---

## II. Evaluation Checklist (Features Implemented)

| Area                        | Requirement                                          | Status | Weight |
| --------------------------- | ---------------------------------------------------- | ------ | ------ |
| Native‑C++ Integration      | JNI Integration (Frame Address Passing)              | ✅ Done | 25%    |
| OpenCV Usage                | Apply Canny Edge Detection or Grayscale Filter (C++) | ✅ Done | 20%    |
| OpenGL Rendering            | Render Processed Image as a Texture (OpenGL ES 2.0+) | ✅ Done | 20%    |
| TypeScript Web Viewer       | Minimal Web Page & DOM Updates                       | ✅ Done | 20%    |
| Project Structure & History | Modular Structure & Reflective Commit History        | ✅ Done | 15%    |

### ⭐ Bonus Features

* Toggle Button → Switch between **Raw** and **Canny** output
* FPS Counter → Logs frame processing time
* HTTP Endpoint → Serves latest frame via NanoHTTPD for web viewer

---

## III. Architecture Explanation

### A. Data Pipeline (JNI → C++ → OpenGL)

#### **1. Frame Acquisition (Java → JNI)**

* Android **CameraX** ImageAnalysis captures frames.
* `analyze()` extracts the frame, converts it to `cv::Mat`.
* JNI call `processFrame(long matAddress)` passes the memory address to C++.
* **Weight: 25%**

#### **2. Native C++ Processing (OpenCV)**

* `processFrame(cv::Mat& frame)` receives frame by reference.
* Applies **Canny Edge Detection** with OpenCV.
* Controlled by a JNI boolean flag `g_canny_enabled`.
* If disabled → raw frame forwarded.
* **Weight: 20%**

#### **3. Rendering (OpenGL ES 2.0)**

* Native output is uploaded to a GL texture.
* Rendered using `GLSurfaceView` + `MyGLRenderer`.
* Uses `glTexImage2D` for high‑performance texture updates.
* **Weight: 20%**

---

## B. TypeScript Web Viewer (Bonus)

### **Logic**

* A small **NanoHTTPD server** in Android hosts a JPEG endpoint.
* A TypeScript client fetches this frame repeatedly to mimic live streaming.
* Demonstrates comfort with TypeScript, bundling, DOM updates.

---

## IV. Screenshots

### 📸 Preview Images

Below are the primary outputs of the application:

| Canny Output               | Raw Output             | Web Viewer                        |
| -------------------------- | ---------------------- | --------------------------------- |
| ![Canny](images/canny.png) | ![Raw](images/raw.png) | ![Web Viewer](images/webView.png) |

*(Insert images here if needed)*

| Component           | Description                               |
| ------------------- | ----------------------------------------- |
| Android App (Canny) | Real‑time Canny rendering via OpenGL      |
| Android App (Raw)   | Raw camera feed showcasing toggle feature |
| Web Viewer          | Live frame fetching & debug info          |

---

## V. Setup Instructions

### **1. Android Setup**

* Install **Android API 36**, **NDK**, **CMake**.
* Ensure OpenCV Android SDK is included.

### **2. Web Viewer Setup**

```bash
cd web/
npm install
npx tsc
```

### **3. Crucial Network Bridge**

```bash
adb reverse tcp:8000 tcp:8000
```

This forwards the Android NanoHTTPD server to your local machine.

---

## VI. Submission Criteria (Commit History)

The repository follows a **step‑by‑step modular commit pattern**:

1. Environment setup
2. JNI + Native integration
3. OpenCV processing
4. OpenGL rendering
5. Toggle + FPS
6. Web viewer integration

This satisfies the **15% Structural/History** requirement.

---

## ✅ Final Notes

This project demonstrates:

* Practical JNI + OpenCV + OpenGL pipeline design
* Real‑time performance optimization
* Cross‑environment debugging with TypeScript
* Clean architecture & modular commit history
