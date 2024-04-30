package FirstPackage;
import SecondPackage.Client;
import ThirdPackage.Library;
import ThirdPackage.Book;
import ThirdPackage.Journal;
import ThirdPackage.Media;
import java.util.Scanner;
//---------------------------------------------------------------------
// Assignment 1
// Question: Driver
// Written by: Wissem Oumsalem (40291712) and Soukayna Haitami (40280964)
//-----------------------------------------------------------------------

//The purpose of this code is to implement the virtual library using the client,library,book,media and journal classes
//this program manages all the items of the Library and the clients. Only the employees can use it. This program keeps
//track of the leased items, the available items and the list of items the clients have each leased. It can also perform operations
//in the library like adding new items, deleting them and returning and leasing them to clients.
public class Driver {
    //------------------------------------------------------------------------------------------------
    //ATTRIBUTES :
    public static Library[] allItemsArray;
    public static Book[] allBooksArray;
    public static Journal[] allJournalArray;
    public static Media[] allMediaArray;
    public static Client[] allClientsArray;
    //------------------------------------------------------------------------------------------------
    //ARRAYS

    //Initialize the arrays :
    static void createAllItemsArray() {allItemsArray = new Library[1];}
    static void createAllBooksArray() {allBooksArray = new Book[1];}
    static void createAllJournalArray() {allJournalArray = new Journal[1];}
    static void createAllMediaArray() {allMediaArray = new Media[1];}
    static void createAllClientsArray() {allClientsArray = new Client[1];}

    //Display an array (Book, Media, journal)
    static void displayArray(Library[]item) {
        for (int i=0;i<item.length;i++)
        {
            System.out.println(item[i]);
        }
    }
    //------------------------------------------------------------------------------------------------
    //VERIFICATIONS

    //Verify that the user's input is an integer:
    static int validIntegerInput(Scanner input){
        int integerInput=0;
        boolean valid = false;
        do{
            if(input.hasNextInt()){
                integerInput = input.nextInt();
                valid=true;
            }else {
                System.out.print("Invalid value. Please enter an integer:");
                input.next();
            }
        }while (!valid);
        return integerInput;
    }

    //Method to take the user's desired client position and verify its validity :
    static int takeNumberOfTheClient(Scanner input,String message)
    {
        int clientPositionToLeaseTo = 0;
        do {
            displayAllClients();
            System.out.println("Enter the number (#) of the client you would like to "+message+":");
            clientPositionToLeaseTo = validIntegerInput(input);
            if (clientPositionToLeaseTo < 0 || clientPositionToLeaseTo > allClientsArray.length) {
                System.out.println("Error, try again. Please enter a number between 0" + " and " + (allClientsArray.length - 1));

            }
        }while (clientPositionToLeaseTo < 0 || clientPositionToLeaseTo > allClientsArray.length) ;
        return clientPositionToLeaseTo;
    }

    //Method to validate the type of item entered by the user :
    static String takeValidChoiceOfTypeOfItem(Scanner input)
    {
        String choice = input.next();
        while (choice.equals("book")==false&&choice.equals("journal")==false&&choice.equals("media")==false)
        {
            System.out.print("This is not a valid type please choose between book, journal or media >> ");
            choice = input.next();
        }
        return choice;
    }

    //Method to take the user's desired item to lease position and verify its validity :
    static int takeNumberOfItemToLease(Scanner input)
    {
        int itemPositionToLease=0;
        do {
            System.out.println("Here are all the items available: ");
            listAllItems();
            System.out.println("Enter the number (#) of the item you would like to lease : ");
            itemPositionToLease = validIntegerInput(input);
            if (itemPositionToLease < 0 || itemPositionToLease > allItemsArray.length-1) {
                System.out.println("Error, try again. Please enter a number between 0" + " and " + (allItemsArray.length - 1));
            }
        } while (itemPositionToLease < 0 || itemPositionToLease > allItemsArray.length-1);
        return itemPositionToLease;
    }
    //------------------------------------------------------------------------------------------------
    //MENU

