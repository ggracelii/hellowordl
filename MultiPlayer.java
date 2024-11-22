public class MultiPlayer extends Wordl
{	
	public MultiPlayer()
	{
		super();
	}
	
	public MultiPlayer(String word)
	{
		super(word);
	}

  public String forfeit(){
		return "You forfeited! The word was " +  "\"" + getKey() + "\"";
  }
}