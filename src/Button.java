import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
            if(this.getText().split(": ")[1].equals(Panel.getAnswer())){
                Panel.score+=1;
                Panel.reset();
            }
            else{
                Panel.score -= 1;
                Panel.lifes-=1;
                Panel.reset();
            }
    }
}
