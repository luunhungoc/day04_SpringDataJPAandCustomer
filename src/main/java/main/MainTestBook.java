package main;

import configuration.SpringConfig;
import entity.BookEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MainTestBook {
    static ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository=(BookRepository) context.getBean("bookRepository");

    public static void main(String[] args){
        createNewBook();
//        readAllBook();
//        readBookById(1);
//        updateBook(1);
//        deleteBookById(1);
//        deleteAll();
//        findByAuthor("Jame");
        findByAuthorAndPrice("Jame",200);
    }

    public static void findByAuthorAndPrice(String author, double price){
        List<BookEntity> bookList=bookRepository.findByAuthorAndPrice(author,price);
        if(bookList.size()!=0){
            System.out.println("Found "+bookList.size()+" book(s) of "+author+" and price of "+price);
            System.out.println("They are: ");
            for(BookEntity book:bookList){
                System.out.println(book.toString());
            }

        }
        else  System.out.println("Not found "+bookList.size()+" book(s) of "+author+" and price of "+price);
    }

    public static void findByAuthor(String author){
        List<BookEntity> bookList=bookRepository.findByAuthor(author);
        if(bookList.size()!=0){
            System.out.println("Found "+bookList.size()+" book(s) of Roger");
            System.out.println("They are: ");
            for(BookEntity book:bookList){
                System.out.println(book.toString());
            }
        }
    }

    public static void deleteBookById(int i){
        BookEntity bookEntity = bookRepository.findById(i).get();
        if(bookEntity!=null){
            System.out.println("Deleted bookId"+i);
            bookRepository.delete(bookEntity);
            }
        }
    public static void deleteAll(){
        System.out.println("Deleted All");
            bookRepository.deleteAll();

    }
    public static void createNewBook(){
    BookEntity bookEntity =new BookEntity();
    bookEntity.setName("Java A-Z");
    bookEntity.setAuthor("Roger");
    bookEntity.setCategory("IT books");
    bookEntity.setIsbn("ISIBF1219323");
    bookEntity.setNumberOfPage(234);
    bookEntity.setPrice(20.5);
    bookEntity.setPublishDate(LocalDate.parse("2024-08-09"));

    BookEntity result= bookRepository.save(bookEntity);

    if(result!=null){
        System.out.println("A new book saved successfully, book ID= "+ bookEntity.getId());
    }

}
public static void readAllBook(){
    List<BookEntity> bookList=(List<BookEntity>) bookRepository.findAll();
    System.out.println("Found "+bookList.size()+" book(s) in the table book");
    System.out.println("They are: ");
    for(BookEntity book: bookList){
        System.out.println(book.toString());
    }
}

public static void readBookById(int id){
    BookEntity bookEntity = bookRepository.findById(1).get();
    if(bookEntity !=null){
        System.out.println("Found a book with book ID= "+id);
        System.out.println(bookEntity.toString());
    }
    else{
        System.out.println("Not found any book with book Id= "+id);
    }
}

public static void updateBook(int i){
    BookEntity bookEntity = bookRepository.findById(i).get();
    System.out.println("Book data before updating");
    System.out.println(bookEntity.toString());

    bookEntity.setAuthor("Jame");
    bookEntity.setNumberOfPage(199);
    bookEntity.setPrice(201);
    bookRepository.save(bookEntity);
    System.out.println("Book data after updating");
    System.out.println(bookEntity.toString());
}

}
