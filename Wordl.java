import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;

public class Wordl extends Canvas implements Runnable
{
	private Score player1Score;
	private Score player2Score;
	private static ArrayList<Score> scoreList;
	private String key;
	private String guess;
	private static char[] check;
	private static int guesses;
	private BufferedImage back;
	private boolean[] correct;
	private int hm;
	private boolean forfeit;
	private boolean[] hints;
  private int counter;

	public Wordl() 
	{
		setWord();
		check = new char[key.length()];
		guesses = 1;
		player1Score = new Score();
		scoreList = new ArrayList<Score>();
		scoreList.add(player1Score);
		if(!PlayGame.single)
		{
			player2Score = new Score();
			scoreList.add(player2Score);
		}
		correct = new boolean[key.length()];
		hm = 0;
		hints = new boolean[key.length()];
		counter = 1;

		setFocusable(true);
		requestFocus();
		new Thread(this).start();
		setVisible(true);
	}

	public Wordl(String s) 
	{
		key = s;
		check = new char[key.length()];
		guesses = 1;
		player1Score = new Score();
		scoreList = new ArrayList<Score>();
		scoreList.add(player1Score);
		if(!PlayGame.single)
		{
			player2Score = new Score();
			scoreList.add(player2Score);
		}
		correct = new boolean[key.length()];
		hm = 0;
		hints = new boolean[key.length()];
		counter = 1;
		
		setFocusable(true);
		requestFocus();
		new Thread(this).start();
		setVisible(true);
	}

	public void update(Graphics window) 
	{
		paint(window);
	}

	public void run() 
	{
	}

	public void paint(Graphics window) 
	{
		Graphics2D twoDGraph = (Graphics2D) window;

		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		Graphics graphToBack = back.createGraphics();

		if (hm == 0) 
		{
			UI.createBoxes(graphToBack, key.length());
			hm++;
		}

		UI.drawBoxes(graphToBack);
		
		if(PlayGame.single)
		{
			UI.hint(graphToBack);
		}
		else
		{
			UI.forfeit(graphToBack);
		}
		twoDGraph.drawImage(back, 0, 0, null);

		boolean win = false;
		System.out.println("\nStart Guessing!");

		while (guesses <= 6 && !win) 
		{
			System.out.println("\nGuess #" + guesses + ":");
			Scanner input = new Scanner(System.in);
			guess = input.nextLine();
			win = checkWord(graphToBack, guess, twoDGraph, back);
			twoDGraph.drawImage(back, 0, 0, null);
			if (win) 
			{
				System.out.println("\nYou won!");
				if(PlayGame.single || counter % 2 != 0)
				{
					player1Score.win();
					player1Score.addGuessArr(guesses - 1);
				}
				else
				{
					player2Score.win();
					player2Score.addGuessArr(guesses - 1);
				}
				win = true;
			}
		}
		
		if (!win) 
		{
			System.out.println("\nYou lost! The answer was " + key);
			if(PlayGame.single || counter % 2 != 0)
			{
				player1Score.lose();
			}
			else
			{
				player2Score.lose();
			}
		}

		guesses = 1;
		hm = 0;

		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
		}

		if(!win && !forfeit)
		{
			UI.lose(graphToBack, key);
			twoDGraph.drawImage(back, 0, 0, null);
			try 
			{
				Thread.sleep(5000);
			} 
			catch (InterruptedException e) 
			{
			}
		}

		if(forfeit)
		{
			try 
			{
				Thread.sleep(5000);
			} 
			catch (InterruptedException e) 
			{
			}
		}

		if(PlayGame.single)
		{
			UI.scoreScreen(graphToBack, player1Score);
		}
		else if(counter % 2 != 0)
		{
			UI.scoreScreen(graphToBack, player1Score, counter % 2);
		}
		else
		{
			UI.scoreScreen(graphToBack, player2Score, counter % 2);
		}
		twoDGraph.drawImage(back, 0, 0, null);

