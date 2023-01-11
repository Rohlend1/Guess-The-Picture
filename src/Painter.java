import java.awt.*;

public abstract class Painter{
    protected final int x = 1600/2-200, y = 1080/2-300;
    protected String name;
    protected Image image;
    public abstract void draw(Graphics g);

}
