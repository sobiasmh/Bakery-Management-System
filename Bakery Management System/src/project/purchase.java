package project;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class purchase implements Serializable {
    int custid, itemId,quantity;
    String dop;
    

    public purchase(int custid, int itemId, int quantity, String dop){
        this.custid = custid;
        this.itemId = itemId;
        this.quantity = quantity;
        this.dop = dop;
    }

    public int getCustid() {
        return custid;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getDop() {
        return dop;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setDop(String dop) {
        this.dop = dop;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static void writeObjectToFile (purchase s)
    {
        ObjectOutputStream outputStream = null;

		try {
             // Read old objects
			ArrayList<purchase> purchaseList = readAllData();
			// Append new object into existing list
			purchaseList.add(s);
			// Open Stream for writing
	outputStream = new ObjectOutputStream(new FileOutputStream("purchaseList.txt"));
			
			// Write all objects (old and new one) into the file
			for(int i = 0 ; i < purchaseList.size() ; i++) {
				outputStream.writeObject(purchaseList.get(i));
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


 
   
   public static ArrayList <purchase>  readAllData ()
                       {
                         //  ArrayList initialized with size 0
		ArrayList<purchase> purchaseList = new ArrayList<purchase>(0);
		// Input stream
		ObjectInputStream inputStream = null;
		try
		{
			// open file for reading
		inputStream = new ObjectInputStream(new FileInputStream("purchaseList.txt"));
			// End Of File flag
			boolean EOF = false;
			// Keep reading file until file ends
			while(!EOF) {
				try {
					// read object and type cast into  object
					purchase myObj = (purchase) inputStream.readObject();
					// add object into ArrayList
					purchaseList.add(myObj);
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
		return purchaseList;
	}
   
   
}
