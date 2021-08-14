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


public class Customer implements Serializable {
    String name, city;
    int id, age;
    

    public Customer(String name, String city, int id, int age){
        this.name = name;
        this.city = city;
        this.id = id;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public static void writeObjectToFile (Customer s)
    {
        ObjectOutputStream outputStream = null;

		try {
                   // Read old objects
			ArrayList<Customer> customerList = readAllData();
			// Append new object into existing list
			customerList.add(s);
			// Open Stream for writing
	outputStream = new ObjectOutputStream(new FileOutputStream("Customer.txt"));
			
			// Write all objects (old and new one) into the file
			for(int i = 0 ; i < customerList.size() ; i++) {
				outputStream.writeObject(customerList.get(i));
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


 
   
   public static ArrayList <Customer>  readAllData ()
                       {
                         //  ArrayList initialized with size 0
		ArrayList<Customer> customerList = new ArrayList<Customer>(0);
		// Input stream
		ObjectInputStream inputStream = null;
		try
		{
			// open file for reading
		inputStream = new ObjectInputStream(new FileInputStream("Customer.txt"));
			// End Of File flag
			boolean EOF = false;
			// Keep reading file until file ends
			while(!EOF) {
				try {
					// read object and type cast into  object
					Customer myObj = (Customer) inputStream.readObject();
					// add object into ArrayList
					customerList.add(myObj);
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
		return customerList;
	}
   
   
}
