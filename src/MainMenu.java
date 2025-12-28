import javax.swing.JFrame;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable {
    public KL keyListener = new KL();
    public Text startGame, exitGame, pong;
    public ML mouseListener = new ML();
    public boolean isRunning = true;

    //Default Constructor
    public MainMenu() {
        //Window Values
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Key Listener and Mouse Listener Values
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        //Title
        this.pong = new Text("Pong", new Font("Arial", Font.BOLD, 100), Constants.SCREEN_WIDTH / 2.0 - 155.0, 200, Constants.PADDLE_COLOR);

        //Start and Exit Buttons
        this.startGame = new Text("Start Game", new Font("Arial", Font.BOLD, 40),
                Constants.SCREEN_WIDTH / 2.0 - 140.0, Constants.SCREEN_HEIGHT / 2.0, Constants.PADDLE_COLOR);
        this.exitGame = new Text("Exit", new Font("Arial", Font.BOLD, 40),
                Constants.SCREEN_WIDTH / 2.0 - 80.0, Constants.SCREEN_HEIGHT / 2.0 + 60.0, Constants.PADDLE_COLOR);
    }

    public void update(double dt) {

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width &&
                mouseListener.getMouseY() > startGame.y - startGame.height / 2 && mouseListener.getMouseY() < startGame.y + startGame.height / 2) {
            startGame.color = Constants.SCORE_COLOR;

            if (mouseListener.isMousePressed()) {
                Main.changeState(1);
            }
        } else {
            startGame.color = Constants.PADDLE_COLOR;
        }

        if (mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width &&
                mouseListener.getMouseY() > exitGame.y - exitGame.height / 2 && mouseListener.getMouseY() < exitGame.y + exitGame.height / 2) {
            exitGame.color = Constants.SCORE_COLOR;
            if (mouseListener.isMousePressed()) {
                System.exit(0);
            }
        } else {
            exitGame.color = Constants.PADDLE_COLOR;
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        pong.draw(g2);
        startGame.draw(g2);
        exitGame.draw(g2);
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
        this.dispose();
        return;
    }

    public void stop() {
        isRunning = false;
    }
}

