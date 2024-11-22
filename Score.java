import java.util.*;

public class Score
{
	private int wins;
	private int totalGames;
	private int maxStreak;
	private int streak;
	private int[] guesses;
  private boolean win; 
	
	public Score()
	{
		wins = 0;
		totalGames = 0;
		maxStreak = 0;
		streak = 0;
		guesses = new int[6];
		for(int i = 0; i < 6; i++)
		{
			guesses[i] = 0;
		}
	}

	public void addGuessArr(int a)
	{
		guesses[a]++; 
	}

  public void win(){
		totalGames++;
    wins++;
		streak++;
		maxStreak = Math.max(streak, maxStreak);
    win = true; 
  }

	public void lose()
	{
		totalGames++;
		streak = 1;
    win = false; 
	}

	public int getWins()
	{
		return wins;
	}

	public int getStreak()
	{
		return streak; 
	}

	public int getMaxStreak()
	{
		return wins;
	}

	public String getDistribution()
	{
		return Arrays.toString(guesses);
	}

	public String getGamesWon()
	{
		return String.format("%.0f", (double)wins/(double)totalGames * 100) + "%";
	}
	
	public String toString()
	{
    return "  Wins: " + wins + "\n  Streak: " + streak + "\n  Max Streak: " + maxStreak + "\n  Guess Distribution: " + Arrays.toString(guesses) + "\n  " + wins + "/" + totalGames + " games won";
		
	}
}