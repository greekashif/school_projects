/*
authors: Kashif Ahmadi, Jia Chen
date:   April 17th, 2013
class: TicInterface.java
TicInterface setups the panel for TicTicToe game
All game logic is in this class
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class TicInterface extends JPanel implements ActionListener {
	
	private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
    private JPanel ticLevel, ticGame, ticScore, mainPanel;
    private JLabel normal, intermediate, difficult;
    private int move, emptySpot = -1;
    private int[] boardMoves;
    private int difficultLevel =0;
	private boolean canWin;
    public int humanScore, aiScore, tieScore;
    String levelName = null;
    ButtonGroup group;
    
    //create the board array for game logic
    public TicInterface() 
    {
		canWin = false;
   		move = 0;
        humanScore = aiScore = tieScore = 0;
		levelName = "naive";
        boardMoves = new int[9];
        for(int i = 0; i < 9; i++)
            boardMoves[i] = 0;

        setupTicCenter();
    }
    
    //reset the game configurations
    public void reset()
    {
		canWin = false;
        move = 0; emptySpot = -1;
        boardMoves = new int[9];
        for(int i = 0; i < 9; i++)
            boardMoves[i] = 0;
		
		b1.setText(""); b2.setText("");
		b3.setText(""); b4.setText("");
		b5.setText(""); b6.setText("");
		b7.setText(""); b8.setText("");
		b9.setText(""); 
    }
    //set up the main game panel 
    public void setupTicCenter() 
    {

        setLayout(new GridLayout(3,3));
        setBackground(Color.BLACK);

        b7 = new JButton(""); b8 = new JButton("");
        b9 = new JButton(""); b4 = new JButton("");
        b5 = new JButton(""); b6 = new JButton("");
        b1 = new JButton(""); b2 = new JButton("");
        b3 = new JButton("");

        b7.setFont( new Font("Helvetica", Font.BOLD, 180));
        b8.setFont( new Font("Helvetica", Font.BOLD, 180));
        b9.setFont( new Font("Helvetica", Font.BOLD, 180));
        b4.setFont( new Font("Helvetica", Font.BOLD, 180));
        b5.setFont( new Font("Helvetica", Font.BOLD, 180));
        b6.setFont( new Font("Helvetica", Font.BOLD, 180));
        b1.setFont( new Font("Helvetica", Font.BOLD, 180));
        b2.setFont( new Font("Helvetica", Font.BOLD, 180));
        b3.setFont( new Font("Helvetica", Font.BOLD, 180));
        
        add(b7); add(b8); add(b9);
        add(b4); add(b5); add(b6);
        add(b1); add(b2); add(b3);

        b7.addActionListener(this); b8.addActionListener(this);
        b9.addActionListener(this); b4.addActionListener(this);
        b5.addActionListener(this); b6.addActionListener(this);
        b1.addActionListener(this); b2.addActionListener(this);
        b3.addActionListener(this);
                                                   
        setVisible(true);
    }

    /*
    Sets the move for each player
    Pre-conditions: 'b' and 'index' are valid
    Post-conditions: move is set on the board if game is not over
    returns void
    */

    public void setButton(JButton b, int index)
    {

        if(move % 2 == 0 && !isTaken(index)) 
        {
            b.setText("X");
			boardMoves[index] = 1;
            ++move;

			if(hasThreeInaRow(1) || hasThreeInaRow(3)) {
                ++humanScore;
                ButtonPanel.updateHumanScore(humanScore);
				JOptionPane.showMessageDialog(null,"Game Over"
					+ "\nYou have won the game!\nHuman: " + 
                    humanScore + "\nComputer: " + aiScore +
                    "\nCat Games: " + tieScore, null,
					JOptionPane.PLAIN_MESSAGE);
				reset();
				return;
			}

			if(boardisFull()) {
				System.out.println("Game Over");
				JOptionPane.showMessageDialog(null,"Game Over"
					+ "\nTie game.", null,
					JOptionPane.PLAIN_MESSAGE);
                ++tieScore;
				reset();
			}

			else if(levelName.equalsIgnoreCase("naive"))
				playNaive();
			else if(levelName.equalsIgnoreCase("random"))
				playRandom();
			else if(levelName.equalsIgnoreCase("cutthroat"))
				playCutThroat();

			if(hasThreeInaRow(1) || hasThreeInaRow(3)) {
				for(int i = 0; i < 9; i++) 
					System.out.println(boardMoves[i]);
				JOptionPane.showMessageDialog(null,"Game Over"
					+ "\nComputer has won the game!", null,
					JOptionPane.PLAIN_MESSAGE);
                ++aiScore;
                ButtonPanel.updateAIscore(aiScore);
				reset();
				return;
			}
        }
    }
    /*
    Algorithm for naive difficulty
    Pre-cond: none
    Post-cond: naive computer places move on board
    return void
    */
   	
	public void playNaive()
	{
		for(int i = 0; i < 9; i++) {
			if(!isTaken(i)) {
				setComputerMove(i);
				boardMoves[i] = 3;
				break;
			}
		}
		++move;
	}
    /*
    Algorithm for random difficulty
    Pre-cond: none
    Post-cond: random computer places move on board
    return void
    */

	public void playRandom()
	{
		int index;
		Random rand = new Random();
		while(move % 2 == 1) {
			index = rand.nextInt(9);
			if(!isTaken(index)) {
				setComputerMove(index);
				boardMoves[index] = 3;
				++move;
			}
		}
	}
    /*
    Algorithm for cut-throat difficulty
    Pre-cond: none
    Post-cond: cut-throat computer places move on board
    return void
    */

	public void playCutThroat()
	{
		if(findBestMove(3))	 //possible winning move
		{
			setComputerMove(emptySpot);
			boardMoves[emptySpot] = 3;
			++move; emptySpot = -1; 
		}
		else if(findBestMove(1)) //block opponent's winning move
		{
			setComputerMove(emptySpot);
			boardMoves[emptySpot] = 3;
			++move; emptySpot = -1;
		}
		else if(canPlayCenter()) 
		{
			setComputerMove(4);
			boardMoves[4] = 3;
			++move;
		} 
		else if(canPlayCorner() && !avoidFork())
		{
			System.out.println("corner test");
			for(int i = 0; move % 2 == 1; i+=2) {
				if(!isTaken(i)) {
					setComputerMove(i);
					boardMoves[i] = 3;
					++move;
				}
			}
		}
		else {

			int index;
			Random rand = new Random();
			while(move % 2 == 1) {
				index = rand.nextInt(9);
				if(!isTaken(index)) {
					setComputerMove(index);
					boardMoves[index] = 3;
					++move;
				}
			} 
		}
	}
    /*
    Places the move for computer
    Pre-cond: index is valid
    Post-cond: computer's move is placed on board
    return void
    */		
	public void setComputerMove(int index)
	{
		switch(index + 1) {
			case 1: b7.setText("O"); break;
			case 2: b8.setText("O"); break;
			case 3: b9.setText("O"); break;
			case 4: b4.setText("O"); break;
			case 5: b5.setText("O"); break;
			case 6: b6.setText("O"); break;
			case 7: b1.setText("O"); break;
			case 8: b2.setText("O"); break;
			case 9: b3.setText("O"); break;
		}
	}
    /*
    Listens for the human player's move
    Pre-cond: e is valid
    Post-cond: triggers setButton() method
    return void
    */		 
    public void actionPerformed(ActionEvent e)
    {
       if(e.getSource() == b7) setButton(b7, 0);    
       if(e.getSource() == b8) setButton(b8, 1);    
       if(e.getSource() == b9) setButton(b9, 2);    
       if(e.getSource() == b4) setButton(b4, 3);    
       if(e.getSource() == b5) setButton(b5, 4);    
       if(e.getSource() == b6) setButton(b6, 5);    
       if(e.getSource() == b1) setButton(b1, 6);    
       if(e.getSource() == b2) setButton(b2, 7);    
       if(e.getSource() == b3) setButton(b3, 8);    

    }
	//returns true if spot is taken
    public boolean isTaken(int index)
    {
        return (boardMoves[index] == 1 || boardMoves[index] == 3); 
    }
    //returns true if board is full
	public boolean boardisFull()
	{
		boolean flag = true;
		for(int i = 0; i < 9; i++) {
			if(boardMoves[i] == 0)
				flag = false;
		}
		return flag;
	}
    //sets the difficulty from the radio buttons
	public void difficultyLevel(int level)
	{
		if(level == 1){
			System.out.println("naive selected");
			levelName = "naive";
		}
	
		else if(level == 2){
			System.out.println("random selected");
			levelName = "random";
		}
		else if(level == 3){
			System.out.println("cutthroat selected");
			levelName = "cutthroat";
        }
  	}
    //tallies the score
    public void tallyTicScore(boolean humanWon, boolean compWon)
    {
        if(humanWon) ++humanScore;
        else if(compWon) ++aiScore;
        else ++tieScore;
    }
    //returns true if player indicated by parameter
    //has 3 in a column	
	public boolean hasColumn(int plyr)
	{
		return boardMoves[0] == plyr && boardMoves[3] == plyr && 
		       boardMoves[6] == plyr || boardMoves[1] == plyr &&
		       boardMoves[4] == plyr && boardMoves[7] == plyr || 
		       boardMoves[2] == plyr && boardMoves[5] == plyr &&
		       boardMoves[8] == plyr;
	} 
    //returns true if player indicated by parameter
    //has 3 in a row
	public boolean hasRow(int plyr)
	{
		return boardMoves[0] == plyr && boardMoves[1] == plyr && 
		       boardMoves[2] == plyr || boardMoves[3] == plyr &&
		       boardMoves[4] == plyr && boardMoves[5] == plyr || 
		       boardMoves[6] == plyr && boardMoves[7] == plyr &&
		       boardMoves[8] == plyr;
	} 

    //returns true if player indicated by parameter
    //has 3 in a diagonal 
	public boolean hasDiag(int plyr)
	{
		return boardMoves[0] == plyr && boardMoves[4] == plyr &&
			   boardMoves[8] == plyr || boardMoves[6] == plyr &&
			   boardMoves[4] == plyr && boardMoves[2] == plyr;
	}
    //returns true if player has 3 in row, column, or diagonal
	public boolean hasThreeInaRow(int plyr)
	{
		return hasRow(plyr) || hasColumn(plyr) || hasDiag(plyr);
	}
    //returns true if player has two in a row
	public boolean hasTwoInRow(int plyr, int row)
	//0 = 1st row; 3 = 2nd row; 6 = 3rd row
	{
		return boardMoves[row] + boardMoves[row+1] + 
			   boardMoves[row+2] == plyr*2;
	}		
    //returns true if player has two in a column 
	public boolean hasTwoInColumn(int plyr, int col)
	{
		return boardMoves[col] + boardMoves[col+3] + 
			   boardMoves[col+6] == plyr*2;
	}		

    //returns true if player has two in a left diagonal 
	public boolean hasTwoInLeftDiag(int plyr)
	{
		return boardMoves[0] + boardMoves[4] + boardMoves[8] == plyr*2;
	}

    //returns true if player has two in a right diagonal 
	public boolean hasTwoInRightDiag(int plyr)
	{
		return boardMoves[2] + boardMoves[4] + boardMoves[6] == plyr*2;
	}
    //returns true if computer can win or block
    //human player from winning
	public boolean findBestMove(int p)
	{
		for(int i = 0; i < 7; i+=3) {
			if(hasTwoInRow(p, i)) {
				for(int j = i; j < i+3; j++) {
					System.out.println(boardMoves[j]);
					if(boardMoves[j] == 0) emptySpot = j;
				}
			}
		}

		for(int i = 0; i < 3; i++) {
			if(hasTwoInColumn(p, i)) {
				for(int j = i; j < i+7; j+=3) {
					if(boardMoves[j] == 0) emptySpot = j;
				}
			}
		}

		if(hasTwoInLeftDiag(p)) {
			for(int i = 0; i < 9; i+=4) {
				if(boardMoves[i] == 0) emptySpot = i;
			}
		}
		else if(hasTwoInRightDiag(p)) {
			for(int i = 2; i < 7; i+=2) {
				if(boardMoves[i] == 0) emptySpot = i;
			}
		}

		return emptySpot != -1;
	}
    //returns true if computer can play corner
	public boolean canPlayCorner()
	{
		return (!isTaken(0) || !isTaken(2) || !isTaken(6) || !isTaken(8))
				 && (!(boardMoves[0] + boardMoves[4] + boardMoves[8] == 7)
				 || !(boardMoves[2] + boardMoves[4] + boardMoves[6] == 7));
	}
    //returns true if computer can play center	
	public boolean canPlayCenter()
	{
		return !isTaken(4);
	}
    //returns true if human set up a fork
    public boolean avoidFork()
    {
        return boardMoves[0] == 1 && boardMoves[8] == 1 ||
               boardMoves[2] == 1 && boardMoves[6] == 1;
    }
    //sets the scoreboard
    public void setScore(int human, int comp)
    {
        humanScore = human;
        aiScore = comp; 
    }
    //returns the human's score
    public int getHumanScre()
    {
        return humanScore;
    }
    //returns the computer's score
    public int getCompScore()
    {
        return aiScore;
    }
}//class brace

