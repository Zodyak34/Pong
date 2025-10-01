import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect playerOne, ai, ballRect, divider;
    public PlayerController pc;
    public AIController aiController;
    public Ball ball;
    public Text playerScoreText, aiScoreText, hyphen;
    public int playerScore, aiScore;

    //Default Constructor
    public Window() {
        //Window Values
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Graphics Object Value
        g2 = (Graphics2D)this.getGraphics();

        //Key Listener Value
        this.addKeyListener(keyListener);

        //Top and Bottom Inset Values
        Constants.toolBarHeight = this.getInsets().top;
        Constants.insetBottom = this.getInsets().bottom;

        //Divider Value
        divider = new Rect(Constants.SCREEN_WIDTH / 2.0 - (Constants.DIV_WIDTH) / 2.0, 0, Constants.DIV_WIDTH, Constants.SCREEN_HEIGHT, Constants.PADDLE_COLOR);

        //Score Values
        playerScore = 0;
        aiScore = 0;
        playerScoreText = new Text(playerScore, new Font("Arial", Font.BOLD, 50),
                Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH / 2.0) - 78, Constants.SCREEN_HEIGHT / 2.0 + Constants.toolBarHeight);
        aiScoreText = new Text(aiScore, new Font("Arial", Font.BOLD, 50),
                Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH / 2.0) + 50,Constants.SCREEN_HEIGHT / 2.0 + Constants.toolBarHeight);

        //Paddle Rect Values
        playerOne = new Rect(Constants.HORIZ_PADDING, Constants.SCREEN_HEIGHT / 2.0 - Constants.toolBarHeight,
                                Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HORIZ_PADDING, Constants.SCREEN_HEIGHT / 2.0 - Constants.toolBarHeight,
                                Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);

        //Ball Values
        ballRect = new Rect(Constants.SCREEN_WIDTH / 2.0, Constants.SCREEN_HEIGHT / 2.0, Constants.BALL_CIRC, Constants.BALL_CIRC, Constants.PADDLE_COLOR);
        ball = new Ball(ballRect, playerOne, ai, playerScoreText, aiScoreText);

        //Controller Values
        pc = new PlayerController(playerOne, keyListener);
        aiController = new AIController(new PlayerController(ai), ballRect, ball);
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        pc.update(dt);
        aiController.update(dt);
        ball.update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        //Draw Rectangles
        divider.draw(g2);
        playerOne.draw(g2);
        ai.draw(g2);
        ballRect.draw(g2);

        //Draw Score
        if (playerScoreText.isVisible) {
            playerScoreText.draw(g2);
            aiScoreText.draw(g2);
        } else if (aiScoreText.isVisible) {
            aiScoreText.draw(g2);
            playerScoreText.draw(g2);
        }
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
    }
}
