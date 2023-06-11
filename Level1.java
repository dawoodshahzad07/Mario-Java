import javax.swing.*;
import java.awt.*;

public class Level1{
    Mario m1=new Mario();
    Goomba g1 =new Goomba();
    JPanel panel;

    JLayeredPane layer;
    JLabel coin1, coin2, background, scoreDisplay, qblock1, qblock2 ;
    static JLabel cloud;
    public static final int SCREEN_WIDTH = 900, SCREEN_HEIGHT = 630;

    String imgpath = "E:\\MarioJava\\GameAssets\\GFX\\";
    String Level1Img = imgpath + "Levels\\Level_1.png";
    String Level2Img = imgpath + "Levels\\Level_2.png";
    String CoinImg = imgpath + "Interactive\\coin.gif";
    String CloudImg = imgpath + "Background\\clouds.png";
    int score = 0;
    //SFX
    public static final String sfxpath = "E:\\MarioJava\\GameAssets\\SFX\\";
    public static final String jump_s = sfxpath + "jump.mp3";
    public static final String coin_s = sfxpath + "coin.mp3";
    public static final String supercoin_s = sfxpath + "supercoin.mp3";
    public static final String stomp_s = sfxpath + "grow.mp3";
    public static final String dead_s = sfxpath + "pipe.mp3";
    Level1(){
        Background();
        coin();
        qblocks();
        score();
        cloudAnimation();
        scenes();
        panel();
    }
    public void Background() {
        background = new JLabel();
        background.setIcon(new ImageIcon(Level1Img));
        background.setBounds(0, 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT - 30);
        background.setVisible(true);
    }
    public void coin() {
        coin1 = new JLabel(new ImageIcon(CoinImg));
        coin1.setBounds(500, 470, 50, 60);
        coin1.setOpaque(false);
        coin1.setVisible(true);

        coin2 = new JLabel(new ImageIcon(CoinImg));
        coin2.setBounds(700, 470, 50, 60);
        coin2.setOpaque(false);
        coin2.setVisible(true);
    }

    public void qblocks() {
        qblock1 = new JLabel(new ImageIcon("E:\\MarioJava\\GameAssets\\GFX\\Blocks\\qblock11.png"));
        qblock1.setBounds(55, 385, 35, 35);
        qblock1.setOpaque(false);
        qblock1.setVisible(true);

        qblock2 = new JLabel(new ImageIcon("E:\\MarioJava\\GameAssets\\GFX\\Blocks\\qblock22.png"));
        qblock2.setBounds(55, 385, 35, 35);
        qblock2.setOpaque(false);
        qblock2.setVisible(true);
    }

    public void score() {
        scoreDisplay = new JLabel("Score: " + score);
        scoreDisplay.setBounds(710, 0, 180, 100);
        scoreDisplay.setFont(new Font("Mad Marker", 0, 24));
        scoreDisplay.setForeground(new Color(204, 204, 0));
        scoreDisplay.setOpaque(false);
        scoreDisplay.setVisible(true);
    }
    public void cloudAnimation() {
        cloud = new JLabel();
        cloud.setBounds(-70, -70, SCREEN_WIDTH, SCREEN_HEIGHT);
        cloud.setIcon(new ImageIcon(CloudImg));
        cloud.setOpaque(false);
        cloud.setVisible(true);
    }
    public void scenes() {
        layer = new JLayeredPane();
        layer.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT - 30);
        layer.add(background, new Integer(1));
        layer.add(cloud, new Integer(2));
        layer.add(qblock1, new Integer(2));
        layer.add(qblock2, new Integer(2));
        layer.add(coin1, new Integer(3));
        layer.add(coin2, new Integer(4));
        layer.add(g1.Goomba, new Integer(4));
        layer.add(scoreDisplay, new Integer(5));
        layer.add(m1.mario, new Integer(6));
    }
    public void panel() {
        panel = new JPanel();
        panel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT - 30);
        panel.setLayout(null);
        panel.add(layer);
        panel.setVisible(true);
    }
    public static class animationCloud extends Thread {
        public void run() {
            try {
                setPriority(1);

                for (int j = 0; j < 100000; j++) {
                    for (int i = 0; i < 60; i++) {
                        sleep(100);
                        cloud.setLocation(cloud.getX() + 1, cloud.getY());
                        //sleep(300);
                    }
                    for (int i = 0; i < 60; i++) {
                        sleep(100);
                        cloud.setLocation(cloud.getX() - 1, cloud.getY());
                        //sleep(300);
                    }
                }
            } catch (InterruptedException f) {
                System.out.println(f);
            }
        }
    }
}