
public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;
    public Text playerScoreText, aiScoreText;
    public boolean delay;
    public double delayTime;
    public int scoringPlayer; // 0 = none, 1 = player, 2 = AI

    //velocity x and y
    public double vy = 10.0;
    public double vx = -300.0;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle, Text playerScoreText, Text aiScoreText) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.playerScoreText = playerScoreText;
        this.aiScoreText = aiScoreText;
        this.delay = false;
        this.scoringPlayer = 0;
    }

    public double calcNewVeloAngle(Rect paddle) {
        double relInterceptY = (paddle.y + (paddle.height / 2.0)) - (this.rect.y + (this.rect.height / 2.0));
        double normalInterceptY = relInterceptY / (paddle.height / 2.0);
        double theta = normalInterceptY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt) {
        if (vx < 0) {
            if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width &&
                    this.rect.x + this.rect.width >= this.leftPaddle.x &&
                    this.rect.y >= this.leftPaddle.y &&
                    this.rect.y <= this.leftPaddle.y + this.leftPaddle.height) {
                double theta = calcNewVeloAngle(this.leftPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;

            } else if (this.rect.x + this.rect.width < this.leftPaddle.x) {
                int aiScore = Integer.parseInt(aiScoreText.text);
                aiScore++;
                aiScoreText.text = "" + aiScore;

                delay = true;
                delayTime = System.currentTimeMillis();
                scoringPlayer = 2;

                this.vx = 0;
                this.vy = 0;

                if (aiScore >= Constants.WIN_CON) {
                    //TODO: return to main menu and stop game
                    System.out.println("Computer Wins!");
                }
            }
        } else if (vx > 0) {
            if (this.rect.x + this.rect.width >= this.rightPaddle.x &&
                    this.rect.x <= this.rightPaddle.x + this.rightPaddle.width &&
                    this.rect.y >= this.rightPaddle.y &&
                    this.rect.y <= this.rightPaddle.y + this.rightPaddle.height) {
                double theta = calcNewVeloAngle(this.rightPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;
            } else if (this.rect.x + this.rect.width > this.rightPaddle.x + this.rightPaddle.width) {
                int playerScore = Integer.parseInt(playerScoreText.text);
                playerScore++;
                playerScoreText.text = "" + playerScore;

                delay = true;
                delayTime = System.currentTimeMillis();
                scoringPlayer = 1;

                this.vx = 0;
                this.vy = 0;

                if (playerScore >= Constants.WIN_CON) {
                    //TODO: return to main menu and stop game
                    System.out.println("You win!");
                }
            }
        }

        if (delay && System.currentTimeMillis() - delayTime >= Constants.DELAY_TIME) {
            System.out.println("Delay Condition Met");
            this.rect.x = Constants.SCREEN_WIDTH / 2.0;
            this.rect.y = Constants.SCREEN_HEIGHT / 2.0;

            this.leftPaddle.y = Constants.SCREEN_HEIGHT / 2.0 - Constants.toolBarHeight;
            this.rightPaddle.y = Constants.SCREEN_HEIGHT / 2.0 - Constants.toolBarHeight;

            if (scoringPlayer == 1) {
                this.vx = -300.0;
                this.vy = 10.0;
            } else if (scoringPlayer == 2) {
                this.vx = 300.0;
                this.vy = 10.0;
            }

            delay = false;
            scoringPlayer = 0;
        }

        if (vy > 0) {
            if (this.rect.y + this.rect.height > Constants.SCREEN_HEIGHT - Constants.insetBottom) {
                this.vy *= -1;
            }
        } else if (vy < 0) {
            if (this.rect.y < Constants.toolBarHeight) {
                this.vy *= -1;
            }
        }

        this.rect.x += vx * dt;
        this.rect.y += vy * dt;
    }
}
