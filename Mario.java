import javax.swing.*;

public class Mario {
    JLabel mario;
    public static final String imgpath = "E:\\MarioJava\\GameAssets\\GFX\\Mario\\";
    String addressMarioStandingRight = imgpath + "mario.png";
    String addressMarioJumpRight = imgpath + "jump_r.png";
    String addressMarioJumpLeft = imgpath + "jump_l.png";
    String addressMarioStandingLeft = imgpath + "lmario.png";
    String addressMarioWalkingRight = imgpath + "wmario_r.gif";
    String addressMarioWalkingLeft = imgpath + "wmario_l.gif";
    String addressDMario = imgpath + "dmario.png";
    String addressMarioDuck = imgpath + "smario_r.png";

    Mario(){
        mario = new JLabel();
        mario.setIcon(new ImageIcon(addressMarioStandingRight));
        mario.setBounds(0, 470, 50, 60);
        mario.setOpaque(false);
        mario.setVisible(true);
    }
}