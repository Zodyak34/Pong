public class Main {
    public static int state = 0;
    public static Thread mainThread, gameThread, endThread;
    public static MainMenu mainMenu;
    public static Window gameWindow;

    public static EndScreen endScreen;

    public static void main(String[] args) {
       // Window window = new Window();
        mainMenu = new MainMenu();
        mainThread = new Thread(mainMenu);
        mainThread.start();
    }

    public static void changeState(int newState) {
        if (newState == 1 && state == 0) {
            mainMenu.setVisible(false);
            mainMenu.mouseListener.isPressed = false;
            mainMenu.stop();
            gameWindow = new Window();
            gameThread = new Thread(gameWindow);
            gameThread.start();
        } else if (newState == 0 && state == 1) {
            gameWindow.setVisible(false);
            mainMenu.mouseListener.isPressed = false;
            gameWindow.stop();
            mainMenu.setVisible(true);
            mainThread = new Thread(mainMenu);
            mainThread.start();
        } else if (newState == 2 && state == 1) {
            int playerScore = gameWindow.ball.playerScore;
            int aiScore = gameWindow.ball.aiScore;

            gameWindow.stop();
            gameWindow.setVisible(false);
            mainMenu.mouseListener.isPressed = false;
            endScreen = new EndScreen(playerScore, aiScore);
            endThread = new Thread(endScreen);
            endThread.start();
        } else if (newState == 1 && state == 2) {
            endScreen.stop();
            endScreen.setVisible(false);
            mainMenu.mouseListener.isPressed = false;
            gameWindow = new Window();
            gameThread = new Thread(gameWindow);
            gameThread.start();
        } else if (newState == 0 && state == 2) {
            endScreen.stop();
            endScreen.setVisible(false);
            mainMenu.mouseListener.isPressed = false;
            mainMenu.setVisible(true);
            mainMenu.isRunning = true;
            mainThread = new Thread(mainMenu);
            mainThread.start();
        }
        state = newState;
    }
}
