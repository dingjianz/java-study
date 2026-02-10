import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        setTitle("贪吃蛇小游戏");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGame::new);
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int UNIT_SIZE = 20;
    private final int GAME_UNITS = 20;
    private final int SCREEN_SIZE = UNIT_SIZE * GAME_UNITS;
    static  final int DELAY = 100;
    private final ArrayList<Point> snake = new ArrayList<>();
    private Point food;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    private void startGame() {
        snake.clear();
        snake.add(new Point(GAME_UNITS / 2, GAME_UNITS / 2));
        direction = 'R';
        score = 0;
        spawnFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void spawnFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(GAME_UNITS);
            y = rand.nextInt(GAME_UNITS);
            food = new Point(x, y);
        } while (snake.contains(food));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            // Draw food
            g.setColor(Color.RED);
            g.fillOval(food.x * UNIT_SIZE, food.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            // Draw snake
            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                Point p = snake.get(i);
                g.fillRect(p.x * UNIT_SIZE, p.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            }
            // Draw score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("分数: " + score, 10, 20);
        } else {
            gameOver(g);
        }
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);
        switch (direction) {
            case 'U': newHead.y -= 1; break;
            case 'D': newHead.y += 1; break;
            case 'L': newHead.x -= 1; break;
            case 'R': newHead.x += 1; break;
        }
        snake.add(0, newHead);
        if (newHead.equals(food)) {
            score++;
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);
        // Check wall
        if (head.x < 0 || head.x >= GAME_UNITS || head.y < 0 || head.y >= GAME_UNITS) {
            running = false;
        }
        // Check self
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
                break;
            }
        }
        if (!running) timer.stop();
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("游戏结束", SCREEN_SIZE / 2 - 100, SCREEN_SIZE / 2 - 20);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("分数: " + score, SCREEN_SIZE / 2 - 40, SCREEN_SIZE / 2 + 20);
        g.drawString("按空格重新开始", SCREEN_SIZE / 2 - 70, SCREEN_SIZE / 2 + 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
        }
        repaint();
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
                case KeyEvent.VK_SPACE:
                    if (!running) startGame();
                    break;
            }
        }
    }
}

