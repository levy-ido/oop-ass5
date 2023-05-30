package game;

import auxiliary.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.collision.Collidable;
import game.listener.BallRemover;
import game.listener.BlockRemover;
import game.listener.ScoreTrackingListener;
import game.sprites.Background;
import game.sprites.Ball;
import game.sprites.Block;
import game.sprites.Paddle;
import game.sprites.ScoreIndicator;
import game.sprites.Sprite;

import java.awt.Color;

/**
 * Holds the sprites and collidables. Responsible for animating the sprites.
 */
public class Game {
    public static final int NUM_OF_BLOCKS = 57;
    private static final int NUM_OF_BALLS = 3;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;
    private final Counter removedBlocks;
    private final Counter removedBalls;
    private final Counter scoreCounter;

    /**
     * Constructs a new Game object.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.removedBlocks = new Counter(0);
        this.removedBalls = new Counter(0);
        this.scoreCounter = new Counter(0);
    }
    /**
     * Adds a given collidable to the games' environment.
     * @param c A Collidable representing the collidable to add to the games' environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a given sprite to the games' sprites.
     * @param s A Sprite representing the sprite to add to the games' sprites
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Creates the borders of the game.
     */
    private void createBorders() {
        new Block(0.0, 0.0, 20.0, 600.0, Color.GRAY).addToGame(this);
        new Block(20.0, 0.0, 760.0, 20.0, Color.GRAY).addToGame(this);
        new Block(780.0, 0.0, 20.0, 600.0, Color.GRAY).addToGame(this);
        Block bottomBorder = new Block(20.0, 580.0, 760.0, 20.0, Color.GRAY);
        bottomBorder.addHitListener(new BallRemover(this, this.removedBalls));
        bottomBorder.addToGame(this);
    }

    /**
     * Creates the balls of the game.
     */
    private void createBalls() {
        for (int i = 0; i < NUM_OF_BALLS; ++i) {
            Ball b = new Ball(30.0 + 40.0 * i, 300.0 - 10.0 * i, 10, Color.WHITE);
            b.setVelocity(0.0, 3.0);
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
    }

    private void createPaddle() {
        Paddle paddle = new Paddle(350.0, 550.0, 100.0, 30.0, Color.YELLOW, gui.getKeyboardSensor());
        paddle.addToGame(this);
    }

    /**
     * Initializes this game.
     */
    public void initialize() {
        this.gui = new GUI("Game Title", 800, 600);
        createBackground();
        createBorders();
        createBalls();
        createPaddle();
        createBlocks();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        scoreIndicator.addToGame(this);
    }

    /**
     * Creates the games' background.
     */
    private void createBackground() {
        new Background(20.0, 20.0, 760.0, 560.0, Color.BLUE).addToGame(this);
    }

    /**
     * Creates the games' block patterns.
     */
    private void createBlocks() {
        Color[] colorArray = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        double blockWidth = 760.0 / 16.0;
        double blockHeight = 560.0 / 20.0;
        double blockX = 20.0 + 4.0 * blockWidth;
        BlockRemover blockRemover = new BlockRemover(this, this.removedBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        for (int i = 0; i < colorArray.length; ++i) {
            double y = 20.0 + 3.0 * blockHeight + i * blockHeight;
            for (int j = 0; j < 12 - i; ++j) {
                double x = blockX + j * blockWidth;
                Block b = new Block(x, y, blockWidth, blockHeight, colorArray[i]);
                b.addHitListener(blockRemover);
                b.addHitListener(scoreTrackingListener);
                b.addToGame(this);
            }
            blockX += blockWidth;
        }
    }

    /**
     * Animates the game.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (this.removedBlocks.getValue() != NUM_OF_BLOCKS && this.removedBalls.getValue() != NUM_OF_BALLS) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.removedBlocks.getValue() == NUM_OF_BLOCKS) {
            this.scoreCounter.increase(100);
        }
        gui.close();
    }

    /**
     * Removes the given collidable from this games' game environment.
     * @param c A Collidable to be removed from this games' game environment
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Removes the given sprite from this games' sprite collection.
     * @param s A Sprite to be removed from this games' sprite collection
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }
}
