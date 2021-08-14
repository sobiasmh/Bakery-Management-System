package project;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


public class client {
	public static void main(String args[]){
        DatagramSocket sock = null; //sock obj for the data transfer
        int port = 4444; //port to whick socket is to be bound

        Scanner obj = new Scanner(System.in);

        try
        {
            while (true) {
                sock = new DatagramSocket();
                //for closing socket
                boolean close = false;
                InetAddress host = InetAddress.getByName("localhost");

                //output stream for sending data
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); //array of output data in bytes
                ObjectOutputStream os = new ObjectOutputStream(outputStream);

                System.out.println("press:\n1 for Handling Bakery Items menu\n2 for Handling Customers \n3 for Buying Items \n4 for Exit");
                int option = obj.nextInt();
                
                // Bakery Items
                if (option == 1) {
                   
                    	
                    
                	System.out.println("press:\n 1 To Add Bakery Item\n 2 To View all Bakery Items"+
                            "\n 3 To View by Bakery Item's ID \n 4 To update Quantity \n 5 to exit ");
                    //taking option
                    int option2 = obj.nextInt();
                    // while (true) {
                        if (option2 == 1) {
                        	System.out.println("Enter Item's Name: ");
                            Scanner obj2Scanner = new Scanner(System.in);
                            String name = obj2Scanner.nextLine();
                            System.out.println("Enter Item's id: ");
                            int id = obj.nextInt();
                            System.out.println("Enter Item's price: ");
                            int price = obj.nextInt();
                            System.out.println("Enter Item's Quantity: ");
                            int quantity = obj.nextInt();

                            bakeryItem bakeryitem = new bakeryItem(name, quantity, id, price);
                            os.writeObject(bakeryitem);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                           // break;
                        }

                        else if (option2 == 2) {
                            String o = "printItem";
                            os.writeObject(o);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                           // break;
                        }

                        else if (option2 == 3) {
                        	System.out.println("Enter Bakery item's id: ");
                            Scanner obj2Scanner = new Scanner(System.in);
                            int id = obj2Scanner.nextInt();
                            String name = "abc";
                            int quantity = 123;
                            int price =123;
                            bakeryItem bakeryitem = new bakeryItem(name, quantity,  id, price);
                            os.writeObject(bakeryitem);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                            //break;
                        }
                        else if (option2 == 4) {
                        	System.out.println("Enter Bakery item's id: ");
                            Scanner obj2Scanner = new Scanner(System.in);
                            int id = obj2Scanner.nextInt();
                            System.out.println("Enter Quantity of the item to be add: ");
                            int quantity = obj.nextInt();
                            String name = "update";
                            int price =123;
                            bakeryItem bakeryitem = new bakeryItem(name, quantity,  id, price);
                            os.writeObject(bakeryitem);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                           // break;
                        }
                        else if (option2 == 5) {
                            String o = "close";
                            os.writeObject(o);
                            close = true;
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                           // break;
                        } else {
                        	System.out.println("invalid option selected");
                            //break;
                        }

                    }
              //  }
                
                //!--------------Customer---------------------!
                
                if(option == 2) {
                	System.out.println("press:\n 1 To Add Customer\n 2 To View all Customers" +
                            "\n 3 to View by Customer's ID \n 4 to exit\n");
                    //taking option
                    int option2 = obj.nextInt();
                  //  while (true) {
                        if (option2 == 1) {
                        	System.out.println("Enter Customer Name: ");
                            Scanner obj2Scanner = new Scanner(System.in);
                            String name = obj2Scanner.nextLine();
                            System.out.println("Enter Customer id: ");
                            int id = obj.nextInt();
                            System.out.println("Enter Customer age: ");
                            int age = obj.nextInt();
                            System.out.println("Enter Customer City: ");
                            Scanner obj3Scanner = new Scanner(System.in);
                            String city = obj3Scanner.nextLine();

                            Customer customer = new Customer(name, city, id, age);
                            os.writeObject(customer);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                            //break;
                        }

                        else if (option2 == 2) {
                            String o = "printCustomer";
                            os.writeObject(o);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                           // break;
                        }

                        else if (option2 == 3) {
                        	System.out.println("Enter CustomerID: ");
                            Scanner obj2Scanner = new Scanner(System.in);
                            int id = obj2Scanner.nextInt();
                            String name = "abc";
                            String city = "abc";
                            int age =123;
                            Customer customer = new Customer(name, city,  id, age);
                            os.writeObject(customer);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                           // break;
                        }
                        else if (option2 == 4) {
                            String o = "close";
                            os.writeObject(o);
                            close = true;
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                         //   break;
                        } else {
                        	System.out.println("invalid option selected");
                        //    break;
                        }

                    }
             //   }
                
                //----------------Purchase--------------
                
                if(option == 3) {
                	System.out.println("press:\n 1 To Purchase a Bakery Item\n 2 To View all Purchases\n" +
                            " 3 to exit\n");
                    //taking option
                    int option2 = obj.nextInt();
                  //  while (true) {
                        if (option2 == 1) {
                        	System.out.println("Enter Customer's ID: ");
                        	int custid = obj.nextInt();
                            System.out.println("Enter Item's ID: ");
                            int itemId = obj.nextInt();
                            System.out.println("Enter Quantity of the item to be purchased: ");
                            int quantity = obj.nextInt();
                            System.out.println("Enter the purachse date: ");
                            Scanner obj3Scanner = new Scanner(System.in);
                            String dop = obj3Scanner.nextLine();

                            purchase purchaseItem = new purchase(custid, itemId, quantity, dop);
                            os.writeObject(purchaseItem);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                         //   break;
                        }

                        else if (option2 == 2) {
                            String o = "printPurchase";
                            os.writeObject(o);
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                        //    break;
                        }

                        else if (option2 == 3) {
                            String o = "close";
                            os.writeObject(o);
                            close = true;
                            byte[] b = outputStream.toByteArray();
        	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
        	                sock.send(dp);
        	                System.out.println("object sent to server...");
                    //        break;
                        } else {
                        	System.out.println("invalid option selected");
                      //      break;
                        }

                    }
     //           }
                
                //---------------------------------------
                
                
                
                
                if (option == 4){
                    String o = "close";
                    os.writeObject(o);
                    byte[] b = outputStream.toByteArray();
	                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
	                sock.send(dp);
	                System.out.println("object sent to server...");
                    close = true;
                }

                
                /// buffer to receive incoming data
                byte[] buffer = new byte[65536];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                sock.receive(incoming);
                byte[] data = incoming.getData();
                //creating object for incoming data
                String msg = new String(data, 0, incoming.getLength());
                //displaying msg
               
                
                
                 
                 System.out.println("server: "+msg);
                 msg=" ";
                if (close == true) {
                	System.out.println("client socket closed");
                    sock.close();
                    break;
                }
            }
            
        
            
        }
        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
        catch (InputMismatchException e){
        	System.out.println("invalid input");
        }
    }

}
