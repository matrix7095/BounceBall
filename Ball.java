import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Ball {
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = Math.random() + 0.5;
    private double dy = Math.random() + 0.5;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void move(Rectangle2D bounds) {
        x += dx;
        y += dy;

        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }

        if (x + XSIZE >= bounds.getMaxX()) {
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }

        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }

        if (y + YSIZE >= bounds.getMaxY()) {
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }


    }

    public void calculate(List<Ball> ballList, int index) {

        double distance;

        for (int i = 0; i < ballList.size(); i++) {
            if (i == index) {
                continue;
            }

            Ball otherBall = ballList.get(i);

            distance = Math.sqrt(Math.pow((otherBall.getX() - this.x), 2) + Math.pow((otherBall.getY() - this.y), 2));
            if (distance < XSIZE + 1) {

                reSet(otherBall);
            }


        }

    }

    public void reSet(Ball otherBall) {

        double dx = otherBall.getDx();
        double dy = otherBall.getDy();


        otherBall.setDx(this.getDx());
        otherBall.setDy(this.getDy());

        this.setDx(dx);
        this.setDy(dy);
    }


    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }


}
