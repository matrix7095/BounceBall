import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new GameFrame();
            frame.setTitle("Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        });
    }
}

class GameFrame extends JFrame {
    private GameComponent gameComponent;
    public static final int STEPS = 10000000;
    public static final int DELAY = 3;
    private java.util.List<Ball> balls = new ArrayList<>();

    public GameFrame() {
        setTitle("Game");
        gameComponent = new GameComponent();
        add(gameComponent, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        toFront();
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);

    }

    public void addBall() {
        Ball ball = new Ball();
        int index = balls.size();

        balls.add(ball);


        gameComponent.add(ball);
        Runnable runnable = () -> {
            try {
                for (int i = 0; i <= STEPS; i++) {
                    ball.move(gameComponent.getBounds());
                    ball.calculate(balls, index);
                    gameComponent.repaint();
                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();

    }

}

class GameComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;
    private java.util.List<Ball> balls = new ArrayList<>();

    public void add(Ball b) {
        balls.add(b);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