    //Method to ask the user if he wants the menu or a predefined Scenario :
    static int menuOrPredefinedScenario(Scanner input)
    {
        System.out.print("Hello welcome to Soukayna Haitami's and Wissem Oumsalem's FunReadings Library what would you like to do ?\n1-Display the menu\n2-Run a predefined scenario"
                +"\nPlease enter your choice (1 or 2) >>");
        int choice = validIntegerInput(input);
        while(choice!=1&&choice!=2)
        {
            System.out.print("\nThis is not a valid input please enter a valid choice >> ");
            choice = validIntegerInput(input);
        }
        return choice;
    }
    //Method to display main menu (Choice 1):
    static void displayMenu() {
        System.out.print("| What would you like to do?\n"
                + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"
                + "| 1 >> Add an item \n"
                + "| 2 >> Delete an item\n"
                + "| 3 >> Change information of an item \n"
                + "| 4 >> List all items in a specific category\n"
                +"| 5 >> List all items	\n"
                + "| 6 >> Add a client\n"
                +"| 7 >> Remove a client \n"
                + "| 8 >> Edit a client \n"
                +"| 9 >> Lease an item to a client  \n"
                + "|10 >> Return an item from a client \n"
                + "|11 >> Show all items leased by a client \n"
                + "|12 >> Show all leased items (by all clients)\n"
                + "|13 >> Display the biggest book \n"
                + "|14 >> Make a copy of the books array\n"
                + "|0 >> Quit\n"
                + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"
                + "\n Please enter your choice and press <Enter>: ");
    }
    //Method to collect the user's choice of action listed in the main menu :
    static int getChoiceMenu(Scanner input)
    {
        displayMenu();
        int choice =0;
        choice = validateChoiceMenu(choice,input);
        return choice;
    }
    //Method to make sure that the user's input for the menu's choice is valid in the range 0-14 :
    static int validateChoiceMenu(int choice,Scanner input)
    {
        choice=validIntegerInput(input);
        while (choice>14||choice<0)
        {
            System.out.print("This is not a valid input please try again >>");
            choice = validIntegerInput(input);
        }
        return choice;
    }
    //------------------------------------------------------------------------------------------------
    //MENU'S OPTIONS :
    //------------------------------------------------------------------------------------------------
    //OPTION 1 : 1- Add an item

    //Method to add an item :
    static void addItem(Library item){
            if (allItemsArray[allItemsArray.length-1] == null) {
                allItemsArray[allItemsArray.length-1] = item;
            }
            else
            {
                Library[] tempArray = new Library[allItemsArray.length + 1];
                for (int i = 0; i < allItemsArray.length; i++) {
                    tempArray[i] = allItemsArray[i];
                }
                tempArray[tempArray.length - 1] = item;
                allItemsArray = tempArray;
            }
    }

    //Method to ask what type of item the user would like to add
    static Library askTheUserForTheItemHeWantsToAdd(Scanner input)
    {
        System.out.print("What type of item do you want to add (book, journal or media) >>");
        String choice=takeValidChoiceOfTypeOfItem(input);
        Library item =null;
        String title;
        String author;
        int yearOfPublication;
        switch (choice)
        {
            case "book":{
                System.out.print("\nEnter the book information in the following order: Number of pages, title, author, year of publication >> ");
                int nbOfPages=validIntegerInput(input);
                String trash = input.nextLine();
                title= input.nextLine();
                author=input.nextLine();
                yearOfPublication=validIntegerInput(input);
                item = new Book(nbOfPages,title,author,yearOfPublication);
                addBookToArray((Book) item);
                listAllBooks();
                break;
            }
            case "journal":
            {
                System.out.print("\nEnter the Journal information in the following order: Volume number, title, author, year of publication >> ");
                int volumeNumber=validIntegerInput(input);
                String trash = input.nextLine();
                title= input.nextLine();
                author=input.nextLine();
                yearOfPublication=validIntegerInput(input);
                item = new Journal(volumeNumber,title,author,yearOfPublication);
                addJournalToArray((Journal) item);
                break;
            }
            case "media":{System.out.print("\nEnter the Media information in the following order: Type of media, title, author, year of publication >> ");
                String trash = input.nextLine();
                String typeMedia=input.nextLine();
                title= input.nextLine();
                author=input.nextLine();
                yearOfPublication=validIntegerInput(input);
                item = new Media(typeMedia,title,author,yearOfPublication);
                addMediaToArray((Media) item);
                break;
            }
        }
        return item;
    }
    //Method to add a Book to the Book Array addBookToArray()
    static void addBookToArray(Book item){
        if (allBooksArray[allBooksArray.length-1] == null) {
            allBooksArray[allBooksArray.length-1] = item;
        }
        else
        {
            Book [] tempArray = new Book[allBooksArray.length + 1];
            for (int i = 0; i < allBooksArray.length; i++) {
                tempArray[i] = allBooksArray[i];
            }
            tempArray[tempArray.length - 1] = item;
            allBooksArray = tempArray;
        }
    }

