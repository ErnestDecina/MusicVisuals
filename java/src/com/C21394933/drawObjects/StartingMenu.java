package com.C21394933.drawObjects;

import com.C21394933.network.DownloadAIGeneratedImage;
import com.C21394933.network.PostOpenAPI;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.FFT;
import ie.tudublin.DrawObjectAbstractClass;
import processing.core.PApplet;

public class StartingMenu extends DrawObjectAbstractClass {
    String imageLink;
    String prompt;


    StartingMenu(PApplet pApplet2, AudioBuffer audioBuffer, FFT fft, int windowWidth, int windowHeight) {
        this.pApplet = pApplet2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.audioBuffer = audioBuffer;
        this.fft = new FFT(2048, 44100);


    } // End Constructor


    
    public void render() {

    } // End void render()

    public Boolean generatingImage() {



        return null;
    } // End Boolean generatingImage

    public void buttonClick() {
        // Check 
        do
         {

        } while(generateAIImage()); // End while
    } // End void buttonClick

    public Boolean generateAIImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageLink = PostOpenAPI.run(prompt);
            }
       }).start();

       return null;
    } // End void generateAIImage()

    public Boolean downloadAIGeneratedImage() {
        DownloadAIGeneratedImage.downloadImage(imageLink);
        return null;
    }
}
