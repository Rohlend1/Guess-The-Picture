import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class GameStatus {
    public static void end(Graphics g, Font font, AttributedString stringScore, int id){
        if (id == 1){
            AttributedString as = new AttributedString("Вы победили");
            as.addAttribute(TextAttribute.FONT, font);
            as.addAttribute(TextAttribute.FOREGROUND, Color.GREEN);
            g.drawString(as.getIterator(), 1600 / 2 - 70, 475);
            g.drawString(stringScore.getIterator(), 1600 / 2 - 62, 420);
        }
       else if(id == 2) {
            AttributedString as1 = new AttributedString("Вы проиграли");
            as1.addAttribute(TextAttribute.FOREGROUND, Color.red);
            as1.addAttribute(TextAttribute.FONT, font);
            g.drawString(as1.getIterator(), 1600 / 2 - 74, 475);
            g.drawString(stringScore.getIterator(), 1600 / 2 - 65, 420);
        }
    }
}
