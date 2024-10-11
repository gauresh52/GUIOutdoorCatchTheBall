import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private Player player;
    private List<Ball> balls;
    private int score;
    private int missedBalls;

    public GamePanel() {
        this.player = new Player(300, 500, 50, 20);
        this.balls = new ArrayList<>();
        this.score = 0;
        this.missedBalls = 0;

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.moveRight(getWidth());
                }
            }
        });

        Thread gameThread = new Thread(() -> {
            while (true) {
                updateGame();
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    private void updateGame() {
        // Add new balls at intervals
        if (Math.random() < 0.02) {
            balls.add(new Ball(getWidth()));
        }

        // Move the balls and check for collisions
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();
            ball.fall();

            if (ball.isOffScreen(getHeight())) {
                missedBalls++;
                iterator.remove();
            } else if (ball.getBounds().intersects(player.getBounds())) {
                score++;
                iterator.remove();
            }
        }

        // End game if too many balls are missed
        if (missedBalls >= 10) {
            System.out.println("Game Over! Score: " + score);
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);

        for (Ball ball : balls) {
            ball.draw(g);
        }

        g.drawString("Score: " + score, 10, 10);
        g.drawString("Missed: " + missedBalls, 10, 25);
    }
}
