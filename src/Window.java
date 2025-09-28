import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    Graphics2D g2;
    KL keyListener = new KL();

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g2 = (Graphics2D)this.getGraphics();
        this.addKeyListener(keyListener);
    }

    public void update(double dt) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            System.out.println("The user is pressing the up arrow");
        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            System.out.println("The user is pressing the down arrow");
        }  else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            System.out.println("The user is pressing the left arrow");
        }  else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            System.out.println("The user is pressing the right arrow");
        }
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try{
                Thread.sleep(30);
            } catch(InterruptedException e) {

            }

        }
    }
}
