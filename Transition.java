import javax.swing.*;
import java.awt.*;

public class Transition {
    JLayeredPane layer;
    JFrame frame;
    JPanel panel;
    JButton retry, mainMenu;

    Transition(){
        retry();
        mainMenu();
        layer();
        panel();
        frame();


    }
    public void retry(){
        retry = new JButton();
        retry.setBounds(300, 70, 300, 100);
        retry.setText("RETRY!");
        //retry.setBorder(null);
        //retry.setContentAreaFilled(false);
        //retry.setOpaque(false);
        retry.setVisible(true);
    }
    public void mainMenu(){
        mainMenu = new JButton();
        mainMenu.setBounds(300, 220, 300, 100);
        mainMenu.setText("Main Menu!");
//        mainMenu.setBorder(null);
//        mainMenu.setContentAreaFilled(false);
//        mainMenu.setOpaque(false);
        mainMenu.setVisible(true);
    }
    public void layer(){
        layer = new JLayeredPane();
        layer.setBounds(0, 0, 900, 600);
        layer.add(retry, new Integer(1));
        layer.add(mainMenu, new Integer(2));
    }
    public void panel(){
        panel=new JPanel();
        panel.setBounds(0,0,900,600);
        panel.setLayout(null);
        panel.setBackground(Color.black);
        panel.add(layer);
        panel.setVisible(true);
    }
    public void frame(){
        frame=new JFrame();
        frame.setSize(900,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.cyan);
        frame.setLayout(null);
        frame.add(panel);
        frame.setVisible(true);
    }

}