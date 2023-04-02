package com.C21782059.drawObjects;

import ie.tudublin.DrawObjectAbstractClass;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class AudioHm extends DrawObjectAbstractClass {

    // Constructor
    public AudioHm(final PApplet pApplet2, final AudioBuffer audioBuffer, final FFT fft, final int windowWidth, final int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = fft;

    }

    int numBands = 512;
    float[] bandSize = new float[numBands];

    float angle = 0;
    float colorAngle = 0;

    public void render() {
        pApplet.background(0);
        pApplet.lights();

        // Sphere field in top-left corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4, windowHeight / 4, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in top-right corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4 * 3, windowHeight / 4, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in bottom-right corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4 * 3, windowHeight / 4 * 3, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in bottom-left corner
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 4, windowHeight / 4 * 3, -100);
        drawShapeField();
        pApplet.popMatrix();

        // Sphere field in center of screen
        pApplet.pushMatrix();
        pApplet.translate(windowWidth / 2, windowHeight / 2, -100);
        drawShapeField();
        pApplet.popMatrix();
    }

    private void drawShapeField() {
        pApplet.rotateX(angle / 30.0f);
        pApplet.rotateY(angle * 1.3f / 30.0f);
        pApplet.rotateZ(angle * 0.7f / 30.0f);
        pApplet.noStroke();
        final float r = 200;
        fft.forward(audioBuffer);
        for (int i = 0; i < numBands; i++) {
            bandSize[i] = fft.getBand(i) * 5;
        }
        angle += PApplet.map(bandSize[0], 0, 100, 0.01f, 0.1f);
        colorAngle += PApplet.map(bandSize[1], 0, 100, 0.001f, 0.01f);
        for (int i = 0; i < 360; i += 5) {
            final float x = r * PApplet.cos(PApplet.radians(i));
            final float y = r * PApplet.sin(PApplet.radians(i));
            final float z = 0;
            final float size = PApplet.map(bandSize[i / 5], 0, 255, 0, 50);
            final float yOffset = PApplet.map(PApplet.sin(angle + i), -1, 1, -50, 50);
            final int c = pApplet.color(PApplet.map(bandSize[i / 5], 0, 255, 0, 360), 255, 255);
            pApplet.fill(c);
            pApplet.pushMatrix();
            pApplet.translate(x, y + yOffset, z);
            if (i % 2 == 0) {
                pApplet.box(size);
            } else {
                pApplet.sphere(size);
            }
            pApplet.popMatrix();
        }
    }

}
