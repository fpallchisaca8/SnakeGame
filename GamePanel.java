import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    private Game game;
    private Timer timer;
    InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = getActionMap();

    public GamePanel(MainGUI mainFrame) {
        this.game = new Game();
        setupGamePanel();
        startGameTimer();
        setupKeyBindings();
        
    }

    public void setSnakeColor(String color) {
        game.setSnakeColor(color);
    }

    public void setBlindMode(boolean blindMode) {
        game.setBlindMode(blindMode);
    }

    private void setupGamePanel() {
        setBounds(0, 100, 500, 500);
        setBackground(Color.BLACK);
        setFocusable(true);
        setVisible(true);
    }

    private void startGameTimer() {
        timer = new Timer(100, this);
        timer.start();
    }

//DRAWING SNAKE + FOOD

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        if (game.isGameRunning()) {
            if (game.isBlindMode()) {
                drawBlindMode(g);
            } else {
                drawSnake(g);
                drawFood(g);
            }
        } else {
            displayGameOver(g);
        }
    }

//GRID!!!
    private void drawGrid(Graphics g) { 
        g.setColor(new Color(43,43,43));
        for (int i = 0; i < getWidth(); i += 20) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int i = 0; i < getHeight(); i += 20) {
            g.drawLine(0, i, getWidth(), i);
        }
    }


    private void drawSnake(Graphics g) {
        g.setColor(game.getSnakeColor());
        for (Point p : game.getSnake()) {
            g.fillRect(p.x * 20, p.y * 20, 20, 20);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(game.getFood().x*20, game.getFood().y*20, 20, 20); //Change food size
    }

///BLIND MODE
    private void drawBlindMode(Graphics g) {
        setBackground(Color.BLACK);
        Point head = game.getSnake().get(0);
        Point tail = game.getSnake().get(game.getSnake().size() - 1);

        g.setColor(Color.GREEN);
        g.fillRect(head.x * 20, head.y * 20, 20, 20);

        g.setColor(Color.RED);
        g.fillRect(game.getFood().x * 20, game.getFood().y * 20, 20, 20);

        g.setColor(Color.YELLOW);
        g.fillRect(tail.x * 20, tail.y * 20, 20, 20);
    }


    private void displayGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Game Over", 265, 250);
    }

    public void actionPerformed(ActionEvent e) {
        if (game.isGameRunning()) {
            game.move();
            game.checkCollision();
            game.checkFood();
        }
        repaint();
    }
    

    private void setupKeyBindings() {
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");

        ActionMap(actionMap);
    }

    private void ActionMap(ActionMap actionMap) {
        actionMap.put("moveUp", new MoveAction("UP", "DOWN"));
        actionMap.put("moveDown", new MoveAction("DOWN", "UP"));
        actionMap.put("moveLeft", new MoveAction("LEFT", "RIGHT"));
        actionMap.put("moveRight", new MoveAction("RIGHT", "LEFT"));
    }

    private class MoveAction extends AbstractAction {
        private String newDirection;
        private String oppositeDirection;

        public MoveAction(String newDirection, String oppositeDirection) {
            this.newDirection = newDirection;
            this.oppositeDirection = oppositeDirection;
        }

        public void actionPerformed(ActionEvent e) {
            if (!game.getDirection().equals(oppositeDirection)) {
                game.setDirection(newDirection);
            }
        }
    }
}
