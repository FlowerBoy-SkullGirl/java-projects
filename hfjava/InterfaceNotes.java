//An interface is like a completely abstract class that can be used for multiple inheritance
//eg
public interface Pet{
	public abstract void beFriendly();
	public abstract void Play();
}

public class Dog extends Animal implements Pet{
	public void beFriendly()
	{
		//etc
		//Takes the superclass version of the method
		super.eat();
	}
}
