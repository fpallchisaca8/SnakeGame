import java.awt.*;
import javax.swing.*;

public class customizeGame extends JPanel {
    private MainGUI mainFrame;
    private ButtonGroup colorGroup;
    private JRadioButton redButton, greenButton, blueButton, yellowButton, purpleButton;
    private JRadioButton blindModeOnButton, blindModeOffButton;
    private JButton saveButton;

    public customizeGame(MainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setupCustomizationPanel();
    }

    private void setupCustomizationPanel() {
        setLayout(new GridLayout(8, 1));

        JLabel colorLabel = new JLabel("Choose Snake Color:");
        add(colorLabel);

        colorGroup = new ButtonGroup();

        redButton = new JRadioButton("Red");
        greenButton = new JRadioButton("Green");
        blueButton = new JRadioButton("Blue");
        yellowButton = new JRadioButton("Yellow");
        purpleButton = new JRadioButton("Purple");

        colorGroup.add(redButton);
        colorGroup.add(greenButton);
        colorGroup.add(blueButton);
        colorGroup.add(yellowButton);
        colorGroup.add(purpleButton);

        add(redButton);
        add(greenButton);
        add(blueButton);
        add(yellowButton);
        add(purpleButton);

        JLabel blindModeLabel = new JLabel("Blind Mode:");
        add(blindModeLabel);

        ButtonGroup blindModeGroup = new ButtonGroup();
        blindModeOnButton = new JRadioButton("On");
        blindModeOffButton = new JRadioButton("Off");

        blindModeGroup.add(blindModeOnButton);
        blindModeGroup.add(blindModeOffButton);

        add(blindModeOnButton);
        add(blindModeOffButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> applySettings());
        add(saveButton);
    }

    private void applySettings() {
        // Apply settings to the game
        String selectedColor = "Green"; // default color
        if (redButton.isSelected()) selectedColor = "Red";
        if (blueButton.isSelected()) selectedColor = "Blue";
        if (yellowButton.isSelected()) selectedColor = "Yellow";
        if (purpleButton.isSelected()) selectedColor = "Purple";

        boolean blindMode = blindModeOnButton.isSelected();

        mainFrame.setSnakeColor(selectedColor);
        mainFrame.setBlindMode(blindMode);

        mainFrame.switchToHomePanel();
    }
}
