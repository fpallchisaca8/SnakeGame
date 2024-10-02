/* 
 * The Game: snake movement, food, light vs dark mode, points, etc.
 */
import java.awt.*;
import javax.swing.*;

public class SnakeGameGUI extends JPanel {

    private MainGUI mainFrame;
    private JButton homeButton;
    private GamePanel gamePanel;
    private JPanel stripPanel;

    public SnakeGameGUI(MainGUI mainFrame, String snakeColor, boolean blindMode) {
        this.mainFrame = mainFrame;
        gamePanel = new GamePanel(mainFrame);
        // Set the custom settings
        gamePanel.setSnakeColor(snakeColor);
        gamePanel.setBlindMode(blindMode);
    
        //ImageIcon homeIcon = new ImageIcon(getClass().getResource("/Downloads/house.png"));
        homeButton = new JButton("Home"); 
        homeButton.setBounds(280, 0, 50, 50);
        homeButton.addActionListener(e -> mainFrame.switchToHomePanel());

        stripPanel = new JPanel();
        stripPanel.setBounds(0, 0, 600, 50);
        stripPanel.setBackground(new Color(55, 55, 55));



        //BUTTONS + THEIR FUNCTIONS
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        stripPanel.add(homeButton);
        add(stripPanel, BorderLayout.NORTH);
    }

}