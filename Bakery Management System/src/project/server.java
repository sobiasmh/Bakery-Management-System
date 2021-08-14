package project;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Scanner;




public class server {
		public static void main(final String args[]) throws ClassNotFoundException {
	        DatagramSocket sock = null;
			Scanner sc = new Scanner(System.in);

			
	        try {
	            // creating a server socket, parameter is local port number
	            sock = new DatagramSocket(4444);

	            // buffer to receive incoming data
	            byte[] buffer = new byte[65536];
	            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

	            // Wait for an incoming data
	            System.out.println("Server socket created. Waiting for incoming data...");
	            //String message that contain server Output
	            String msg = " \n";

	            boolean close = false;
	            while (true) { 

	               
	                sock.receive(incoming);
	                byte[] data = incoming.getData();

	                ByteArrayInputStream in = new ByteArrayInputStream(data);
	                ObjectInputStream is = new ObjectInputStream(in);
	                Object q = (Object) is.readObject();
	                
	                
	                if (q instanceof bakeryItem){
	                    //bakeryitem object received
	                	bakeryItem bakeryItem1 = (bakeryItem) q;
	                    System.out.println("bakery  item object received"+
	                            "\n<------------------------------->\n");
	                    //search by ID 
	                    
	                    if(bakeryItem1.getName().equals("abc")){
	                        ArrayList allitems = bakeryItem.readAllData();
	                        boolean present = false;
	                        bakeryItem found= (bakeryItem) allitems.get(0) ;
	                        for(int i=0; i<allitems.size(); i++){
	                        	bakeryItem bakeryItem2 = (bakeryItem) allitems.get(i);
	                            if(bakeryItem1.getId()==(bakeryItem2.getId())){
	                                found = bakeryItem2;
	                                present= true;
	                                break;
	                            }
	                        }
	                        if (present== true){
	                        	msg+=("Name: " + found.getName() +
	                                    "\nID: " + found.getId() +
	                                    "\nQuantity: " + found.getQuantity()+
	                                    "\nPrice: " + found.getPrice())+
	                                "\n<------------------------------->\n";
	                        	
	                      
	                        }
	                        else {
	                            msg+=("Bakery Item with this ID is not registered"+
	                                    "\n<------------------------------->\n");
	                            
	                        }
	                    }
	                    
	                    //update quantity
	                    else if(bakeryItem1.getName().equals("update")){
	                        ArrayList allitems = bakeryItem.readAllData();
	                        boolean present = false;
	                        bakeryItem found= (bakeryItem) allitems.get(0) ;
	                        for(int i=0; i<allitems.size(); i++){
	                        	bakeryItem bakeryItem2 = (bakeryItem) allitems.get(i);
	                            if(bakeryItem1.getId()==(bakeryItem2.getId())){
	                                found = bakeryItem2;
	                                present= true;
	                                
	                                
	                            		
	                            		int quantityAfterOrdered = bakeryItem1.quantity+found.quantity;
	                            		
	                            		
	                            		((bakeryItem) allitems.get(i)).setQuantity(quantityAfterOrdered);
	                            		
	                            		
	                            		
	                            		
	                            		bakeryItem.updateQuantity(allitems);
	                            		msg+="Quantity Added";
	                            	
	                                
	                                break;
	                            }
	                            
	                            
	                        }
	                     
	                    }
	                    else {
	                        //bakery Item object would be added to list
	                        //first we will check if it already exists or not
	                        ArrayList allitems = bakeryItem.readAllData();
	                        if (allitems.size() == 0 ){
	                        	bakeryItem1.writeObjectToFile(bakeryItem1);
	                            msg+=("Object is written to file BakeryItems.txt"+
	                                    "\n<------------------------------->\n");
	                                                    }

	                        else{
	                            boolean present = false;
	                            bakeryItem found= (bakeryItem) allitems.get(0) ;
	                            for(int i=0; i<allitems.size(); i++){
	                            	bakeryItem bakeryItem2 = (bakeryItem) allitems.get(i);
	                                if(bakeryItem1.getId()==(bakeryItem2.getId())){
	                                    found = bakeryItem2;
	                                    present= true;
	                                    break;
	                                }
	                            }
	                            if (present== true){
	                            	msg+=("Cannot register item already exists"+
	                                        "\n<------------------------------->\n");
	                            	
	                            }
	                            else {
	                            	bakeryItem1.writeObjectToFile(bakeryItem1);
	                                msg+=("Object is written to file BakeryItems.txt"+
	                                        "\n<------------------------------->\n");

	                            }
	                        }
	                    }
	                }
	                //---------------Customer-----------------------------
	                if (q instanceof Customer){
	                    //customer object received
	                    Customer customer1 = (Customer) q;
	                    System.out.println("customer object received"+
	                            "\n<------------------------------->\n");
	                    //search by ID 
	                    
	                    if(customer1.getName().equals("abc")){
	                        ArrayList allCustomer = Customer.readAllData();
	                        boolean present = false;
	                        Customer found= (Customer) allCustomer.get(0) ;
	                        for(int i=0; i<allCustomer.size(); i++){
	                            Customer customer2 = (Customer) allCustomer.get(i);
	                            if(customer1.getId()==(customer2.getId())){
	                                found = customer2;
	                                present= true;
	                                break;
	                            }
	                        }
	                        if (present== true){
	                        	msg+=("\nName: " + found.getName() +
	                                    "\nID: " + found.getId() +
	                                    "\nCity: " + found.getCity()+
	                                    "\nAge: " + found.getAge())+
	                                "\n<------------------------------->\n";
	                        	
	                      
	                        }
	                        else {
	                            msg+=("Customer with this ID not registered"+
	                                    "\n<------------------------------->\n");
	                            
	                        }
	                    }
	                    else {
	                        //customer object would be added to list
	                        //first we will check if it already exists or not
	                        ArrayList allCustomer = Customer.readAllData();
	                        if (allCustomer.size() == 0 ){
	                            customer1.writeObjectToFile(customer1);
	                            msg+=("Object is written to file Customer.txt"+
	                                    "\n<------------------------------->\n");
	                                                    }

	                        else{
	                            boolean present = false;
	                            Customer found= (Customer) allCustomer.get(0) ;
	                            for(int i=0; i<allCustomer.size(); i++){
	                                Customer customer2 = (Customer) allCustomer.get(i);
	                                if(customer1.getId()==(customer2.getId())){
	                                    found = customer2;
	                                    present= true;
	                                    break;
	                                }
	                            }
	                            if (present== true){
	                            	msg+=("Cannot register customer already exists0"+
	                                        "\n<------------------------------->\n");
	                            	
	                            }
	                            else {
	                                customer1.writeObjectToFile(customer1);
	                                msg+=("Object is written to file Customer.txt"+
	                                        "\n<------------------------------->\n");

	                            }
	                        }
	                    }
	                }
	                
	                //---------------Purchase-----------------------------
	                if (q instanceof purchase){
	                    //purchase object received
	                    purchase purchase1 = (purchase) q;
	                    System.out.println("Purchase object received"+
	                            "\n<------------------------------->\n");
	                    //search by ID 
	                      /// Check for customer if exists
	                        ArrayList allCustomer = Customer.readAllData();
	                        boolean present = false;
	                        Customer found= (Customer) allCustomer.get(0) ;
	                        for(int i=0; i<allCustomer.size(); i++){
	                            Customer customer2 = (Customer) allCustomer.get(i);
	                            if(purchase1.getCustid()==(customer2.getId())){
	                                found = customer2;
	                                present= true;
	                                break;
	                            }
	                        }
	                        if (present== true){
	                        	msg+=("Customer Exists")+
	                                "\n<------------------------------->\n";
	                        	
	                        	// Check for if item exists
	                        	
	                        	
	                            //search by ID 
	                            
	                                ArrayList allitems = bakeryItem.readAllData(); //store all old data into list
	                                boolean exist = false;
	                                bakeryItem matched= (bakeryItem) allitems.get(0) ; ///look for in the bakerylist if the id exist
	                                for(int j=0; j<allitems.size(); j++){
	                                	bakeryItem bakeryItem2 = (bakeryItem) allitems.get(j);
	                                    if(purchase1.getItemId()==(bakeryItem2.getId())){
	                                        matched = bakeryItem2; //found the item
	                                        exist= true;
	                                        ///If bakery item exist now check the quantity
	                                    	// we will have three condition:
	                                    	
	                                    	//1. If they asked for less than the total (we'll have to update the original quantity
	                                    	if(purchase1.getQuantity()<matched.getQuantity()) {
	                                    		
	                                    		int quantityAfterOrdered = matched.getQuantity() - purchase1.getQuantity();
	                                    		int totalbill =0;
	                                    		totalbill = purchase1.getQuantity()*matched.getPrice();
	                                    		((bakeryItem) allitems.get(j)).setQuantity(quantityAfterOrdered);
	                                    		
	                                    		//matched.setQuantity(quantityAfterOrdered);
	                                    		
	                                    		
	                                    		
	                                    		msg+=(
	                                    			   "\n*****BILL*****\n "+
	                                    			   "\n--------------------------------\n"+
	                                    				"Your TotalBill is: "+totalbill+
	                                    				"\n--------------------------------\n"+
	                                    				"Thanks for the Purchase           "+
	                                    				"\n--------------------------------\n");
	                                    		
	                                    		bakeryItem.updateQuantity(allitems);
	                                    //		bakeryItem.writeObjectToFile(matched);
	                                    		purchase1.writeObjectToFile(purchase1);
	                                    		
	                                    		 msg+=("Order information saved to file Purchase.txt"+
	                                             		"\n--------------------------------\n");
	                                    	}
	                                    	
	                                    	// if matched quantity is 0
	                                    	else if(matched.getQuantity()==0) {
	                                    		msg+=("SOLD OUT");
	                                    		
	                                    		
	                                            //msg+=("Order information saved to file Purchase.txt");
	                                    	}
	                                    	
	                                    	//2. If they asked for more than the quantity avilable
	                                    	else if(purchase1.getQuantity()>matched.getQuantity()) {
	                                    		msg+=("Sorry only quantity avilable is: "+matched.getQuantity());
	                                    		
	                                    		
	                                            //msg+=("Order information saved to file Purchase.txt");
	                                    	}
	                                    	// 4. if the asked for all the quantity
	                                    	//else if(purchase1.getQuantity()==matched.getQuantity()) 
	                                    	else {
	                                    		int quantityAfterOrdered = matched.getQuantity() - purchase1.getQuantity();

	                                    		int totalbill =0;
	                                    		totalbill = purchase1.getQuantity()*matched.getPrice();
	                                    		((bakeryItem) allitems.get(j)).setQuantity(quantityAfterOrdered);

	                                    		
	                                    		
	                                    		msg+=(
	                                     			   "\n*****BILL*****\n "+
	                                     			   "\n--------------------------------\n"+
	                                     				"Your TotalBill is: "+totalbill+
	                                     				"\n--------------------------------\n"+
	                                     				"Thanks for the Purchase           "+
	                                     				"\n--------------------------------\n");
	                                    		
	                                    		bakeryItem.updateQuantity(allitems);
	                                    		purchase1.writeObjectToFile(purchase1);
	                                    		
	                                            msg+=("Order information saved to file Purchase.txt"+
	                                            		"\n--------------------------------\n");
	                                    	}
	                                    	
	                                    	
	                                    	//4. If quantity is 0
	                                    	// else {
	                                    		
	                                           // msg+=(" SOLD OUT");
	                                    	// }
	                                    	
	                                        break;
	                                    }
	                                }
	                                
	                               
	                                 if(exist==false){
	                                    msg+=("Bakery Item with this ID is not registered"+
	                                            "\n<------------------------------->\n");
	                                    
	                                }
	                            
	                        	
	                      
	                        }
	                        else {
	                            msg+=("Customer with this ID not registered"+
	                                    "\n<------------------------------->\n");
	                            
	                        }
	                    }
	                    

	               
	                else if (q instanceof String) {

	                    String command = (String) q;
	                    
	                    if (command.equals("printItem")){
	                        ArrayList allitems = bakeryItem.readAllData();
	                        if(allitems.size()==0){
	                        	msg+=("no item registered" +
	                                    "\n<------------------------------->\n");
	                        }
	                        else{
	                        	msg+=("      REGISTERED ITEMS:\n");
	                            for(int i=0; i<allitems.size(); i++){
	                            	bakeryItem bakeryitem = (bakeryItem) allitems.get(i);

	                               msg+=("\nName: " + bakeryitem.getName() +
	                                        "\nID: " + bakeryitem.getId() +
	                                        "\nQuantity: " + bakeryitem.getQuantity() +
	                                        "\nPrice: " + bakeryitem.getPrice()+
	                                        "\n<------------------------------->\n");

	                                
	                            }
	                        }

	                    }
	                

	                    
	                    if (command.equals("printCustomer")){
	                        ArrayList allCustomer = Customer.readAllData();
	                        if(allCustomer.size()==0){
	                        	msg+=("no customer registered" +
	                                    "\n<------------------------------->\n");
	                        }
	                        else{
	                        	msg+=("      REGISTERED CUSTOMERS:\n");
	                            for(int i=0; i<allCustomer.size(); i++){
	                                Customer customer = (Customer) allCustomer.get(i);

	                               msg+=("\nName: " + customer.getName() +
	                                        "\nID: " + customer.getId() +
	                                        "\nCity: " + customer.getCity() +
	                                        "\nAge: " + customer.getAge()+
	                                        "\n<------------------------------->\n");

	                                
	                            }
	                        }

	                    }
	                    if (command.equals("printPurchase")){
	                        ArrayList allPurchase = purchase.readAllData();
	                        if(allPurchase.size()==0){
	                        	msg+=("no purchase registered" +
	                                    "\n<------------------------------->\n");
	                        }
	                        else{
	                        	msg+=("      REGISTERED PURCHASES:\n");
	                            for(int i=0; i<allPurchase.size(); i++){
	                                purchase purcahse1 = (purchase) allPurchase.get(i);

	                               msg+=("\nCustomer's ID: " + purcahse1.getCustid() +
	                                        "\nItem's ID: " + purcahse1.getItemId() +
	                                        "\nQuantity: " + purcahse1.getQuantity() +
	                                        "\nDate: " + purcahse1.getDop()+
	                                        "\n<------------------------------->\n");

	                                
	                            }
	                        }

	                    }
	                    if (command.equals("close")){
	                        close = true;
	                        msg += "server closed"+
	                            "\n<------------------------------->\n";

	                    }
	                }
	                //making packet and sending response to client
	                DatagramPacket dp = new DatagramPacket(msg.getBytes() , msg.getBytes().length, incoming.getAddress() , incoming.getPort());
	                msg = "";
	                sock.send(dp);
	                
	                if (close == true){
	                    sock.close();
	                    break;
	                }
	            }
	        }

	        catch (final IOException e) {
	            System.err.println("IOException " + e);
	        }
	    }
}
