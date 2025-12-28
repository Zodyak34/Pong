import javax.swing.JFrame;
import java.awt.*;
public class EndScreen extends JFrame implements Runnable{
    public KL keyListener = new KL();
    public Text winner, scoreText, newGame, mainMenuButton, exit;
    public ML mouseListener = new ML();
    public boolean isRunning = true;
    public int playerScore, aiScore;

    public EndScreen(int playerScore, int aiScore) {
        this.playerScore = playerScore;
        this.aiScore = aiScore;

        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        String winnerText = playerScore >= Constants.WIN_CON ? "You Win!" : "AI Wins!";
        this.winner = new Text(winnerText, new Font("Arial", Font.BOLD, 80), Constants.SCREEN_WIDTH / 2.0 - 160.0, 180, Constants.PADDLE_COLOR);

        String scoreString = playerScore + " - " + aiScore;
        this.scoreText = new Text(scoreString, new Font("Arial", Font.BOLD, 60),
                Constants.SCREEN_WIDTH / 2.0 - 80.0, 280, Constants.PADDLE_COLOR);

        this.newGame = new Text("New Game", new Font("Arial", Font.BOLD, 40),
                Constants.SCREEN_WIDTH / 2.0 - 120.0, Constants.SCREEN_HEIGHT / 2.0 + 50, Constants.PADDLE_COLOR);
        this.mainMenuButton = new Text("Main Menu", new Font("Arial", Font.BOLD, 40),
                Constants.SCREEN_WIDTH / 2.0 - 120.0, Constants.SCREEN_HEIGHT / 2.0 + 110, Constants.PADDLE_COLOR);
        this.exit = new Text("Exit", new Font("Arial", Font.BOLD, 40),
                Constants.SCREEN_WIDTH / 2.0 - 60.0, Constants.SCREEN_HEIGHT / 2.0 + 170, Constants.PADDLE_COLOR);

        this.setVisible(true);
    }

    public void update(double dt) {
        if (mouseListener.getMouseX() > newGame.x && mouseListener.getMouseX() < newGame.x + newGame.width &&
                mouseListener.getMouseY() > newGame.y - newGame.height / 2 && mouseListener.getMouseY() < newGame.y + newGame.height / 2) {
            newGame.color = Constants.SCORE_COLOR;

            if (mouseListener.isMousePressed()) {
                Main.changeState(1);
            }
        } else {
            newGame.color = Constants.PADDLE_COLOR;
        }

        if (mouseListener.getMouseX() > mainMenuButton.x && mouseListener.getMouseX() < mainMenuButton.x + mainMenuButton.width &&
                mouseListener.getMouseY() > mainMenuButton.y - mainMenuButton.height / 2 && mouseListener.getMouseY() < mainMenuButton.y + mainMenuButton.height / 2) {
            mainMenuButton.color = Constants.SCORE_COLOR;

            if (mouseListener.isMousePressed()) {
                Main.changeState(0);  // Go to main menu
            }
        } else {
            mainMenuButton.color = Constants.PADDLE_COLOR;
        }

        if (mouseListener.getMouseX() > exit.x && mouseListener.getMouseX() < exit.x + exit.width &&
                mouseListener.getMouseY() > exit.y - exit.height / 2 && mouseListener.getMouseY() < exit.y + exit.height / 2) {
            exit.color = Constants.SCORE_COLOR;

            if (mouseListener.isMousePressed()) {
                System.exit(0);  // Exit the game
            }
        } else {
            exit.color = Constants.PADDLE_COLOR;
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbGraphics = dbImage.getGraphics();
        this.draw(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        winner.draw(g2);
        scoreText.draw(g2);
        newGame.draw(g2);
        mainMenuButton.draw(g2);
        exit.draw(g2);
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double dt = time - lastFrameTime;
            lastFrameTime = time;

            update(dt);
        }
        this.dispose();
        return;
    }

    public void stop() {
        isRunning = false;
    }
}
