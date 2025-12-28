import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public double x, y;
    public boolean isVisible;
    public double width, height;
    Color color;

    public Text(int text, Font font, double x, double y, Color color) {
        this.text = "" + text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.color = color;
        isVisible = false;
    }

    public Text(String text, Font font, double x, double y, Color color) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        isVisible = false;
        this.width = font.getSize() * text.length();
        this.height = font.getSize();
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.setFont(font);
        g2.drawString(text, (float) x, (float) y);
    }

}
