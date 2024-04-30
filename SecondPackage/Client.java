package SecondPackage;

import ThirdPackage.Book;
import ThirdPackage.Library;
import ThirdPackage.Media;
//---------------------------------------------------------------------
// Assignment 1
// Question: Client Class
// Written by: Wissem Oumsalem (40291712) and Soukayna Haitami (40280964)
//-----------------------------------------------------------------------

//The purpose of this code is to implement the class client that will be used in the driver
public class Client {

    //Attributes :
    private String idClient="Client";
    private String nameClient;
    private String phoneNumberClient;
    private String emailClient;
    private static int numberOfClients;
    private Library [] itemsLeasedByClient;

    //Getter :
    public String getIdClient() {
        return idClient;
    }
    public String getNameClient() {
        return nameClient;
    }
    public String getPhoneNumberClient() {
        return phoneNumberClient;
    }
    public String getEmailClient() {
        return emailClient;
    }
    public int getNumberOfClients() {
        return numberOfClients;
    }
    public Library[] getItemsLeasedByClient() {return itemsLeasedByClient;}

    //Setter :
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
    public void setPhoneNumberClient(String phoneNumberClient) {
        this.phoneNumberClient = phoneNumberClient;
    }
    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }
    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setItemsLeasedByClient(Library [] items)
    {
        this.itemsLeasedByClient = new Library[items.length];
        for(int i=0;i<items.length;i++) {
            this.itemsLeasedByClient[i] = items[i];
        }
    }

    //Parametrized Constructor :
    public Client(String nameClient, String phoneNumberClient, String emailClient){
        this.nameClient=nameClient;
        this.phoneNumberClient=phoneNumberClient;
        this.emailClient=emailClient;
        numberOfClients++;
        this.idClient=idClient+numberOfClients;
        this.itemsLeasedByClient = new Library[1];
    }

    //Default Constructor :
    public Client(){
        this.nameClient="";
        this.phoneNumberClient="0000000000";
        this.emailClient="";
        numberOfClients++;
        this.idClient=idClient+numberOfClients;
        this.itemsLeasedByClient = new Library[1];
    }

    //Copy Constructor :
    public Client (Client client){
        this.nameClient=client.getNameClient();
        this.phoneNumberClient=client.getPhoneNumberClient();
        this.emailClient=client.getEmailClient();
        numberOfClients++;
        this.idClient=idClient+numberOfClients;
        this.itemsLeasedByClient = new Library[client.getItemsLeasedByClient().length];
        for(int i=0;i<this.itemsLeasedByClient.length;i++) {
            this.itemsLeasedByClient[i] = client.getItemsLeasedByClient()[i];
        }
    }

    //Method toString() :
    public String toString(){
        return "Client's ID: " + idClient +
                "\n Client's Name: " + nameClient +
                "\n Client's phone number: " + phoneNumberClient +
                "\n Client's Email: " + emailClient;
    }

    //Method equals() :
    public boolean equals(Object otherObject){
        if (otherObject==null){ //Make sure that the compared object is not empty (null)
            return false;
        }
        if(this.getClass()!=otherObject.getClass()){ //Make sure that the compared object is from the same class
            return false;
        }
        Client otherClient = (Client) otherObject;
        return (nameClient==otherClient.nameClient && phoneNumberClient==otherClient.phoneNumberClient && emailClient==otherClient.emailClient);
    }
}
