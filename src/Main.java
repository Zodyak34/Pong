public class Main {
    public static int state = 0;
    public static Thread mainThread;

    public static Thread gameThread;
    public static MainMenu mainMenu;
    public static Window gameWindow;

    public static void main(String[] args) {
       // Window window = new Window();
        mainMenu = new MainMenu();
        mainThread = new Thread(mainMenu);
        mainThread.start();
    }

    public static void changeState(int newState) {
        if (newState == 1 && state == 0) {
            mainMenu.setVisible(false);
            mainMenu.stop();
            gameWindow = new Window();
            gameThread = new Thread(gameWindow);
            gameThread.start();
        } else if (newState == 0 && state == 1) {
            gameWindow.setVisible(false);
            gameWindow.stop();
            mainMenu.setVisible(true);
            mainThread = new Thread(mainMenu);
            mainThread.start();
        }
        state = newState;
    }
}
