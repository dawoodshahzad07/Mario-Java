import javax.swing.*;

public class MainMenu{
    JPanel panel;
    JLayeredPane layer;
    JLabel background;
    JButton start,credit, exit;

    public static final int SCREEN_WIDTH = 900, SCREEN_HEIGHT = 600;
    public static final String btnpath = "D:\\MarioJava\\GameAssets\\GFX\\Buttons\\";
    String addressBackground = "D:\\MarioJava\\img\\Mainmenu\\bg.png";
    String StartButton = btnpath + "Start1-1.png";
    String StartButtonHover = btnpath + "Start1-2.png";
    String StartButtonPress = btnpath + "Start1-3.png";

    String CreditsButton = btnpath + "Credits1-1.png";
    String CreditsButtonHover = btnpath + "Credits1-2.png";
    String CreditsButtonPress = btnpath + "Credits1-3.png";

    String ExitButton = btnpath + "Exit1-1.png";
    String ExitButtonHover = btnpath + "Exit1-2.png";
    String ExitButtonPress = btnpath + "Exit1-2.png";

    MainMenu(){
        Background();
        scenes();
        panel();
    }
    public void Background() {
        background = new JLabel();
        background.setIcon(new ImageIcon(addressBackground));
        background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        background.setVisible(true);

        start = new JButton();
        start.setIcon(new ImageIcon(StartButton));
        start.setBounds(300, 140, 300, 100);
        start.setText("Start Game");
        start.setBorder(null);
        start.setContentAreaFilled(false);
        start.setOpaque(false);
        start.setVisible(true);
        start.setRolloverIcon(new ImageIcon(StartButtonHover));
        start.setSelectedIcon(new ImageIcon(StartButtonPress));

        credit = new JButton();
        credit.setIcon(new ImageIcon(CreditsButton));
        credit.setBounds(300, 270, 300, 100);
        credit.setText("Credits");
        credit.setBorder(null);
        credit.setContentAreaFilled(false);
        credit.setOpaque(false);
        credit.setVisible(true);
        credit.setRolloverIcon(new ImageIcon(CreditsButtonHover));
        credit.setSelectedIcon(new ImageIcon(CreditsButtonPress));

        exit = new JButton();
        exit.setIcon(new ImageIcon(ExitButton));
        exit.setBounds(300, 400, 300, 100);
        exit.setText("High Score");
        exit.setBorder(null);
        exit.setContentAreaFilled(false);
        exit.setOpaque(false);
        exit.setVisible(true);
        exit.setRolloverIcon(new ImageIcon(ExitButtonHover));
        exit.setSelectedIcon(new ImageIcon(ExitButtonPress));
    }
    public void scenes() {
        layer = new JLayeredPane();
        layer.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        layer.add(background, new Integer(1));
        layer.add(start, new Integer(2));
        layer.add(credit, new Integer(2));
        layer.add(exit, new Integer(2));
    }
    public void panel() {
        panel = new JPanel();
        panel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        panel.setLayout(null);
        panel.add(layer);
        panel.setVisible(true);
    }
}