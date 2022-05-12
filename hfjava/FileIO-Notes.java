//creates output file named MyGame.ser by making a fileo object
FileOutputStream fileStream = new FileOutputStream("MyGame.ser"); 

//Makes an output stream that needs to be fed into a file output stream
ObjectOutputStream os = new ObjectOutputStream(fileStream); //fileStream is the stream its feeding into, like piping

os.writeObject(characterOne); //writes an object serially, and pipes this into the filestream

os.close(); //apparently this will close all streams, including filestream

//Object streams are called CHAINS
//File streams are called CONNECTIONS
//For the properties they have above

//TO DO THIS, must do as follows
import java.io.*;

public class character implements Serializable //has to be serializable
	public Duck duck = new Duck(); //duck has to be serializable too!

//FIle Output must be in a try{}catch() block

//ALL references to objects in a serialized object MUST also be serializable, see above
//If you use an object that cannot or should not be serialized, use the word transient to prevent it from being saved

class Character implements Serializable
	transient String currentString; //will not be serialized or saved

//INPUT STREAMS
FileInputStream fileStream = new FileInputStream("MyGame.ser"); //will throw an exception if file does not exist

ObjectInputStream os = new ObjectInputStream(fileStream);

Object one = os.readObject(); //Will read objects in the order they were saved

GameCharacter elf = (GameCharacter) one; //must cast the Object object back to its class

os.close();

//PLAIN TEXT
//to write plain text, use FileWriter objects
//All the below can throw an exception except the import
import java.io.*;

FileWriter writer = new FileWriter("Game.txt"); //Creates file if it doesn't exist

writer.write("Hello world");

writer.close();


//FILE OBJECTS
File f = new File("FIlename.ext");
	
f.mkdir(); //makes the file a directory

//Show contents of a dir
if (f.isDirectory()){
	String[] dirContents = f.list();
	for (int i = 0; i < dirContents.length; i++){
		System.out.println(dirContents[i]);
	}	
}

f.getAbsolutePath(); //Self explanatory

f.delete(); //returns a bool of success value

//BufferedWriter writes to a buffer before disk, so you move data only when buffer is full and more efficiently or until you use bufferwriter.flush(); method