		try 
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
		}
		
		if(!PlayGame.single)
		{
			System.out.println("\nEnter a new key:");
			UI.askForKey(graphToBack, (MultiPlayer)this, counter % 2);
			twoDGraph.drawImage(back, 0, 0, null);
			setWord();
		}

		counter++;

		PlayGame.playGame.init();
	}

	public void setWord() 
	{
		Scanner word = new Scanner(System.in);
		String s = word.nextLine();
		while(s.length() < 5 || s.length() > 7)
		{
			s = word.nextLine();
		}
		setKey(s);
	}

	public void setKey(String s) 
	{
		key = s;
		hints = new boolean[key.length()];
		check = new char[key.length()];
		correct = new boolean[key.length()];
		for(int i = 0; i < key.length(); i++)
		{
			hints[i] = false;
		}
	}

	public String getKey()
	{
		return key;
	}

	public boolean checkWord(Graphics window, String guess, Graphics2D twoDGraph, BufferedImage back) 
	{
		if(guess.equals("0"))
		{
			if(PlayGame.single)
			{
				SinglePlayer g = (SinglePlayer)this;
				int at = (int)(Math.random()*5);
				boolean allHints = true;
				for(int i = 0; i < key.length(); i++)
				{
					if(!hints[i])
					{
						allHints = false;
					}
				}
				if(allHints)
				{
					UI.overHint(window);
					return false;
				}
				
				while(hints[at])
				{
					at = (int)(Math.random()*5);
				}
				hints[at] = true;
				UI.drawHint(window, g.giveHint(at));
				for(int i = 0; i < 5; i++)
				{
					if(i != at)
					{
						grey(window, i);
					}
				}
				green(window, at);
				UI.drawLetter(window, key.substring(at, at + 1), guesses, at);
				twoDGraph.drawImage(back, 0, 0, null);
				
				try 
				{
					Thread.sleep(2000);
				} catch (InterruptedException e) 
				{
				}

				UI.hint(window);
				guesses++;
			}
			else
			{
				MultiPlayer g = (MultiPlayer)this;
				UI.drawForfeit(window, g.forfeit());
				forfeit = true;
				guesses = 7;
			}
			return false;
		}
		if (guess.length() != key.length()) 
		{
			return false;
		}
		if (guess.equalsIgnoreCase(key)) 
		{
			for (int i = 0; i < guess.length(); i++) 
			{
				green(window, i);
				UI.drawLetter(window, guess.substring(i, i + 1), guesses, i);
			}
			return true;
		}

		int[] alphabet = new int[26];
		for (int i = 0; i < key.length(); i++) 
		{
			alphabet[toLowerCase(key.charAt(i)) - 97]++;
			correct[i] = false;
			check[i] = 'e';
		}
		
		for (int i = 0; i < guess.length(); i++) 
		{
			if (guess.substring(i, i + 1).equalsIgnoreCase(key.substring(i, i + 1))
					&& alphabet[toLowerCase(key.charAt(i)) - 97] > 0) 
			{
				green(window, i);
				alphabet[toLowerCase(key.charAt(i)) - 97]--;
				correct[i] = true;
			}
		}
		for (int i = 0; i < guess.length(); i++) 
		{
			if (!correct[i])
			{
				for (int j = 0; j < guess.length(); j++) 
				{
					if (guess.substring(j, j + 1).equalsIgnoreCase(key.substring(i, i + 1)) && alphabet[toLowerCase(key.charAt(i)) - 97] > 0 && i != j && !correct[j]) 
					{
						yellow(window, j);
						alphabet[toLowerCase(key.charAt(i)) - 97]--;
					}
				}
				if (check[i] != 'y') 
				{
					grey(window, i);
				}
			}
		}

		System.out.println();

		for (int i = 0; i < guess.length(); i++) 
		{
			String letter = guess.substring(i, i + 1);
			UI.drawLetter(window, letter, guesses, i);
		}

		if (guesses <= 6) 
		{
			guesses++;
		}
				
		return false;
	}

	public static void green(Graphics window, int idx) 
	{
		check[idx] = 'g';
		UI.turnGreen(window, guesses, idx);
	}

	public static void yellow(Graphics window, int idx) 
	{
		check[idx] = 'y';
		UI.turnYellow(window, guesses, idx);
	}

	public void grey(Graphics window, int idx)
	{
		check[idx] = 'x';
		UI.turnGray(window, guesses, idx);
	}

	public void reset() 
	{
		scoreList = new ArrayList<Score>();
		System.out.print("All scores reset!");
	}
	
	public int getTotalGames() 
	{
		return scoreList.size();
	}
}