import java.util.ArrayList;
public class Ship{
	
	private ArrayList cellPos = new ArrayList();
	private String name;

	public void setCells(ArrayList pos)
	{
		cellPos = pos;
	}
	
	public void setName(String src)
	{
		name = src;
	}

	public String getName()
	{
		return name;
	}

	public String checkCell(String guessStr)
	{
		String result = "miss";
		int index = cellPos.indexOf(guessStr);
		
		if (index >= 0){
			cellPos.remove(index);
			result = "hit";
		}	
		if (cellPos.isEmpty()){
			result = "kill";
		}

		return result;
	}
}
