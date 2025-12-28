public class AIController {
    public PlayerController playerController;
    public Rect ballRect;
    public Ball ball;

    public AIController(PlayerController playerController, Rect rectBall, Ball ball) {
        this.playerController = playerController;
        this.ballRect = rectBall;
        this.ball = ball;
    }

    public void update(double dt) {
        playerController.update(dt);
        if (ball.vx > 0) {
            if (ballRect.y < playerController.rect.y) {
                playerController.moveUp(dt);
            } else if (ballRect.y + ballRect.height > playerController.rect.y + playerController.rect.height) {
                playerController.moveDown(dt);
            }
        }
    }
}
