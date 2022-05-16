public class FlashCard{
	
	private String answer;
	private String question;
	
	public FlashCard(String q, String a)
	{
		question = q;
		answer = a;	
	}

	public String getAnswer()
	{
		return answer;
	}
	
	public String getQuestion()
	{
		return question;
	}
}
