package dto;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-19 15:29
 */
public class Reordering {
    int x = 0;
    int y = 0;

    public void writer () {
        x = 1;
        y = 2;
    }

    public void read() {
        int r1 = x;
        int r2 = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
