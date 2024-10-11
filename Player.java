import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
    private int x, y, width, height;
    private int speed;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 25;  // Speed of movement
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) x = 0;
    }

    public void moveRight(int panelWidth) {
        x += speed;
        if (x + width > panelWidth) x = panelWidth - width;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
