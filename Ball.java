import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    private int x, y, radius, speed;
    private Color color;

    public Ball(int panelWidth) {
        Random random = new Random();
        this.x = random.nextInt(panelWidth);  // Random horizontal position
        this.y = 0;  // Start from the top of the screen
        this.radius = 20;
        this.speed = 5 + random.nextInt(10);  // Random speed
        this.color = Color.RED;
    }

    public void fall() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius, radius);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, radius, radius);
    }

    public boolean isOffScreen(int panelHeight) {
        return y > panelHeight;
    }
}

