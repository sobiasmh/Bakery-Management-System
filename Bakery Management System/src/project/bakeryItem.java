package project;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;








public class bakeryItem implements Serializable {
    String name;
    int quantity, id, price;
    

    public bakeryItem(String name, int quantity, int id, int price){
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    

    public static void writeObjectToFile (bakeryItem s)
    {
        ObjectOutputStream outputStream = null;

		try {
                   // Read old objects
			ArrayList<bakeryItem> bakeryList = readAllData();
			// Append new object into existing list
			bakeryList.add(s);
			// Open Stream for writing
	outputStream = new ObjectOutputStream(new FileOutputStream("BakeryItem.txt"));
			
			// Write all objects (old and new one) into the file
			for(int i = 0 ; i < bakeryList.size() ; i++) {
				outputStream.writeObject(bakeryList.get(i));
			}
		} catch(IOException e) {
			System.out.println("IO Exception while opening file");
		} finally { // cleanup code which closes output stream if its object was created
			try {
				if(outputStream != null) {
					outputStream.close();								
				}

			} catch (IOException e) {
				System.out.println("IO Exception while closing file");
			}
		}		
	}


 
   
   public static ArrayList <bakeryItem>  readAllData ()
                       {
                         //  ArrayList initialized with size 0
		ArrayList<bakeryItem> BakeryList = new ArrayList<bakeryItem>(0);
		// Input stream
		ObjectInputStream inputStream = null;
		try
		{
			// open file for reading
		inputStream = new ObjectInputStream(new FileInputStream("BakeryItem.txt"));
			// End Of File flag
			boolean EOF = false;
			// Keep reading file until file ends
			while(!EOF) {
				try {
					// read object and type cast into  object
					bakeryItem myObj = (bakeryItem) inputStream.readObject();
					// add object into ArrayList
					BakeryList.add(myObj);
				} catch (ClassNotFoundException e) {
					//System.out.println("Class not found");
				} catch (EOFException end) {
					// EOFException is raised when file ends
					// set End Of File flag to true so that loop exits
					EOF = true;
				}
                       }
               }
		 catch(FileNotFoundException e) {
			//System.out.println("Cannot find file");
		} catch (IOException e) {
			//System.out.println("IO Exception while opening stream");
			//e.printStackTrace();
		} finally { // cleanup code to close stream if it was opened
			try {
				if(inputStream != null)
					inputStream.close( );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IO Exception while closing file");
			}
		}
               
		// returns ArrayList
		return BakeryList;
	}
   
   
   
   
   public static  void updateQuantity(ArrayList<bakeryItem> s) throws IOException {
       // Code for writing data to file
       ObjectOutputStream outputStream = null;

       try {

           // Append new object into existing list
           outputStream = new ObjectOutputStream(new FileOutputStream("BakeryItem.txt"));

           // overwrite into the file
           for (int i = 0; i < s.size(); i++) {
               outputStream.writeObject(s.get(i));
           }

       } catch (IOException exp) {
           System.out.println("IO Exception while opening file");
       }

       
               if (outputStream != null) {
                   outputStream.close();

               }

          
       }

   
   
    
    

}

    

