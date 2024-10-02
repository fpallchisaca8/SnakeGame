import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Point> snake;
    private Point food;
    private String direction;
    private boolean gameRunning;
    private String snakeColor;
    private boolean blindMode;


    public Game() {
        this.snake = new ArrayList<>();
        this.food = new Point();
        this.direction = "RIGHT";
        this.gameRunning = true;
        this.snakeColor = "Green"; // Default color
        this.blindMode = false;    // Default blind mode
        initGame();
    }

    public void initGame() {
        snake.clear();
        snake.add(new Point(10, 10));
        spawnFood();
    }

    private void spawnFood() {
        Random rand = new Random();
        food.setLocation(rand.nextInt(500/20), rand.nextInt(500/20)); //Boarder width/height = 500/20
    }

    public ArrayList<Point> getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }

    public void checkFood() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            snake.add(new Point(food));
            spawnFood();
        }
    }

    public void setSnakeColor(String color) {
        this.snakeColor = color;
    }

    public Color getSnakeColor() {
        switch (snakeColor) {
            case "Red": return Color.RED;
            case "Blue": return Color.BLUE;
            case "Yellow": return Color.YELLOW;
            case "Purple": return Color.MAGENTA;
            default: return Color.GREEN;
        }
    }

    public void setBlindMode(boolean blindMode) {
        this.blindMode = blindMode;
    }

    public boolean isBlindMode() {
        return blindMode;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public String getDirection(){
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void move() {
        Point head = new Point(snake.get(0));
        switch (direction) {
            case "UP": head.y -= 1; break;
            case "DOWN": head.y += 1; break;
            case "LEFT": head.x -= 1; break;
            case "RIGHT": head.x += 1; break;
        }
        snake.add(0, head);
        snake.remove(snake.size() - 1);
    }

    public void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= 30 || head.y < 0 || head.y >= 30) {
            gameRunning = false;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameRunning = false;
            }
        }
    }
}
