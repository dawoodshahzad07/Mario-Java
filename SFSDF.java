import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Thread.sleep;

class Game3 extends JFrame implements KeyListener, ComponentListener, ActionListener {
    MainMenu m1 = new MainMenu();
    Level1 l1 = new Level1();
    JButton fallArea;

    Rectangle marioHitbox = new Rectangle(0, 0, 50, 60);

    Rectangle fallHitbox = new Rectangle(200, 520, 90, 60);
    int highscore, score = 0, animationCount = 0, jumpCount = 0, levelpart = 0, switchScreen = 0;
    int checkForDiagonal = 0, checkForJump = 1;
    public static final int SCREEN_WIDTH = 900, SCREEN_HEIGHT = 630;

    Game3() {
        Level1.animationCloud a1 = new Level1.animationCloud();
        a1.start();
        fall();

        //Reading HighScore
        Scanner read = null;
        try {
            read = new Scanner(new File("E:\\MarioJava\\GameAssets\\Other\\HighScore.txt"));
            highscore = Integer.parseInt(read.nextLine());
            System.out.println("high: " +highscore);

        } catch (Exception e) {
            System.out.println(e);
        } finally{
            highscore = 0;
        }//

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        Switch();
        l1.m1.mario.addComponentListener(this);
        this.setVisible(true);
    }

    public void Switch() {
        if (switchScreen == 0) {
            //switchScreen=1;
            this.add(m1.panel);
            l1.panel.setVisible(false);

            //Action listener added to MainMenu Buttons
            m1.start.addActionListener(this);
            m1.credit.addActionListener(this);
            m1.exit.addActionListener(this);
        }
        if (switchScreen == 1) {
            //this.remove(m1.panel);
            m1.panel.setVisible(false);
            this.add(l1.panel);
            this.addKeyListener(this);
        }
    }

