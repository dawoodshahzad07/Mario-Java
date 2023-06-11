
import javax.swing.*;
public class Mobs {

        public static final String imgpath = "D:\\MarioJava\\GameAssets\\GFX\\Enemy\\";

    }

    class Goomba extends Mobs {

        JLabel Goomba;

        String goomba = imgpath + "goomb.png";
        String goombaRight = imgpath + "rgoomba.gif";
        String goombaLeft = imgpath + "lgoomba.gif";
        String goombaPress = imgpath + "goombdie.png";

        Goomba() {
            Goomba = new JLabel();
            Goomba.setIcon(new ImageIcon(goomba));
            Goomba.setBounds(524, 480, 50, 50);
            Goomba.setOpaque(false);
            Goomba.setVisible(true);
        }
    }

