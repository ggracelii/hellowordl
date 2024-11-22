public class SinglePlayer extends Wordl
{
	private static final String[] WORDS = {"array", "javas", "float", "ascii", "cache", "stack", "apple", "arial", "click", "scope", "debug", "close", "crash", "email", "excel", "field", "frame", "fonts", "image", "input", "intel", "logic", "morph", "mouse", "paint", "pixel", "reset", "robot", "table", "virus", "white", "house"};

	public SinglePlayer()
	{
		super(WORDS[(int)(Math.random() * WORDS.length)]);
	}
	
	public SinglePlayer(String word)
	{
		super(word);	
	}

	public void resetKey()
	{
		super.setKey(WORDS[(int)(Math.random() * WORDS.length)]);
	}

  public String giveHint(int at){
		return "Hint: The letter in position "+ (at + 1) + " is " + getKey().charAt(at);
  }
}