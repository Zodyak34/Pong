import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public double x, y;
    public boolean isVisible;

    public Text(int text, Font font, double x, double y) {
        this.text = "" + text;
        this.font = font;
        this.x = x;
        this.y = y;
        isVisible = false;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Constants.SCORE_COLOR);
        g2.setFont(font);
        g2.drawString(text, (float) x, (float) y);
    }

}
