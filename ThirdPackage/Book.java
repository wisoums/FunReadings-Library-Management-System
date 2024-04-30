package ThirdPackage;
//---------------------------------------------------------------------
// Assignment 1
// Question: Book Class
// Written by: Wissem Oumsalem (40291712) and Soukayna Haitami (40280964)
//-----------------------------------------------------------------------

//This code's purpose is to implement the book class, a child of the library class
public class Book extends Library{

    //Attributes
    private int numberPages;
    private String idBook="B";
    private static int numberOfBooks=0;

    //Getter :
    public int getNumberPages() {
        return numberPages;
    }
    public String getIdBook() {
        return idBook;
    }
    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    //Setter :
    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }
    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }
    public void setNumberOfBooks() {
        this.numberOfBooks=numberOfBooks;
    }

    // Parametrized Constructor:
    public Book(int numberPages, String name, String author, int yearPublication) {
        super(name, author,yearPublication);
        this.numberPages = numberPages;
        numberOfBooks++;
        this.idBook = idBook+numberOfBooks;
    }

    //Default Constructor:
    public Book(){
        super();
        this.numberPages=0;
        numberOfBooks++;
        this.idBook=idBook+numberOfBooks;
    }

    // Copy Constructor:
    public Book(Book otherBook){
        this(otherBook.numberPages, otherBook.name,otherBook.author,otherBook.yearPublication);
        this.idBook="B"+numberOfBooks;
    }

    //Method toString()
    @Override
    public String toString() {
        return ("\nBook's ID: " + idBook+
                "\n" + super.toString() +
                "\nNumber  of pages: " + numberPages);
    }

    //Method equals()
    @Override
    public boolean equals(Object otherObject){
        if (otherObject==null){ //Make sure that the compared object is not empty (null)
            return false;
        }
        if(this.getClass()!=otherObject.getClass()){ //Make sure that the compared object is from the same class
            return false;
        }
        Book otherBook = (Book) otherObject;
        return (numberPages==otherBook.numberPages);
    }
}
