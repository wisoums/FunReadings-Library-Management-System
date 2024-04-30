package ThirdPackage;
import SecondPackage.Client;

//---------------------------------------------------------------------
// Assignment 1
// Question: Library Class
// Written by: Wissem Oumsalem (40291712) and Soukayna Haitami (40280964)
//-----------------------------------------------------------------------

//The purpose of this program is to implement the parent class Library. This class will have 3 children
//the classes book,journal and media.

public abstract class Library {

    //Attributes :
    protected String name;
    protected String author;
    protected int yearPublication;

    //Getter :
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public int getYearPublication() {
        return yearPublication;
    }
    //Setter :
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    //Parametrized Constructor:
    public Library(String name, String author, int yearPublication){
        this.name=name;
        this.author=author;
        this.yearPublication=yearPublication;
    }
    //Default Constructor :
    public Library(){
        this.name="";
        this.author="";
        this.yearPublication=0000;
    }
    //Copy constructor :
    public Library(Library library){
        this.name= library.getName();
        this.author=library.getAuthor();
        this.yearPublication=library.getYearPublication();
    }
    //Method toString() :
    @Override
    public String toString(){
        return "Name: " + name + "\nAuthor: " +author+ "\nYear of Publication: " + yearPublication;
    }


    //Method equals() :
    public boolean equals(Library library){
        if(name==library.getName() && author==library.getAuthor() && yearPublication==library.getYearPublication()){
            return true;
        }else{return false;}
    }

    @Override
    public boolean equals(Object otherObject){
        if (otherObject==null){ //Make sure that the compared object is not empty (null)
            return false;
        }
        if(this.getClass()!=otherObject.getClass()){ //Make sure that the compared object is from the same class
            return false;
        }
        Library otherLibrary = (Library) otherObject;
        return (name==otherLibrary.name && author==otherLibrary.author && yearPublication==otherLibrary.yearPublication);
    }


}
