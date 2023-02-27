### Eloi Nshuti
### Southwest Technical College
### CSCI 1175 - Industry Projects

## **Synopsis**
##### This is the final project for the Industry Projects (CSCI 1175) class. The project contains code that provides a client and server window for a library to record books logged out.
##### This repository will also be used to save coursework from the class.

## **Motivation**
##### I built this program to help users such as  those that use library resources including the librarian who takes care of the institution. It will accomplish its purpose by providing a window for students to record their personal information and the books checked out which will be recorded in a server window overseen by the librarian in  an organized manner.

## **Code Example**
##### This is a code snippet of how the program connects to the server and sends information.
```
    btSubmit.setOnAction(e -> {
    	try {
    		// Get the values from the text field   		
			String clientFirstName = tfFirstName.getText().trim();
			String clientLastName = tfLastName.getText().trim();
			String clientBookName = tfBookName.getText().trim();
			String clientISBN = tfISBN.getText().trim();
			String clientEmail = tfEmail.getText().trim();
			String clientPhoneNumber = tfPhoneNumber.getText().trim();
			
			tfFirstName.clear();
			tfLastName.clear();
			tfBookName.clear();
			tfISBN.clear();
			tfEmail.clear();
			tfPhoneNumber.clear();
			
    		// Send the values to the server
    		toServer.writeUTF(clientFirstName);
    		toServer.writeUTF(clientLastName);
    		toServer.writeUTF(clientBookName);
    		toServer.writeUTF(clientISBN);
    		toServer.writeUTF(clientEmail);
    		toServer.writeUTF(clientPhoneNumber);
    		toServer.flush();
    		}
    	catch (IOException ex) {
    		System.err.println(ex);
    		}
    	});
    try {
    	// Create a socket to connect to the server
    	Socket socket = new Socket("localhost", 8000);   	
    	fromServer = new DataInputStream(socket.getInputStream());
    	toServer = new DataOutputStream(socket.getOutputStream());
    	}
    catch (IOException ex) {
    	
    	}  
```
## **Implementing**
##### To run the program, the user has to enter the informantion press the submit button. When the submission is finished, the server will record the information in a table.

![image](https://user-images.githubusercontent.com/112521045/221642013-42db219b-9b45-478e-a919-8fe807f8390e.png)
![image](https://user-images.githubusercontent.com/112521045/221642152-17cdb0fe-cf4e-4401-9cae-553f23ef9a74.png)

## **Contributors**
##### Use this program using JUnit4.
