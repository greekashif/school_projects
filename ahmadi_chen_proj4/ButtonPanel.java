    /*
    authors: Kashif Ahmadi, Jia Chen
    date:    April 17th, 2013
    class:   ButtonPanel.java
    ButtonPanel sets up the main interface that switches
    between the different games.
    */
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	
	public class ButtonPanel extends JPanel {
	
		private JButton tictactoe;
		private JButton guacamole;
		private JButton quit;
		private JButton playTic, playGuac;
		private JButton rules, guacInstructions, resetTic;
		private JButton backTic, backGuac, backPlayTic;
		private JLabel tictactoeMenu, guacMenu;
		private JLabel mainMenu;
		private JLabel greeting; //humnScore, pcScore;
	    private JPanel ticLevel, ticGame, ticScore, guacLevel;
	    private JRadioButton naive, random, cutthroat;
        private JRadioButton easy, intermediate, difficult;
	    private JFrame f;
	    private MainButtonPanel mainButtonPanel;
	    private TicButtonPanel ticButtonPanel;
        private GuacButtonPanel guacButtonPanel;
        private GuacGamePanel guacGamePanel;
        private TicTacToeGamePanel ticTacToeGamePanel;
        private TicInterface gamePanel;
        private ImageIcon advocado, tomato, ticIcon, ticMenu;
        public static int humanScore = 0, aiScore = 0, tieScore = 0;
        private int level=0, gLevel;
	    ButtonGroup group;
        GuacInterface guacInterface;
        TicInterface ticInterface = new TicInterface();
		private static JLabel humnScore, pcScore;
        private ImageIcon mainBG;
	
	
	public ButtonPanel()
	{
        mainBG = new ImageIcon("guacomole_mixed.jpg");
        tomato = new ImageIcon("ripe_tomato.png");
        ticIcon = new ImageIcon("tictactoe.png");
        advocado= new ImageIcon("advocado.png");
        ticMenu = new ImageIcon("ticmenu.png");
		frame();
	}
    //frame constructed    
	public void frame(){
		f = new JFrame("Arcade Games");
		mainButtonPanel = new MainButtonPanel();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.getContentPane().add(mainButtonPanel);
		f.setPreferredSize(new Dimension(1100,900));
        f.setLocationRelativeTo(null);
		f.pack();
		f.setVisible(true);
	}
	
    //constructs the main menu interface
	private class MainButtonPanel extends JPanel
	{
	
		public MainButtonPanel(){
			setup();
		}
		
		public void setup(){

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
			mainMenu = new JLabel("Arcade Game Room");
	        greeting = new JLabel("Welcome to the Arcade");
	
	        tictactoe = new JButton(ticIcon);
	        guacamole = new JButton(advocado);
	        quit = new JButton("Quit");
	        rules = new JButton("Rules");

            tictactoe.setOpaque(false);
            guacamole.setOpaque(false);
            tictactoe.setBorderPainted(false);
            guacamole.setBorderPainted(false);
            
			tictactoe.setAlignmentX(Component.CENTER_ALIGNMENT);
			guacamole.setAlignmentX(Component.CENTER_ALIGNMENT);
			quit.setAlignmentX(Component.CENTER_ALIGNMENT);
			rules.setAlignmentX(Component.CENTER_ALIGNMENT);
	
			mainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
			greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
	
			add(mainMenu);
			add(greeting);
	
			add(tictactoe, BorderLayout.CENTER);
			add(guacamole, BorderLayout.CENTER);
			add(rules, BorderLayout.CENTER);
			add(quit, BorderLayout.CENTER);
	
			tictactoe.addActionListener(new TicTacToeListener());
			guacamole.addActionListener(new GuacListener());
			quit.addActionListener(new QuitListener());
			rules.addActionListener(new RulesListener());
		}
	
	}
    
    //constructs the panel for TicTacToe menu	
	private class TicButtonPanel extends JPanel {

		public TicButtonPanel(){
			setup();
		}

		public void setup() {

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
			tictactoeMenu = new JLabel("Tic-Tac-Toe Game Menu");
	        playTic = new JButton(ticMenu);
            backTic = new JButton("Back to Main Menu");
	        quit = new JButton("Quit");

            playTic.setOpaque(false);
            playTic.setBorderPainted(false);
	
			tictactoeMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
			playTic.setAlignmentX(Component.CENTER_ALIGNMENT);
			backTic.setAlignmentX(Component.CENTER_ALIGNMENT);
			quit.setAlignmentX(Component.CENTER_ALIGNMENT);
	
			add(tictactoeMenu, BorderLayout.CENTER);
			add(playTic, BorderLayout.CENTER);
			add(backTic, BorderLayout.CENTER);
			add(quit, BorderLayout.CENTER);
	
            playTic.addActionListener(new PlayTicTacToeListener());
            backTic.addActionListener(new BackButtonListener());
			quit.addActionListener(new QuitListener());
	
			setVisible(true);
		}
	}
	//constructs the Guac-a-Mole main menu interface
	private class GuacButtonPanel extends JPanel {

		public GuacButtonPanel(){
			setupGuac();
		}
	
        public void setupGuac()
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
            guacMenu = new JLabel("Guac-a-Mole Game Menu");
            playGuac = new JButton(tomato);
            backGuac = new JButton("Back to Main Menu");
            quit = new JButton("Quit");
        
            playGuac.setOpaque(false);
            playGuac.setBorderPainted(false);

            guacMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
            playGuac.setAlignmentX(Component.CENTER_ALIGNMENT);
            backGuac.setAlignmentX(Component.CENTER_ALIGNMENT);
            quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
            add(guacMenu, BorderLayout.CENTER);
            add(playGuac, BorderLayout.CENTER);
            add(backGuac, BorderLayout.CENTER);
            add(quit, BorderLayout.CENTER);
        
            playGuac.addActionListener(new PlayGuacListener());
            quit.addActionListener(new QuitListener());
            backGuac.addActionListener(new BackButtonListener());
            setVisible(true);
        }
	}

    //constructs the game interface for TicTacToe	
	private class TicTacToeGamePanel extends JPanel{
	
		public TicTacToeGamePanel(){
            humanScore = aiScore = 0;
			setup();
            ticInterface = new TicInterface();
            add(ticInterface, BorderLayout.CENTER);
		}
		public void setup()
		{

            setLayout(new BorderLayout());

            ticLevel = new JPanel();
        
            tictactoeMenu = new JLabel("Tic-Tac-Toe Menu");
            JLabel difficulty = new JLabel("Select Difficulty:");
            JLabel score = new JLabel("ScoreBoard");
            humnScore = new JLabel("Human:\t" + humanScore);
            pcScore = new JLabel("Computer:\t" + aiScore);

            ticLevel.setLayout(new BoxLayout(ticLevel, BoxLayout.Y_AXIS));
        
            ticLevel.setBackground(Color.YELLOW);

            naive = new JRadioButton("Naive");
            random = new JRadioButton("Random");
            cutthroat = new JRadioButton("Cut-Throat");
            
            playTic = new JButton(tomato);
            resetTic = new JButton("Restart Game");
            backPlayTic = new JButton("Back to Main Menu");
            quit = new JButton("Quit");

            playTic.setOpaque(false);
            playTic.setBorderPainted(false);
        
            group = new ButtonGroup();
            group.add(naive);
            group.add(random);
            group.add(cutthroat);

			naive.doClick();

            ticLevel.add(tictactoeMenu);
            ticLevel.add(Box.createRigidArea(new Dimension(0,30)));
            ticLevel.add(difficulty);
            ticLevel.add(naive);
            ticLevel.add(random);
            ticLevel.add(cutthroat);
            ticLevel.add(Box.createRigidArea(new Dimension(0,30)));
            ticLevel.add(score);
            ticLevel.add(Box.createRigidArea(new Dimension(0,10)));
            ticLevel.add(humnScore);
            ticLevel.add(pcScore);
            ticLevel.add(Box.createRigidArea(new Dimension(0,30)));
            ticLevel.add(resetTic);
            ticLevel.add(backPlayTic);
            ticLevel.add(quit);

            add(ticLevel, BorderLayout.WEST);
            
            backPlayTic.addActionListener(new BackPlayTicListener());
            quit.addActionListener(new QuitListener());
            naive.addActionListener(new DifficultyListener());
            random.addActionListener(new DifficultyListener());
            cutthroat.addActionListener(new DifficultyListener());
			resetTic.addActionListener(new ResetListener());
            
            setVisible(true);
        }
        
	}
    //constructs the game interface for Guac-A-Mole
	private class GuacGamePanel extends JPanel{
	
		public GuacGamePanel(){
			guacSetup();
            guacInterface = new GuacInterface();
            add(guacInterface, BorderLayout.CENTER);
		}
		public void guacSetup()
		{
            setLayout(new BorderLayout());

            guacLevel = new JPanel();
        
            JLabel guacMenu = new JLabel("Guac-A-Mole!");
            JLabel difficulty = new JLabel("Select Difficulty:");

            guacLevel.setLayout(new BoxLayout(guacLevel, BoxLayout.Y_AXIS));
        
            guacLevel.setBackground(Color.YELLOW);

            easy = new JRadioButton("Easy");
            intermediate = new JRadioButton("Intermediate");
            difficult = new JRadioButton("Difficult");
            
            guacInstructions = new JButton("Gameplay Instructions");
            backGuac = new JButton("Back to Main Menu");
            quit = new JButton("Quit");
        
            group = new ButtonGroup();
            group.add(easy);
            group.add(intermediate);
            group.add(difficult);

			easy.doClick();

            guacMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
            backGuac.setAlignmentX(Component.CENTER_ALIGNMENT);
            difficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
            guacInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
            quit.setAlignmentX(Component.CENTER_ALIGNMENT);

            guacLevel.add(guacMenu);
            guacLevel.add(Box.createRigidArea(new Dimension(0,30)));
            guacLevel.add(difficulty);
            guacLevel.add(easy);
            guacLevel.add(intermediate);
            guacLevel.add(difficult);
            guacLevel.add(Box.createRigidArea(new Dimension(0,30)));
            guacLevel.add(Box.createRigidArea(new Dimension(0,30)));
            guacLevel.add(guacInstructions);
            guacLevel.add(backGuac);
            guacLevel.add(quit);
            
            add(guacLevel, BorderLayout.WEST);
            
            easy.addActionListener(new GuacDifficultyListener());
            intermediate.addActionListener(new GuacDifficultyListener());
            difficult.addActionListener(new GuacDifficultyListener());
            guacInstructions.addActionListener(new GuacRulesListener());
            backGuac.addActionListener(new BackPlayGuacListener());
            quit.addActionListener(new QuitListener());
            
            setVisible(true);
        }
    }
        
    //listeners that navigates the user back to main menu
    //from either the TicTicToe game interfaces
    private class BackPlayTicListener implements ActionListener {

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == backPlayTic) 
            {
                f.getContentPane().remove(ticTacToeGamePanel);
                f.validate();
                f.getContentPane().add(mainButtonPanel);
                f.validate();
                f.repaint();
            }/*
            else if(event.getSource() == backGuac)
                if(guacInterface != null) guacInterface.stopTimer();
				else {
                f.getContentPane().remove(guacGamePanel);
                f.validate();
                f.getContentPane().add(mainButtonPanel);
                f.validate();
                f.repaint();
			}*/
        }
    }
    //listeners that navigates the user back to main menu
    //from either the TicTicToe game interfaces
    private class BackPlayGuacListener implements ActionListener {

        public void actionPerformed(ActionEvent event)
        {
            if (guacInterface != null) guacInterface.stopTimer();

                f.getContentPane().remove(guacGamePanel);
                f.validate();
                f.getContentPane().add(mainButtonPanel);
                f.validate();
                f.repaint();
        }
    }

    //listeners that navigates the user back to main menu
    //from either the TicTicToe or Guacamole menus
    private class BackButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == backTic)
            { 
                f.getContentPane().remove(ticButtonPanel);
                f.validate();
                f.getContentPane().add(mainButtonPanel);
                f.validate();
                f.repaint();
            }
            else
            {
                if(guacInterface != null) guacInterface.stopTimer();
                f.getContentPane().remove(guacButtonPanel);
                f.validate();
                f.getContentPane().add(mainButtonPanel);
                f.validate();
                f.repaint();
            }
        }
    }
    //switches to Tic Tac Toe game interface
    private class PlayTicTacToeListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            f.getContentPane().remove(ticButtonPanel);
            f.validate();
            f.repaint();
            ticTacToeGamePanel = new TicTacToeGamePanel();
            f.getContentPane().add(ticTacToeGamePanel);
            f.validate();
            f.repaint();
            
        }
    }
    //switches to Tic Tac Toe menu
	private class TicTacToeListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e)
		{
			f.getContentPane().remove(mainButtonPanel);
			ticButtonPanel = new TicButtonPanel();
			f.getContentPane().add(ticButtonPanel);
			f.validate();
		}
	}

    //switches to Guac-a-mole game
    private class PlayGuacListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            f.getContentPane().remove(guacButtonPanel);
            f.validate();
            f.repaint();
            guacGamePanel = new GuacGamePanel();         
            f.getContentPane().add(guacGamePanel);
            f.validate();
            f.repaint();
        }
    }

    //swtiches to Guac-a-mole menu	
	private class GuacListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e)
		{
			f.getContentPane().remove(mainButtonPanel);
            f.validate();
            f.repaint();
			guacButtonPanel = new GuacButtonPanel();
			f.getContentPane().add(guacButtonPanel);
			f.validate();
            f.repaint();
		}
	}
    //resets Tic Tac Toe game
	private class ResetListener implements ActionListener {

		public void actionPerformed(ActionEvent e)
		{
			ticInterface.reset();
		}
	}
    
    //sets the Guac-a-mole difficulty
    private class GuacDifficultyListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == easy)
            {
                gLevel = 1;
                guacInterface.setDifficulty(gLevel);
            }
            if(e.getSource() == intermediate)
            {
                gLevel = 2;
                guacInterface.setDifficulty(gLevel);
            }
            if(e.getSource() == difficult)
            {
                gLevel = 3;
                guacInterface.setDifficulty(gLevel);
            }
        }
    }

    //sets the TicTacToe difficulty
    private class DifficultyListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
       	    if(e.getSource() == naive)
       	    {
        		level = 1;
        		ticInterface.difficultyLevel(level);
       	    }
                
            if(e.getSource() == random)
            {
           		level = 2;
           		ticInterface.difficultyLevel(level);
            }
                
            if(e.getSource() == cutthroat)
           	{
           		level = 3;
           		ticInterface.difficultyLevel(level);
           	}
        }
    }
             
    //listener for quit button that exits the program	
	private class QuitListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Arcade Game exited");
			System.exit(0);
		}
	}
    //pulls up a window that outlines the Guac-a-Mole rules
    private class GuacRulesListener implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null,"GAMEPLAY INSTRUCTIONS\n" +
            "==================\n" + "Ripe ingredients are worth 10 " +
            "points,\nbut rotten varieties will incur a 20 point penalty." +
            "\nRotten tomatos and rotten advocados will have dark bruises " +
			"and some discoloration.\nThe default difficulty is set on " +
			"'Easy'.\nHarder" + " difficulties require keen reflexes." + 
			"\nSelect a difficulty " + "level, and press PLAY to start " +
			"the game!\nGood Luck!",null, JOptionPane.PLAIN_MESSAGE);
        }
    }

    //pulls up a window in main menu that provides general rules
	private class RulesListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e)
		{
	        JOptionPane.showMessageDialog(null,"TIC-TAC-TOE:\nPlace three tokens" +
	        		" a row to win the game.\nThe player can duel with three different computer\n" +
	        		"opponents.\n\nGUAC-A-MOLE!\nClick on the area where a mole pops" +
	        		" up.\nAny misses will be deducted from your total score.\nThe player can select" +
	        		" from three different difficulty levels.",null, 
	        JOptionPane.PLAIN_MESSAGE);
			
		}
	}
    //updates scoreboard for human in TicTacToe
    public static void updateHumanScore(int num)
    {
        humanScore = num;
        humnScore.setText("Human:\t" + humanScore);
    }
    //updates scoreboard for computer in TicTacToe
    public static void updateAIscore(int num)
    {
        aiScore = num;
        pcScore.setText("Computer:\t" + aiScore);
    }

}//main class brace
    
