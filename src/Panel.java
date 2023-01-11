import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Panel extends JPanel {
    private static final ArrayList<String> currentLevel = new ArrayList<>(4);
    private static final ArrayList<String> previousLevel = new ArrayList<>();
    private static final JButton jButtonReset = new JButton("Заново");
    private static final Button jButton1 = new Button();
    private static final Button jButton2 = new Button();
    private static final Button jButton3 = new Button();
    private static final Button jButton4 = new Button();
    private static final ArrayList<String> names = new ArrayList<>(Arrays.asList(
            "Явление Христа народу","Богатыри", "Мона лиза", "Утро в сосновом бору", "Ночной дозор",
            "В голубом просторе", "Пор-Манеш", "Звездная ночь", "Приближение грозы", "Постоянство времени",
            "Вавилонская башня"
            ));
    public static byte lifes = 2;
    public static byte score = 0;
    private static String answer = null;
    private static final Color BACKGROUND = Color.getHSBColor(22,33,45);
    public static final Color COLOR = Color.getHSBColor(37,100,15);
    private static final HashMap<String, Paints> paintsMap = new HashMap<>();
    private static final Font font = new Font("Times New Roman", Font.BOLD,24);
    private static final JFrame frame = new JFrame();
    private static Component panel = anotherPanel();
    private static int id = 0;

    public static void main(String[] args) {
        jButton1.setBackground(COLOR);
        jButton2.setBackground(COLOR);
        jButton3.setBackground(COLOR);
        jButton4.setBackground(COLOR);
        jButtonReset.setBackground(COLOR);
        jButton1.addActionListener(jButton1);
        jButton2.addActionListener(jButton2);
        jButton3.addActionListener(jButton3);
        jButton4.addActionListener(jButton4);
        jButtonReset.addActionListener(e->{
            if(id != 0){
                id = 0;
                restart();
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private Panel() {
        setLayout(null);
        Creater.createPaints(paintsMap, names);

        if(answer == null){
            answer = names.get((int) (Math.random() * names.size()));
            while (previousLevel.contains(answer)) {
                answer = names.get((int) (Math.random() * names.size()));
            }
        }
        currentLevel.add(answer);
        for(int i = 0; i < 3; i++){
            String value = names.get((int)(Math.random()*names.size()));
            while(value.equals(answer) || currentLevel.contains(value)){
                value = names.get((int)(Math.random()*names.size()));
            }
            currentLevel.add(value);
        }
        previousLevel.add(answer);
        Collections.shuffle(currentLevel);
        jButton1.setText("A: " + currentLevel.get(0));
        jButton2.setText("B: "+ currentLevel.get(1));
        jButton3.setText("C: " +currentLevel.get(2));
        jButton4.setText("D: " +currentLevel.get(3));

        buttonVisible(true);
        jButtonReset.setBounds(1600/2-125,530,250,50);
        jButton1.setBounds(1600/2-350,650,300,100);
        jButton2.setBounds(1600/2+50,650, 300, 100);
        jButton3.setBounds(1600/2-350,850,300,100);
        jButton4.setBounds(1600/2+50,850, 300, 100);
        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jButtonReset);
        revalidate();
        setBackground(BACKGROUND);
        setPreferredSize(new Dimension(1600, 1080));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        onRepaint(g);
        repaint();
    }

    private void onRepaint(Graphics g){
        AttributedString stringScore = new AttributedString("Ваш счёт: " + score);
        AttributedString stringLifes = new AttributedString("x " + lifes);
        stringLifes.addAttribute(TextAttribute.FONT, font);
        stringScore.addAttribute(TextAttribute.FONT, font);
        if (id == 1 || id == 2){
            GameStatus.end(g, font, stringScore, id);
        }

        else{
            g.drawString(stringScore.getIterator(), 50, 50);
            g.drawImage(new ImageIcon("HUD\\heart.png").getImage(),50, 70,null);
            g.drawString(stringLifes.getIterator(), 110, 100);
            if (answer != null) paintsMap.get(answer).draw(g);
        }
    }

    public static void reset(){
        if(previousLevel.size() == names.size()){
            id = 1;
            buttonVisible(false);
        }
        else if(lifes == 0){
            id = 2;
            buttonVisible(false);
        }
        if(id == 0){
            currentLevel.clear();
            while (previousLevel.contains(answer)) {
                answer = names.get((int) (Math.random() * names.size()));
            }
            frame.getContentPane().removeAll();
            panel = anotherPanel();
            frame.getContentPane().add(panel);
            frame.revalidate();
        }
    }

    private static void restart(){
        lifes = 2;
        score = 0;
        previousLevel.clear();
        reset();
    }
    public static String getAnswer(){
        return answer;
    }

    private static Panel anotherPanel(){
        return new Panel();
    }

    private static void buttonVisible(boolean b) {
        jButton1.setVisible(b);
        jButton2.setVisible(b);
        jButton3.setVisible(b);
        jButton4.setVisible(b);
        jButtonReset.setVisible(!b);
    }

}