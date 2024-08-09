import configuration.SpringConfig;
import entity.BookEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository=(BookRepository) context.getBean("bookRepository");
    public static void main(String[] args){
//        createNewBook();
        readBook();
        updateBook();

    }
    public static void readBook(){
        List<BookEntity> bookList=(List<BookEntity>) bookRepository.findAll();
        System.out.println("Found "+bookList.size()+" book(s) in the table book");
        System.out.println("They are: ");
        for(BookEntity book: bookList){
            System.out.println(book.toString());
        }
    }

    public static void updateBook(){
        BookEntity bookEntity=bookRepository.findOne(1);
        System.out.println("Book data before updating");
        System.out.println(bookEntity.toString());

        bookEntity.setAuthor("Jame");
        bookEntity.setNumberOfPage(199);
        bookEntity.setPrice(201);
        bookRepository.save(bookEntity);
        System.out.println("Book data after updating");
        System.out.println(bookEntity.toString());
    }
    public static void createNewBook(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF1219323");
        bookEntity.setNumberOfPage(234);
        bookEntity.setPrice(20.5);
        bookEntity.setPublishDate(LocalDate.parse("2024-08-09"));

        BookEntity result=bookRepository.save(bookEntity);

        if(result!=null){
            System.out.println("A new book saved successfully, book ID= "+ bookEntity.getId());
        }
    }
}
