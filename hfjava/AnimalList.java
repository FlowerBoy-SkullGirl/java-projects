public class AnimalList{
	private Animal[] animals = new Animal[5];
	private int nextIndex = 0;

	public void add(Animal d)
	{
		if (nextIndex < animals.length){
			animals[nextIndex] = d;
			System.out.println("Animal added at " + nextIndex);
			nextIndex++;
		}
	}
}

