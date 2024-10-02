/* 
 * The App: [MAIN SCREEN] Switched bwt home, game, and customize window
 */
import java.awt.event.*;
import javax.swing.*;

public class MainGUI extends JFrame implements ActionListener {

    private JButton startButton;
    private JButton customizeButton;
    private JPanel homePanel;
    private String snakeColor = "Green";
    private boolean blindMode = false;

    public MainGUI() {
        setupGameFrame();
        initializeComponents();
        setVisible(true);
    }

    private void setupGameFrame() {
        setTitle("Snake Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initializeComponents() {
        homePanel = new JPanel();
        homePanel.setLayout(null);

        startButton = new JButton("Start");
        startButton.setBounds(250, 250, 100, 50);
        startButton.addActionListener(this);

        //Customize screen
        customizeButton = new JButton("Customize");
        customizeButton.setBounds(250, 300, 100, 50);
        customizeButton.addActionListener(e -> switchToCustomizePanel());

        homePanel.add(startButton);
        homePanel.add(customizeButton);
        add(homePanel);
    }

    //SWITCH TO GAME SCREEN
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            switchToGamePanel();
        }
    }
     
    public void switchToGamePanel() {
        // Remove the home panel
        remove(homePanel);
        // Create and add the game panel
        SnakeGameGUI gamePanel = new SnakeGameGUI(this, snakeColor, blindMode);
        add(gamePanel);
        // Refresh the frame to display the new panel
        revalidate();
        repaint();
    }

    private void switchToCustomizePanel() {
        remove(homePanel);
        customizeGame customizePanel = new customizeGame(this);
        add(customizePanel);
        revalidate();
        repaint();
    }

    public void switchToHomePanel() {
        // Remove the game panel
        getContentPane().removeAll();
        // Add the home panel
        add(homePanel);
        // Refresh the frame to display the home panel
        revalidate();
        repaint();
    }

    public void setSnakeColor(String color) {
        this.snakeColor = color;
    }

    public void setBlindMode(boolean blindMode) {
        this.blindMode = blindMode;
    }


    public static void main(String[] args) {
        new MainGUI();
    }
}
