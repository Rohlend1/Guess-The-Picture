import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Paints extends Painter{



    public Paints(String image, String name) throws IOException {
        this.name = name;
        this.image = new ImageIcon(image).getImage();
        }



    public void draw(Graphics g){
        g.drawImage(image, x, y, null);
    }

    public String getName(){
        return name;
    }
}
