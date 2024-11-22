import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UI extends Block{
  
  private static Block[][] guesses;
  private static int startXPos;

  public UI(){
    super(700, 0, 0, Color.BLACK);
  }

  public static void createBoxes(Graphics window, int num)
	{
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, 700, 700);
    guesses = new Block[6][num];
    startXPos = (700 - 80*num - 10)/2;
      
    for (int i = 0; i < 6; i++){
      for(int j = 0; j < num; j++){
        Block temp = new Block(70, startXPos + 80*j, 100 + 80*i, Color.LIGHT_GRAY);
        guesses[i][j] = temp;
      }
    }

		drawBoxes(window);
  }

	public static void drawBoxes(Graphics window)
	{
		for (int i = 0; i < 6; i++){
      for(int j = 0; j < guesses[i].length; j++){
        guesses[i][j].draw(window);
      }
		}
	}

	public static void scoreScreen(Graphics window, Score score)
	{
   	Block temp = new UI();
		temp.draw(window);
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		window.drawString("Stats", 280, 125);
		window.drawString("Total Wins: " + score.getWins(), 240, 200);
		window.drawString("Current Streak: " + score.getStreak(), 200, 250);
		window.drawString("Maximum Streak: " + score.getMaxStreak(), 200, 300);
		window.drawString("Guess Distribution: " + score.getDistribution(), 50, 350);
		window.drawString(score.getGamesWon() + " Games Won", 220, 400);
  }
	
	public static void scoreScreen(Graphics window, Score score, int player)
	{
   	Block temp = new UI();
		temp.draw(window);
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		if(!PlayGame.single)
		{
			if(player == 1)
			{
				window.drawString("Player 2 Stats", 235, 125);
			}
			else
			{
				window.drawString("Player 1 Stats", 235, 125);
			}
		}
		window.drawString("Total Wins: " + score.getWins(), 240, 200);
		window.drawString("Current Streak: " + score.getStreak(), 200, 250);
		window.drawString("Maximum Streak: " + score.getMaxStreak(), 200, 300);
		window.drawString("Guess Distribution: " + score.getDistribution(), 50, 350);
		window.drawString(score.getGamesWon() + " Games Won", 220, 400);
  }

	public static void askForKey(Graphics window, MultiPlayer game, int player)
	{
   	Block temp = new UI();
		temp.draw(window);
		window.setFont(new Font("Courier", Font.PLAIN, 18));
		window.setColor(Color.GREEN);
		if(player == 1)
		{
			window.drawString("Enter a new word for Player 1 to guess", 100, 350);
		}
		else
		{
			window.drawString("Enter a new word for Player 2 to guess", 100, 350);
		}
  }

	public static void hint(Graphics window)
	{
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, 700, 90);
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		window.drawString("Enter 0 for a hint!" , 200, 60);
	}

	public static void drawHint(Graphics window, String hint)
	{
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, 700, 90);
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		window.drawString(hint, 100, 60);
	}

	public static void overHint(Graphics window)
	{
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, 700, 90);
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		window.drawString("You got all the hints!" , 190, 60);
	}

	public static void forfeit(Graphics window)
	{
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		window.drawString("Enter 0 to forfeit!", 200, 60);
	}

	public static void lose(Graphics window, String key)
	{
		Block temp = new UI();
		temp.draw(window);
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		window.drawString("You lost!", 275, 200);
		window.drawString("The word was \"" + key + "\"", 200, 250);
  }

	public static void drawForfeit(Graphics window, String forfeit)
	{
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, 700, 700);
		window.setFont(new Font("Courier", Font.PLAIN, 25));
		window.setColor(Color.GREEN);
		window.drawString(forfeit, 75, 200);
	}
	
  public static void turnGreen(Graphics window, int guess, int position)
	{
		Block temp = new Block(70, startXPos + 80*position, 100 + 80*(guess - 1), Color.GREEN);
		guesses[guess - 1][position] = temp;
    temp.draw(window);
  }

  public static void turnYellow(Graphics window, int guess, int position)
	{
		Color yellow = new Color(255, 240, 0);
    Block temp = new Block(70, startXPos + 80*position, 100 + 80*(guess - 1), yellow);
		guesses[guess - 1][position] = temp;
    temp.draw(window);
  }

  public static void turnGray(Graphics window, int guess, int position)
	{
    Block temp = new Block(70, startXPos + 80*position, 100 + 80*(guess - 1), Color.GRAY);
		guesses[guess - 1][position] = temp;
    temp.draw(window);
  }

  public static void drawLetter(Graphics window, String letter, int guess, int position)
	{
		int xPosition = guesses[guess - 1][position].getX() + 15;
		window.setColor(Color.WHITE);
		window.setFont(new Font("Courier", Font.BOLD, 55));
    window.drawString(letter.toUpperCase(), xPosition, guesses[guess - 1][position].getY()+55);
  }

}