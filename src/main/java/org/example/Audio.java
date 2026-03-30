package org.example;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Audio {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new URL("https://devast.io/audio/ambient4.mp3"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        while (!clip.isRunning()) {
            Thread.sleep(10); // Небольшая задержка
        }
        while (clip.isRunning()) {
            Thread.sleep(1000); // Ждем 1 секунду, чтобы не блокировать основной поток
        }
        // Закрытие ресурса после окончания воспроизведения
        clip.close();
        audioInputStream.close();
    }
}