    public void fall() {
        fallArea = new JButton();
        fallArea.setBounds(180, 470, 92, 60);
        fallArea.setFocusable(false);
        fallArea.setOpaque(false);
        fallArea.setContentAreaFilled(false);
        fallArea.setVisible(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keyCheck = e.getKeyCode();

        switch (keyCheck) {
            //UP
            case KeyEvent.VK_UP:
            case 87:
                movementUP m2 = new movementUP();
                jumpCount++;
                m2.start();
                break;
            //DOWN
            case KeyEvent.VK_DOWN:
            case 83:
                l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioDuck));
                break;
            //RIGHT
            case KeyEvent.VK_RIGHT:
            case 68:
                right();
                break;
            //LEFT
            case KeyEvent.VK_LEFT:
            case 65:
                left();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case 87:
                break;
            case KeyEvent.VK_RIGHT:
            case 68:
                checkForDiagonal = 0;
                if (jumpCount == 0) {
                    l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioStandingRight));
                }
                if (jumpCount == 1 || jumpCount == 2) {
                    l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioJumpRight));
                }
                break;
            case KeyEvent.VK_LEFT:
            case 65:
                checkForDiagonal = 0;
                l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioStandingLeft));
                break;
            case KeyEvent.VK_DOWN:
            case 83:
                l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioStandingRight));
                break;
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

        marioHitbox.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY());
        //If mario goes full to the right,
        if (l1.m1.mario.getX() > 870) {
            if (l1.m1.mario.getX() > 870)
                screenChange(levelpart, 0);
            l1.background.setLocation(-900, 0); //change screen

            l1.m1.mario.setLocation(0, l1.m1.mario.getY()); //relocate mario to the start
            levelpart = 1; //update screen state
        }
        //New coin collect logic
        if (coinCheck(l1.m1.mario, l1.coin1)) {
            updateScore(l1.scoreDisplay);
            System.out.println("coin1 collected");
            l1.coin1.setVisible(false);
        }
        if (coinCheck(l1.m1.mario, l1.coin2)) {
            updateScore(l1.scoreDisplay);
            System.out.println("coin1 collected");
            l1.coin2.setVisible(false);
        }

        /*if(collideCheck(marioHitbox, fallHitbox)){
            if(levelpart == 1){
                fall f1 = new fall();
                f1.start();
            }

        }*/

        //UP
        //Hit first question block
        if (qblockCheck(marioHitbox, l1.qblock1)){
            updateScore(l1.scoreDisplay, "qblock");
            l1.qblock1.setVisible(false);
        }

        //DOWN
        if(fallCollideCheck(marioHitbox, l1.g1.Goomba)){
            System.out.println("killed");
            updateScore(l1.scoreDisplay, "enemy");
            l1.g1.Goomba.setIcon(new ImageIcon(l1.g1.goombaPress)); //Channnge to stomped image
            //l1.g1.Goomba.setVisible(false); //and then remove it from the screen
            jumpCount = 0;
            return;
        }

        //Left
        if (levelpart == 0 && l1.m1.mario.getX() <= 0)
            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY());

        //If mario goes full to the left
        if (l1.m1.mario.getX() < -30) {
            screenChange(levelpart, 1);


            if (levelpart == 1) {
                l1.background.setLocation(0, 0); //change screen
                l1.qblock1.setVisible(true);
                l1.qblock2.setVisible(true);
                l1.m1.mario.setLocation(870, l1.m1.mario.getY()); //relocate mario to the start
                levelpart = 0; //update screen state
            }
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }
    @Override
    public void componentHidden(ComponentEvent e) {
    }

    //MainMenu Button Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == m1.start) { //When start button is pressed
            System.out.println("a");
            l1.panel.setVisible(true);
            m1.start.setFocusable(false);
            this.requestFocus(true);
            switchScreen = 1;
            Switch();
        } else if (e.getSource() == m1.credit) { //When credits button is pressed
            System.out.println("h");
            l1.panel.setVisible(true);
            m1.start.setFocusable(false);
            this.requestFocus(true);
            switchScreen = 1;
            Switch();
        } else if (e.getSource() == m1.exit) { //When exit button is pressed
            try {
                sleep(500);
                System.exit(0);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void right() {
        System.out.println(checkForJump);
        checkForJump = 1;
        l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioWalkingRight));
        l1.m1.mario.setLocation(l1.m1.mario.getX() + 10, l1.m1.mario.getY());
        animationCount += 1;
        if (jumpCount == 1 || jumpCount == 2) {
            checkForDiagonal = 1;
            System.out.println("diagonal");
            diagonal d1 = new diagonal();
            d1.start();
            this.addKeyListener(null);
        }
        if (levelpart == 1) {
            System.out.println("check");
            if (l1.m1.mario.getX() == fallArea.getX() && l1.m1.mario.getY() == fallArea.getY()) {
                fall f1 = new fall();
                f1.start();

                if (score > highscore){ //If our score exceeded the HighScore

                    PrintWriter write = null;
                    try {
                        write = new PrintWriter("E:\\MarioJava\\GameAssets\\Other\\HighScore.txt");
                        write.println(score);
                        write.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    public void left() {
        checkForJump = 2;
        l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioWalkingLeft)); //set walking image
        //Dont let mario move left from the first starting area
        if (jumpCount == 1 || jumpCount == 2) {
            checkForDiagonal = 2;
            System.out.println("diagonal");
            diagonal d1 = new diagonal();
            d1.start();
            this.addKeyListener(null);
        }
        if (levelpart == 1) {
            System.out.println("check");
            if (l1.m1.mario.getX() == fallArea.getX() && l1.m1.mario.getY() == fallArea.getY()) {
                fall f1 = new fall();
                f1.start();
            }
        }
        if (levelpart == 0 && l1.m1.mario.getX() <= 0)
            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY());
        else
            l1.m1.mario.setLocation(l1.m1.mario.getX() - 10, l1.m1.mario.getY());
    }

    public class movementUP extends Thread {
        public void run() {
            //If player isn't already between a 2nd jump (can only do 2 jumps at most)
            //Right Jump
            if (checkForJump == 1) {
                if (jumpCount < 3) {
                    System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                    MP3Player sfx = new MP3Player(new File(l1.jump_s));
                    sfx.play();
                    try {
                        setPriority(6);
                        l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioJumpRight));
                        for (int i = 0; i < 8; i++) {
                            sleep(15);
                            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() - 5);
                            System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                        }
                        for (int i = 0; i < 16; i++) {
                            sleep(10);
                            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() - 5);
                            System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                        }
                        for (int i = 0; i < 8; i++) {
                            if (groundCheck(l1.m1.mario)) {
                                System.out.println("grounnd");
                                jumpCount = 0;
                                return;
                            }


                            sleep(15);
                            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() + 5);
                        }
                        for (int i = 0; i < 16; i++) {

                            if (groundCheck(l1.m1.mario)) {
                                System.out.println("grounnd");
                                jumpCount = 0;
                                return;
                            }



                            sleep(10);
                            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() + 5);
                        }
                        l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioStandingRight));
                    } catch (InterruptedException f) {
                        System.out.println(f);
                    }
                    System.out.println(jumpCount);
                    jumpCount = 0; //resetting jump limit
                }
            }
            //Left jump
            if (checkForJump == 2) {
                if (jumpCount < 3) {
                    System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                    MP3Player sfx = new MP3Player(new File(l1.jump_s));
                    sfx.play();
                    try {
                        setPriority(6);
                        l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioJumpLeft));
                        /*for (int i = 0; i < 8; i++) {
                            sleep(15);
                            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() - 5);
                            System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                        }*/
                        for (int i = 0; i < 16; i++) {
                            sleep(10);
                            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() - 5);
                            System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                        }

                        for (int i = 0; i < 16; i++) {
                            sleep(10);
                            l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() + 5);
                        }
                        l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioStandingLeft));
                    } catch (InterruptedException f) {
                        System.out.println(f);
                    }
                    jumpCount = 0; //resetting jump limit
                }
            }
        }
    }

    public class diagonal extends Thread {
        public void run() {
            try {
                if (checkForDiagonal == 1) {
                    l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioJumpRight));
                    for (int i = 0; i < 24; i++) {
                        System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                        //mario.setIcon(new ImageIcon(addressMarioWalkingRight));
                        l1.m1.mario.setLocation(l1.m1.mario.getX() + 5, l1.m1.mario.getY());

                        sleep(25);
                    }
                    l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioStandingRight));
                }
                if (checkForDiagonal == 2) {
                    l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioJumpLeft));
                    for (int i = 0; i < 24; i++) {
                        System.out.println(l1.m1.mario.getX() + "+" + l1.m1.mario.getY());
                        //mario.setIcon(new ImageIcon(addressMarioWalkingRight));
                        l1.m1.mario.setLocation(l1.m1.mario.getX() - 5, l1.m1.mario.getY());

                        sleep(25);
                    }
                    l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressMarioStandingLeft));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        }
    }

    public class fall extends Thread {
        public void run() {
            try {
                setPriority(1);

                for (int i = 0; i < 60; i++) {
                    sleep(4);
                    l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() + 1);
                }
                l1.m1.mario.setIcon(new ImageIcon(l1.m1.addressDMario));
                for (int i = 0; i < 60; i++) {
                    sleep(4);
                    l1.m1.mario.setLocation(l1.m1.mario.getX(), l1.m1.mario.getY() - 2);
                }

                MP3Player sfx = new MP3Player(new File(l1.dead_s));
                sfx.play();
            } catch (InterruptedException f) {
                System.out.println(f.toString());
            }
        }
    }

    boolean coinCheck(JLabel player, JLabel coin) {
        if (coin.isVisible()) {
            if (player.getX() >= coin.getX() && player.getX() <= coin.getX() + 50) {
                if (player.getY() >= coin.getY() && player.getY() <= coin.getY() + 50) {
                    MP3Player sfx = new MP3Player(new File(l1.coin_s));
                    sfx.play();
                    return true;
                }
            }
        }
        return false;
    }

    boolean collideCheck(Rectangle player, Rectangle entity){

        if(player.intersects(entity))
            return true;
        return false;
    }

    void screenChange(int screen, int reverse){

//question blocks disappear when screen changes
        if(levelpart == 0) {
            if (reverse == 0) {
                l1.background.setLocation(-900, 0); //change screen

                l1.m1.mario.setLocation(0, l1.m1.mario.getY()); //relocate mario to the start
                levelpart = 1; //update screen state

                l1.qblock1.setVisible(false);
                l1.qblock2.setVisible(false);

                l1.g1.Goomba.setVisible(false);

            }
        }
        else if (reverse == 1) {
            l1.background.setLocation(0, 0); //change screen

            l1.m1.mario.setLocation(870, l1.m1.mario.getY()); //relocate mario to the start
            levelpart = 0; //update screen state

            //l1.qblock1.setVisible(false);
            l1.qblock2.setVisible(true);
        }


    }

    boolean qblockCheck(Rectangle player, JLabel qblock){

        if(qblock.isVisible()) {
            if (player.contains(qblock.getX() + 18, qblock.getY() + 35)) {
                MP3Player sfx = new MP3Player(new File(l1.supercoin_s));
                sfx.play();
                return true;
            }
        }
        return false;

    }

    boolean fallCollideCheck(Rectangle player, JLabel entity){

        if(entity.isVisible()){
            if(player.contains(entity.getX() + 25, entity.getY())){
                MP3Player sfx = new MP3Player(new File(l1.stomp_s));
                sfx.play();

                return true;
            }
        }
        return false;
    }

    boolean groundCheck(JLabel player){

        if(player.isVisible()){
            if((player.getY() + 60) >= 530)
                return true;
        }
        return false;
    }

    void updateScore(JLabel scoreDisp) {
        score += 100;
        scoreDisp.setText("Score: " + score);
    }

    void updateScore(JLabel scoreDisp, String entity){

        switch(entity){

            case "enemy":
                score+=200;
                break;
            case "coin":
                score+=100;
                break;
            case "supercoin":
                score+=400;
                break;
            case "level":
                score+=500;
                break;
            case "reset":
                score = 0;
            case "qblock":
                score+=100;
        }

        scoreDisp.setText("Score: " + score);
    }

}