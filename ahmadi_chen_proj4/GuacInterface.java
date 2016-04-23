import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GuacInterface extends JPanel implements ActionListener, 
                                                     MouseListener {

    private int x, y, randButton, randFood;
    private int currentScore, bestScore;
    private int timeDelay, currentTime, diffLevel;
    private boolean rottenClicked;
    private JButton b1,b2,b3,b4,b5,b6;
    private ImageIcon tomato, rottenTomato, onions;
    private ImageIcon seasoning, advocado, rottenAdvocado;
    private ImageIcon playG;
    private JPanel panel;
    private JPanel guacLevel, guacGame;
    private JLabel guacMenu, difficulty, score, time;
    private Timer timer, foodTimer;
    JRadioButton easy, intermediate, difficult;
    private JButton playGuac, backGuac, quit;
    ButtonPanel buttonPanel;
    ButtonGroup group;
    Random random;

    public GuacInterface()
    {
        bestScore = currentScore = 0;
        currentTime = 30;
        rottenClicked = false;
        timeDelay = 1000;
        random = new Random();
        
        playG = new ImageIcon("playGuac.png");
        tomato = new ImageIcon("ripe_tomato.png");
        advocado = new ImageIcon("advocado.png");
        onions = new ImageIcon("onions.png");
        seasoning = new ImageIcon("seasoning.png");
        rottenTomato = new ImageIcon("rotten_tomato.png");
        rottenAdvocado = new ImageIcon("rotten_advocado.png");
        setupGuac();    
    }

    public void reset()
    {
        rottenClicked = false;
        currentScore = 0;
        currentTime = 30;
        time.setForeground(Color.green);
    }

    public void setupGuac()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.green);

        JPanel scoreboard = new JPanel();
        JPanel game = new JPanel();

        game.setLayout(new GridLayout(2,3));
        
        foodTimer = new Timer(timeDelay, this);
        timer = new Timer(1000, this);
        //timer.start();
        setBackground(Color.green);
       
        time = new JLabel("TIME: " + currentTime + " "); 
        time.setFont(new Font("Helvetica", Font.BOLD,50));
        time.setForeground(Color.green);
        time.setAlignmentY(Component.RIGHT_ALIGNMENT);
        score = new JLabel(" SCORE: " + currentScore +
                           "\nBEST SCORE: " + bestScore);
        score.setFont(new Font("Helvetica", Font.BOLD,45));
        score.setAlignmentX(Component.LEFT_ALIGNMENT);

        playGuac = new JButton(playG);

        b1 = new JButton(); b2 = new JButton();
        b3 = new JButton(); b4 = new JButton();
        b5 = new JButton(); b6 = new JButton();

        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b6.setEnabled(false);

        b1.setOpaque(false);
        b2.setOpaque(false);
        b3.setOpaque(false);
        b4.setOpaque(false);
        b5.setOpaque(false);
        b6.setOpaque(false);
        playGuac.setOpaque(false);

        b1.setBorderPainted(false);
        b2.setBorderPainted(false);
        b3.setBorderPainted(false);
        b4.setBorderPainted(false);
        b5.setBorderPainted(false);
        b6.setBorderPainted(false);
        playGuac.setBorderPainted(false);

        scoreboard.add(time);
        scoreboard.add(playGuac);
        scoreboard.add(score);
         
        game.add(b1); game.add(b2); game.add(b3);
        game.add(b4); game.add(b5); game.add(b6);

        add(scoreboard);
        add(game);
        
        b1.addActionListener(new FoodListener());
        b2.addActionListener(new FoodListener());
        b3.addActionListener(new FoodListener());
        b4.addActionListener(new FoodListener());
        b6.addActionListener(new FoodListener());
        playGuac.addActionListener(new PlayGuacListener());
        addMouseListener(this);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) 
    {
        randButton = random.nextInt(6);
        setButtons(randButton);
        currentTime -= 1;

        if(currentTime == 10) {time.setText("TIME: " + currentTime + "    ");
            time.setForeground(Color.yellow); }
        else if(currentTime == 5) {time.setText("TIME: " + currentTime + 
            "    ");
            time.setForeground(Color.red); }
        else time.setText("TIME: " + currentTime + "    ");

        repaint();
        if(currentTime == 0) {
            foodTimer.stop();
            if(bestScore < currentScore) bestScore = currentScore;
            JOptionPane.showMessageDialog(null,"Time's Up!\n" + 
            "CURRENT SESSION SCORE: " + currentScore + "\n\nHIGH SCORE: " +
            bestScore,null, JOptionPane.PLAIN_MESSAGE);
            repaint();
            reset();
            score.setText(" SCORE: " + currentScore +
                               " BEST SCORE: " + bestScore);
        }
        
    }
  
    public void setButtons(int num)
    { 
        switch(num+1) {
            
            case 1:
                randFood = random.nextInt(6);
                setImageButton(randFood);
                break;
            case 2:
                randFood = random.nextInt(6);
                setImageButton(randFood);
                break;
            case 3:
                randFood = random.nextInt(6);
                setImageButton(randFood);
                break;
            case 4:
                randFood = random.nextInt(6);
                setImageButton(randFood);
                break;
            case 5:
                randFood = random.nextInt(6);
                setImageButton(randFood);
                break;
            case 6:
                randFood = random.nextInt(6);
                setImageButton(randFood);
                break;
        }

    }

    private class FoodListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == b1 && b1.isEnabled()) {
                if(rottenClicked) currentScore -= 20;
                else currentScore += 10;

                score.setText(" SCORE: " + currentScore +
                               "\nBEST SCORE: " + bestScore);
                b1.setEnabled(false);
                b1.setIcon(null);
            }
            if(e.getSource() == b2 && b2.isEnabled()) {
                if(rottenClicked) currentScore -= 20;
                else currentScore += 10;

                score.setText(" SCORE: " + currentScore +
                               "\nBEST SCORE: " + bestScore);
                b2.setEnabled(false);
                b2.setIcon(null);
                
            }
            if(e.getSource() == b3 && b3.isEnabled()) {
                if(rottenClicked) currentScore -= 20;
                else currentScore += 10;

                score.setText(" SCORE: " + currentScore +
                               "\nBEST SCORE: " + bestScore);
                b3.setEnabled(false);
                b3.setIcon(null);
            }
            if(e.getSource() == b4 && b4.isEnabled()) {
                if(rottenClicked) currentScore -= 20;
                else currentScore += 10;
                
                score.setText(" SCORE: " + currentScore +
                               "\nBEST SCORE: " + bestScore);
                b4.setEnabled(false);
                b4.setIcon(null);
            }
            if(e.getSource() == b6 && b6.isEnabled()) {
                if(rottenClicked) currentScore -= 20;
                else currentScore += 10;
                
                score.setText(" SCORE: " + currentScore +
                               "\nBEST SCORE: " + bestScore);
                b6.setEnabled(false);
                b6.setIcon(null);
                
            }

        }
    }

    public void setDifficulty(int level)
    {
        diffLevel = level;
        if(diffLevel == 1) {
            System.out.println("Easy selected");
            foodTimer.setDelay(900);
        }
        if(diffLevel == 2) {
            System.out.println("Intermediate selected");
            foodTimer.setDelay(700);
        }
        if(diffLevel == 3) {
            System.out.println("Difficult selected");
            foodTimer.setDelay(500);
        }
    }
    public void setImageButton(int num)
    {
        b1.setIcon(null); 
        b2.setIcon(null);
        b3.setIcon(null);
        b4.setIcon(null);
        b5.setIcon(null);
        b6.setIcon(null);

        if(num == 0) {
            randFood = random.nextInt(6); 
            switch(randFood+1) {
               case 1:
                    b1.setIcon(tomato);
                    rottenClicked = false;
                    break;
               case 2:
                    b1.setIcon(advocado);
                    rottenClicked = false;
                    break;
               case 3:
                    b1.setIcon(seasoning);
                    rottenClicked = false;
                    break;
               case 4:
                    b1.setIcon(onions);
                    rottenClicked = false;
                    break;
               case 5:
                    b1.setIcon(rottenTomato);
                    rottenClicked = true;
                    break;
               case 6:
                    b1.setIcon(rottenAdvocado);
                    rottenClicked = true;
                    break;
            }
            b1.setEnabled(true);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            b6.setEnabled(false);
            
        }
        else if(num == 1) {
            randFood = random.nextInt(6); 
            
            switch(randFood+1) {
               case 1:
                    b2.setIcon(tomato);
                    rottenClicked = false;
                    break;
               case 2:
                    b2.setIcon(advocado);
                    rottenClicked = false;
                    break;
               case 3:
                    b2.setIcon(seasoning);
                    rottenClicked = false;
                    break;
               case 4:
                    b2.setIcon(onions);
                    rottenClicked = false;
                    break;
               case 5:
                    b2.setIcon(rottenTomato);
                    rottenClicked = true;
                    break;
               case 6:
                    b2.setIcon(rottenAdvocado);
                    rottenClicked = true;
                    break;
            }
            b1.setEnabled(false); 
            b2.setEnabled(true);
            b3.setEnabled(false);
            b4.setEnabled(false);
            b6.setEnabled(false);
        }
        else if(num == 2) {

            randFood = random.nextInt(6); 
            switch(randFood+1) {
               case 1:
                    b3.setIcon(tomato);
                    rottenClicked = false;
                    break;
               case 2:
                    b3.setIcon(advocado);
                    rottenClicked = false;
                    break;
               case 3:
                    b3.setIcon(seasoning);
                    rottenClicked = false;
                    break;
               case 4:
                    b3.setIcon(onions);
                    rottenClicked = false;
                    break;
               case 5:
                    b3.setIcon(rottenTomato);
                    rottenClicked = true;
                    break;
               case 6:
                    b3.setIcon(rottenAdvocado);
                    rottenClicked = true;
                    break;
            }
            b1.setEnabled(false); 
            b2.setEnabled(false);
            b3.setEnabled(true);
            b4.setEnabled(false);
            b6.setEnabled(false);

        }
        else if(num == 3) {
            
            randFood = random.nextInt(6); 
            switch(randFood+1) {
               case 1:
                    b4.setIcon(tomato);
                    rottenClicked = false;
                    break;
               case 2:
                    b4.setIcon(advocado);
                    rottenClicked = false;
                    break;
               case 3:
                    b4.setIcon(seasoning);
                    rottenClicked = false;
                    break;
               case 4:
                    b4.setIcon(onions);
                    rottenClicked = false;
                    break;
               case 5:
                    b4.setIcon(rottenTomato);
                    rottenClicked = true;
                    break;
               case 6:
                    b4.setIcon(rottenAdvocado);
                    rottenClicked = true;
                    break;
            }
            b1.setEnabled(false); 
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(true);
            b6.setEnabled(false);
        }
        else if(num == 5) {
            
            randFood = random.nextInt(6); 
            switch(randFood+1) {
               case 1:
                    b6.setIcon(tomato);
                    rottenClicked = false;
                    break;
               case 2:
                    b6.setIcon(advocado);
                    rottenClicked = false;
                    break;
               case 3:
                    b6.setIcon(seasoning);
                    rottenClicked = false;
                    break;
               case 4:
                    b6.setIcon(onions);
                    rottenClicked = false;
                    break;
               case 5:
                    b6.setIcon(rottenTomato);
                    rottenClicked = true;
                    break;
               case 6:
                    b6.setIcon(rottenAdvocado);
                    rottenClicked = true;
                    break;
            }
            b1.setEnabled(false); 
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            b6.setEnabled(true);
        }
    }

    public void mousePressed(MouseEvent e)
    {
        //currentScore += 10;
        //score.setText("SCORE: " + currentScore);
    } 

    public void mouseClicked(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}

    private class PlayGuacListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            foodTimer.start();
        }

    }

    private class QuitListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Arcade game exited");
            System.exit(0);
        }

    }

    public void stopTimer()
    {
        foodTimer.stop();
    }

}//main class brace


        
       