    //Method to add a Journal to the Journal Array addJournalToArray() :
    static void addJournalToArray(Journal item){
        if (allJournalArray[allJournalArray.length-1] == null) {
            allJournalArray[allJournalArray.length-1] = item;
        }
        else
        {
            Journal [] tempArray = new Journal[allJournalArray.length + 1];
            for (int i = 0; i < allJournalArray.length; i++) {
                tempArray[i] = allJournalArray[i];
            }
            tempArray[tempArray.length - 1] = item;
            allJournalArray = tempArray;
        }
    }

    //Method to add a Media to the Media Array addMediaToArray() :
    static void addMediaToArray(Media item){
        int index =0;
        if (allMediaArray[allMediaArray.length-1] == null) {
            allMediaArray[allMediaArray.length-1] = item;
        }
        else
        {
            Media [] tempArray = new Media[allMediaArray.length+1];
            for (int i = 0; i < allMediaArray.length; i++) {
                tempArray[i] = allMediaArray[i];
            }
            tempArray[tempArray.length - 1] = item;
            allMediaArray = tempArray;
        }
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 2 : 2- Delete an item

    //Method to delete an item
    static void deleteItem(Scanner input) {
        if(allItemsArray[allItemsArray.length-1]==null)
        {
            System.out.println("There are no items");
        }
        else {
            listAllItems();
            System.out.print("What is the number of the one you want to delete enter here >>");
            int index = validIntegerInput(input);
            deleteItemFromAllArrays(index);
        }
    }

    //Method to delete from the allItemsArray and the specific item's type array :
    static void deleteItemFromAllArrays(int index)
    {
        if(allItemsArray[index].getClass().equals(Book.class))
        {
            deleteBook(index);
        }
        else if(allItemsArray[index].getClass().equals(Journal.class))
        {
            deleteJournal(index);
        }
        else {
            deleteMedia(index);
        }
        if(index>=0&&index<allItemsArray.length)
        {
            for (int j = index; j < allItemsArray.length - 1; j++) {
                allItemsArray[j] = allItemsArray[j + 1];
            }
            Library[] tempArray = new Library[allItemsArray.length - 1];
            for (int n = 0; n < tempArray.length; n++) {
                tempArray[n] = allItemsArray[n];
            }
            allItemsArray = tempArray;
        }
        else {
            System.out.println("This is not a valid input");
        }
    }

    //Method to delete a Book :
    static void deleteBook(int index) {
        int indexBook=0;
        for (int i = 0;i<allBooksArray.length;i++)
        {
            if (allBooksArray[i].equals(allItemsArray[index]))
            {
               indexBook=i;
            }
        }
        for (int j = indexBook; j < allBooksArray.length - 1; j++) {
            allBooksArray[j] = allBooksArray[j + 1];
        }
        Book[] tempArray = new Book[allBooksArray.length-1];
        for (int n = 0; n < tempArray.length; n++) {
            tempArray[n] = allBooksArray[n];
        }
        allBooksArray = tempArray;
        listAllBooks();
    }

    //Method to delete a Journal :
    static void deleteJournal(int index)
    {
        int indexBook=0;

        for (int i = 0;i<allJournalArray.length;i++)
        {
            if (allJournalArray[i].equals(allItemsArray[index]))
            {
                indexBook=i;
            }
        }
        for (int j = indexBook; j < allJournalArray.length - 1; j++) {
            allJournalArray[j] = allJournalArray[j + 1];
        }
        Journal[] tempArray = new Journal[allJournalArray.length-1];
        for (int n = 0; n < tempArray.length; n++) {
            tempArray[n] = allJournalArray[n];
        }
        allJournalArray = tempArray;
        listAllJournals();
    }

    //Method to delete a Media :
    static void deleteMedia(int index)
    {
        int indexBook=0;

        for (int i = 0;i<allMediaArray.length;i++)
        {
            if (allMediaArray[i].equals(allItemsArray[index]))
            {
                indexBook=i;
            }
        }
        for (int j = indexBook; j < allMediaArray.length - 1; j++) {
            allMediaArray[j] = allMediaArray[j + 1];
        }
        Media[] tempArray = new Media[allMediaArray.length-1];
        for (int n = 0; n < tempArray.length; n++) {
            tempArray[n] = allMediaArray[n];
        }
        allMediaArray = tempArray;
        listAllMedia();
    }

    //------------------------------------------------------------------------------------------------
    //CHOICE 3 : 3- Change information of an item

    //Method to change information of an item :
    static void changeInformationItem(Scanner input)
    {
        listAllItems();
        System.out.print("Please enter the number (#) of the item you want to edit >>  ");
        int index= validIntegerInput(input);

        int choice =0;
        while (index<0||index>=allItemsArray.length) {
            System.out.println();
            System.out.print("This item doesn't exist please enter a valid number >>  ");
            index= validIntegerInput(input);
        }
        System.out.println();
        System.out.println("Here is the item's information: "+allItemsArray[index]);

        //IF THE ITEM IS A BOOK
        if(allItemsArray[index].getClass().equals(Book.class))
        {
            System.out.println();
            System.out.print("Which attribute do you want to edit ?\n1 >> Title\n2 >> Author\n3 >> Year of publication" +
                    "\n4 >> Number of pages\nPlease enter your choice >> ");
            choice=validIntegerInput(input);
            while (choice<1||choice>4)
            {
                System.out.println();
                System.out.println("This is not a valid choice please try again >> ");
                choice = validIntegerInput(input);
            }
            Book book = (Book) allItemsArray[index];
            int indexOfBookInBookArray = 0;
            for (int i =0;i<allBooksArray.length;i++)
            {
                if (allBooksArray[i].equals(allItemsArray[index]))
                {
                    indexOfBookInBookArray=i;
                }
            }
            switch(choice)
            {
                case 1:
                {
                    System.out.print("Please enter the new title of the book >> ");
                    String title = input.next();
                    book.setName(title);
                    break;
                }
                case 2:
                {
                    System.out.print("Please enter the new Author of the book >> ");
                    String author = input.next();
                    book.setAuthor(author);
                    break;
                }
                case 3:
                {
                    System.out.print("Please enter the new year of publication of the book >> ");
                    int year = validIntegerInput(input);
                    book.setYearPublication(year);
                    break;
                }
                case 4:
                {
                    System.out.print("Please enter the new number of pages of the book >> ");
                    int number = validIntegerInput(input);
                    book.setNumberPages(number);
                    break;
                }
            }
            allItemsArray[index] = book;
            allBooksArray[indexOfBookInBookArray]=(Book)allItemsArray[index];

        }
        //IF THE ITEM IS A JOURNAL
        else if(allItemsArray[index].getClass().equals(Journal.class))
        {
            System.out.println();
            System.out.print("Which attribute do you want to edit ?\n1 >> Title\n2 >> Author\n3 >> Year of publication" +
                    "\n4 >> Volume number\nPlease enter your choice >> ");
            choice= validIntegerInput(input);
            while (choice<1||choice>4)
            {
                System.out.println();
                System.out.println("This is not a valid choice please try again >> ");
                choice = validIntegerInput(input);
            }
            Journal journal = (Journal) allItemsArray[index];
            int indexOfJournalInJournalArray = 0;
            for (int i =0;i<allJournalArray.length;i++)
            {
                if (allJournalArray[i].equals(allItemsArray[index]))
                {
                    indexOfJournalInJournalArray=i;
                }
            }
            switch(choice)
            {
                case 1:
                {
                    System.out.print("Please enter the new title of the journal >> ");
                    String title = input.next();
                    journal.setName(title);
                    break;
                }
                case 2:
                {
                    System.out.print("Please enter the new Author of the journal >> ");
                    String author = input.next();
                    journal.setAuthor(author);
                    break;
                }
                case 3:
                {
                    System.out.print("Please enter the new year of publication of the journal >> ");
                    int year = validIntegerInput(input);
                    journal.setYearPublication(year);
                    break;
                }
                case 4:
                {
                    System.out.print("Please enter the new number of pages of the journal >> ");
                    int number = validIntegerInput(input);
                    journal.setVolumeNumber(number);
                    break;
                }
            }
            allItemsArray[index] = journal;
            allJournalArray[indexOfJournalInJournalArray]=(Journal)allItemsArray[index];

        }
        //IF THE ITEM IS A MEDIA
        else
        {
            System.out.println();
            System.out.print("Which attribute do you want to edit ?\n1 >> Title\n2 >> Author\n3 >> Year of publication" +
                    "\n4 >> Type of media\nPlease enter your choice >> ");
            choice= validIntegerInput(input);
            while (choice<1||choice>4)
            {
                System.out.println();
                System.out.println("This is not a valid choice please try again >> ");
                choice = validIntegerInput(input);
            }
            Media media = (Media) allItemsArray[index];
            int indexOfMediaInMediaArray = 0;
            for (int i =0;i<allMediaArray.length;i++)
            {
                if (allMediaArray[i].equals(allItemsArray[index]))
                {
                    indexOfMediaInMediaArray=i;
                }
            }
            switch(choice)
            {
                case 1:
                {
                    System.out.print("Please enter the new title of the media >> ");
                    String title = input.next();
                    media.setName(title);
                    break;
                }
                case 2:
                {
                    System.out.print("Please enter the new Author of the media >> ");
                    String author = input.next();
                    media.setAuthor(author);
                    break;
                }
                case 3:
                {
                    System.out.print("Please enter the new year of publication of the media >> ");
                    int year = validIntegerInput(input);
                    media.setYearPublication(year);
                    break;
                }
                case 4:
                {
                    System.out.print("Please enter the new number of pages of the book >> ");
                    String type = input.next();
                    media.setTypeMedia(type);
                    break;
                }
            }
            allItemsArray[index] = media;
            allMediaArray[indexOfMediaInMediaArray]=(Media)allItemsArray[index];
        }
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 4 : 4- List all items in a specific category

    //Methods to list a specific category :
    static void listSpecificCategory(Scanner input) {
        System.out.print("Enter the category of document you would like to display (\"Book\", \"Journal\", \"Media\"):");
        String category = input.next();
        if (category.equalsIgnoreCase("Book")){
            if (allBooksArray[allBooksArray.length-1]==null) {
                System.out.println("Sorry there's no Book for now.");
            } else{
                listAllBooks();
            }

        }else if (category.equalsIgnoreCase("Journal")) {
            if (allJournalArray[allJournalArray.length-1]==null) {
                System.out.println("Sorry there's no Journal for now.");
            } else{
                listAllJournals();
            }
        }else if (category.equalsIgnoreCase("Media")) {
            if (allMediaArray[allMediaArray.length-1]==null) {
                System.out.println("Sorry there's no media for now.");
            } else{
                listAllMedia();
            }
        }
    }

    //Method to list all the books of the book array :
    static void listAllBooks(){
        if(allItemsArray!=null) {
            System.out.println("Here's all the Books:");
            for (int i = 0; i < allBooksArray.length; i++) {
                System.out.print("book #"+i+"\n"+allBooksArray[i] + "\n");
            }
        }else{
            System.out.println("There's no book yet.");
        }
    }

    //Method to list all the journals of the journal array :
    static void listAllJournals(){
        if(allItemsArray!=null) {
            System.out.println("Here's all the Journals:");
            for (int i=0; i< allJournalArray.length;i++){
                System.out.print("journal #"+i+"\n"+allJournalArray[i] + "\n");
            }
        }else{
            System.out.println("There's no journal yet.");
        }
    }

    //Method to list all the medias of the media array:
    static void listAllMedia(){
        if(allItemsArray!=null) {
            System.out.println("Here's all the Medias:");
            for (int i=0; i< allMediaArray.length;i++) {
                System.out.print("media #"+i+"\n"+allMediaArray[i] + "\n");
            }
        }else{
            System.out.println("There's no media yet.");
        }
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 5 : 5- List all items

    //Method to list all items of the array library :
    static void listAllItems() {
        if(allItemsArray[allItemsArray.length-1]==null) {
            System.out.println("There's no item yet.");
        }else{
            System.out.println("Here are all the items: ");
            for (int i = 0; i < allItemsArray.length; i++) {
                System.out.println("\nitem#" + i + "\n" + allItemsArray[i]);
            }
        }
    }

    //------------------------------------------------------------------------------------------------
    //CHOICE 6 : 6- Add a client

    //Method to add a client :
    static void addClient(Scanner input){
        System.out.print("Enter the client information in the following order: Client name, Phone-number, E-mail >> ");
        String trash = input.nextLine();
        String clientName = input.nextLine();
        String clientPhoneNumber = input.next();
        String clientEmail = input.next();
        Client newClient= new Client(clientName,clientPhoneNumber,clientEmail);
        if (allClientsArray[allClientsArray.length-1] == null) {
            allClientsArray[allClientsArray.length-1] = newClient;
        }
        else {
            //Copy the array and extend it of 1 place to add the client
            Client[] tempAllClientsArray = new Client[allClientsArray.length + 1];
            for (int i = 0; i < allClientsArray.length; i++) {
                tempAllClientsArray[i] = allClientsArray[i];
            }
            tempAllClientsArray[tempAllClientsArray.length-1] = newClient;
            allClientsArray = tempAllClientsArray;
        }
        System.out.println("Your client was successfully added.");
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 7 : 7- Remove a client

    //Method to remove a client :
    static void deleteClient(Scanner input){
        if(allClientsArray[allClientsArray.length-1] !=null){
            int deleteClientPosition=takeNumberOfTheClient(input,"remove");
            Client[] tempAllClientsArray = new Client[allClientsArray.length-1];
            for (int i =0; i<allClientsArray.length-1;i++){
                if(i<deleteClientPosition){
                    tempAllClientsArray[i]=allClientsArray[i];
                }else{
                    tempAllClientsArray[i]=allClientsArray[i+1];
                }
            }
            allClientsArray=tempAllClientsArray;
            displayAllClients();
        }else{
            System.out.println("There's no client yet.");
        }
    }

    //Method display all the clients :
    static void displayAllClients(){
        System.out.println("Here's all your clients :");
        for (int i=0; i<allClientsArray.length; i++){
            System.out.println("Client#"+i+"\n"+allClientsArray[i]+ "\n");
        }
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 8 : 8- Edit a client

    //Method to edit a client editClient()
    static void editClient(Scanner input){
        if(allClientsArray[allClientsArray.length-1]==null){
            System.out.println("There's no client yet.");
        }else {
            int positionClient=takeNumberOfTheClient(input,"edit");

            System.out.print("What do you want to modify : \"Name\", \"Phone Number\" or \"Email\"? : ");
            String clientModification = input.next();
            if(clientModification.equalsIgnoreCase("Name")){
                System.out.print("Enter a new name: ");
                String newName = input.next();
                allClientsArray[positionClient].setNameClient(newName);
                System.out.println("This client's name was successfully modified");
                displayClientInfo(positionClient);
            } else if (clientModification.equalsIgnoreCase("Phone number")) {
                System.out.print("Enter a new phone number: ");
                String newPhoneNumber = input.next();
                allClientsArray[positionClient].setPhoneNumberClient(newPhoneNumber);
                System.out.println("This client's phone number was successfully modified");
                displayClientInfo(positionClient);
            } else if (clientModification.equalsIgnoreCase("Email")) {
                System.out.print("Enter a new name: ");
                String newEmail = input.next();
                allClientsArray[positionClient].setEmailClient(newEmail);
                System.out.println("This client's e-mail was successfully modified");
                displayClientInfo(positionClient);
            }
        }
    }

    //Method to display a specific client's info :
    static void displayClientInfo(int i){
        System.out.println("Here are the information of client #" + i + " :");
        System.out.println(allClientsArray[i]);
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 9 : 9- Lease an item to a client

    //Method to lease an item to client :
    static void leaseItemToClient(Scanner input) {
        int clientPositionToLeaseTo;
        if (allItemsArray[allItemsArray.length-1] != null && allClientsArray[allClientsArray.length-1] != null) {

            clientPositionToLeaseTo=takeNumberOfTheClient(input,"lease an item to");

            int itemPositionToLease;
           itemPositionToLease=takeNumberOfItemToLease(input);

            Library[] leasedItemByClient = allClientsArray[clientPositionToLeaseTo].getItemsLeasedByClient();
            if(leasedItemByClient[leasedItemByClient.length-1]==null)
            {
                leasedItemByClient[leasedItemByClient.length-1]=allItemsArray[itemPositionToLease];
                deleteItemFromAllArrays(itemPositionToLease);
                System.out.println("The item #"+itemPositionToLease+" has been leased successfully to client #"+clientPositionToLeaseTo);
            }
            else {
                Library[] newLeasedItemByClient = new Library[leasedItemByClient.length + 1];
                for (int i = 0; i < leasedItemByClient.length; i++) {
                    newLeasedItemByClient[i] = leasedItemByClient[i];
                }
                newLeasedItemByClient[newLeasedItemByClient.length - 1] = allItemsArray[itemPositionToLease];
                leasedItemByClient = newLeasedItemByClient;
                allClientsArray[clientPositionToLeaseTo].setItemsLeasedByClient(leasedItemByClient);
                deleteItemFromAllArrays(itemPositionToLease);
                System.out.println("The item #"+itemPositionToLease+" has been leased successfully to client #"+clientPositionToLeaseTo);
            }
        }else{
            if(allClientsArray[allClientsArray.length-1] == null) {
                System.out.println("There's no client yet.");
            }else if(allItemsArray[allItemsArray.length-1] == null){
                System.out.println("There's no item yet.");
            }
        }
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 10 : 10- Return an item from a client

    //Method to return an item of a client :
    static void returnItemFromClient(Scanner input)
    {
        if(allClientsArray[allClientsArray.length-1]==null){
            System.out.println("There's not client");
        }else{
            int clientPosition = displayLeasedItemOfClient(input,"return an item from");
            Library[] leasedItemsOfClient = allClientsArray[clientPosition].getItemsLeasedByClient();


            System.out.print("Which number of item (#) does the client wish to return:");
            int itemPosition = 0;
            do {
                itemPosition = validIntegerInput(input);
                if (itemPosition < 0 || itemPosition > leasedItemsOfClient.length - 1) {
                    System.out.print("\nSorry this is not a valid input please try again: ");
                }
            } while (itemPosition < 0 || itemPosition > leasedItemsOfClient.length - 1);

            addItem(leasedItemsOfClient[itemPosition]);//adds the item to allItemsArray

            //adding the item to his respective array
            if(leasedItemsOfClient[itemPosition].getClass().equals(Book.class))
            {
                addBookToArray((Book)leasedItemsOfClient[itemPosition]);
            }
            else if (leasedItemsOfClient[itemPosition].getClass().equals(Journal.class))
            {
                addJournalToArray((Journal) leasedItemsOfClient[itemPosition]);
            }
            else
            {
                addMediaToArray((Media)leasedItemsOfClient[itemPosition]);
            }

            //delete the item from the leased items of the client
            for (int i=itemPosition;i<leasedItemsOfClient.length-1;i++)
            {
                leasedItemsOfClient[i]=leasedItemsOfClient[i+1];
            }
            Library[] tempArray = new Library[leasedItemsOfClient.length-1];
            for (int i=0;i<tempArray.length;i++)
            {
                tempArray[i]=leasedItemsOfClient[i];
            }
            allClientsArray[clientPosition].setItemsLeasedByClient(tempArray);
        }
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 11 : 11- Show all items leased by a client

    //Method to display the leased items by a client :
    static int displayLeasedItemOfClient(Scanner input,String message){
        int clientPosition=0;
        if(allClientsArray[allClientsArray.length-1]==null)
        {
            System.out.println("There are no clients");
        }
        else {
            clientPosition = takeNumberOfTheClient(input, message);
            Library[] leasedItemByClient = allClientsArray[clientPosition].getItemsLeasedByClient();
            if (leasedItemByClient[leasedItemByClient.length - 1] != null) {
                for (int i = 0; i < leasedItemByClient.length; i++) {
                    System.out.println("Item#" + i + "\n" + leasedItemByClient[i] + "\n");
                }
            } else {
                System.out.println("Sorry there are no items leased");
            }
        }
        return clientPosition;
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 12 : 12- Show all leased items (by all clients)

    //Method to display the leased items of all the clients :
    static void displayLeasedItemsOfAllClients(){
        if(allClientsArray[allClientsArray.length-1]==null)
        {
            System.out.println("There are no clients");
        }
        else {
            for (int j = 0; j < allClientsArray.length; j++) {
                Library[] leasedItemByClient = allClientsArray[j].getItemsLeasedByClient();
                System.out.println("Client#" + j);
                for (int i = 0; i < leasedItemByClient.length; i++) {
                    System.out.println(leasedItemByClient[i] + "\n");
                }
                System.out.println("\n");
            }
        }
    }
    //------------------------------------------------------------------------------------------------
    //CHOICE 13 : 13- Display the biggest book

    //Display the biggest book getBiggestBook() :
    static void getBiggestBook(Library [] items) {
        if(items[items.length-1]==null)
        {
            System.out.println("This array is empty");
        }
        else {
            if (items.getClass().equals(Book[].class)) {

                Book []copyArray = copyBook(items);

                Book biggestBook = (Book) items[0];

                int biggestBookPosition = 0;
                for (int i = 1; i < copyArray.length; i++) {
                    if (copyArray[i].getNumberPages() > biggestBook.getNumberPages()) {
                        biggestBook = copyArray[i];
                        biggestBookPosition = i;
                    }
                }
                System.out.println("The biggest book is the Book #" + biggestBookPosition);
                System.out.println(copyArray[biggestBookPosition]);

            }
            else {
                System.out.println("This is not an array of books");
            }
        }
    }

    //------------------------------------------------------------------------------------------------
    //CHOICE 14 : 14- Make a copy of the books array

    //Method to make a copy of the books array :
    static Book [] copyBook(Library []items){
        Book[] copy = new Book[items.length];
        if(items[items.length-1]==null)
        {
            System.out.println("This array is empty");
        }
        else {
            if (items.getClass().equals(copy.getClass())) {
                for (int i = 0; i < copy.length; i++) {
                    copy[i] = (Book) items[i];
                }
                System.out.println("This array has been copied successfully");
            } else {
                System.out.println("This is not an array of books");
            }
        }
        return copy;
    }
    //------------------------------------------------------------------------------------------------
    //MAIN
    //------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int choiceMenuOrScenario =menuOrPredefinedScenario(input);

        if(choiceMenuOrScenario==2) {
            //Create 3 objects from each type
            Book book = new Book(350,"Harry Potter","JK Rowling",2001);
            Journal journal = new Journal(25,"The New York Times","Soukay",2024);
            Media media = new Media("Movie","Interstellar","Christopher Nolan",2012);
            Media media1 = new Media("Movie","Interstellar","Christopher Nolan",2012);

            Library[] items = {book,journal,media};


            //Display all items
            System.out.println("Here are the 3 items created:");
            System.out.println(book);
            System.out.println(journal);
            System.out.println(media);

            //Create 3 users
            Client client1 = new Client("Soukayna","514550287","haitamis@hotmail.com");
            Client client2 = new Client("Wissem","51489887","wissem@hotmail.com");
            Client client3 = new Client("Tasneem","4393070000","haitamit@hotmail.com");


            //Display the user's information
            System.out.println();
            System.out.println("Here are the 3 users created:");
            System.out.println();
            System.out.println(client1);
            System.out.println();
            System.out.println(client2);
            System.out.println();
            System.out.println(client3);
            System.out.println();

            //Test equals() of 2 objects of different classes
            System.out.println("book=journal? \nTest equals() of 2 objects of different classes");
            System.out.println(book.equals(journal));
            System.out.println();

            //Test equals() of 2 objects of same class with different values
            System.out.println("book 1 = book 2? \nTest equals() of 2 objects of same class with different values");
            Book book2 = new Book(450,"After","Anna",2001);
            System.out.println(book.equals(book2));
            System.out.println();

            //Test equals() of 2 objects of same class with same values
            System.out.println("journal 1 = journal 2? \nTest equals() of 2 objects of same class with same values ");
            Journal journal2 = new Journal(25,"The New York Times","Soukay",2024);
            System.out.println(journal.equals(journal2));
            System.out.println();

            //Create an array for Book
            Book [] books = {book,book2,new Book(165,"aa","bb",2024),new Book(75,"c","d",2024)};

            //Create an array for Journal
            Journal [] journals = {journal, new Journal()};

            //Create an array for Media
            Media[] medias = {media,new Media()};

            //Create an array for all the items(Book, Journal and Media)
            Library[] allItems = {book,journal,media};

            System.out.println("This is the array of type Library:");
            displayArray(allItems);
            System.out.println();

            //call function getBiggestBook
            System.out.println("Here we call the function getBiggestBook() on this array of books:");
            displayArray(books);
            System.out.println();
            getBiggestBook(books);
            System.out.println();

            //callfunction copyBooks on a media
            System.out.println("Here we call the function copyBooks() on this array of media:");
            displayArray(medias);
            System.out.println();
            System.out.println("The function is executed here is the output: ");
            copyBook(medias);

        }
        else {
            int menuChoice=0;
            createAllItemsArray();
            createAllClientsArray();
            createAllBooksArray();
            createAllJournalArray();
            createAllMediaArray();
            do {
                menuChoice = getChoiceMenu(input);
                switch(menuChoice)
                {
                    case 1: {
                        Library item = askTheUserForTheItemHeWantsToAdd(input);
                        addItem(item);
                        listAllItems();
                        break;
                    }
                    case 2: {

                        deleteItem(input);
                        listAllItems();
                        break;
                    }

                    case 3: {
                        changeInformationItem(input);
                        break;
                    }

                    case 4: {
                        listSpecificCategory(input);
                        break;

                    }
                    case 5: {
                        listAllItems();
                        break;
                    }
                    case 6: {
                        addClient(input);
                        displayAllClients();
                        break;
                    }

                    case 7: {
                        deleteClient(input);
                        break;
                    }

                    case 8:
                    {
                        editClient(input);
                        break;
                    }

                    case 9:
                    {
                        leaseItemToClient(input);
                        break;
                    }
                    case 10:
                    {
                        returnItemFromClient(input);
                        break;
                    }
                    case 11:
                    {
                        displayLeasedItemOfClient(input,"see his leased items");
                        break;
                    }
                    case 12:
                    {
                        displayLeasedItemsOfAllClients();
                        break;
                    }
                    case 13:
                    {
                        getBiggestBook(allBooksArray);
                        break;
                    }
                    case 14:
                    {
                        copyBook(allBooksArray);
                        break;
                    }
                }
            }while(menuChoice!=0);
            System.out.println("Goodbye see you soon!");
        }
    }
}


