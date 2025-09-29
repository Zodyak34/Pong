import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController {
    public Rect rect;
    public KL keyListener;

    public PlayerController(Rect rect, KL kl) {
        this.rect = rect;
        this.keyListener = kl;
    }

    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            if ((this.rect.y + Constants.PADDLE_SPEED * dt) + rect.height < Constants.SCREEN_HEIGHT - Constants.insetBottom) {
                this.rect.y += Constants.PADDLE_SPEED * dt;
            }
        } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            if (rect.y - Constants.PADDLE_SPEED * dt > Constants.toolBarHeight) {
                this.rect.y -= Constants.PADDLE_SPEED * dt;
            }
        }
    }
}
