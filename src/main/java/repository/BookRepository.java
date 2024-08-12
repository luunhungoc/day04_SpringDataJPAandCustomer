package repository;

import entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity,Integer> {


    //Get all books where author = Roger
    List<BookEntity> findByAuthor(String author);
    List<BookEntity> findByAuthorAndPrice(String author, double price);
    List<BookEntity> findByPriceOrNumberOfPage(double price, int numOfPage);
    List<BookEntity> findByPriceLessThan(double price);
    List<BookEntity> findByPriceGreaterThanEqual(double price);
    List<BookEntity> findByNameContaining(String searchWords);
    BookEntity findByIsbn(String isbn);
    List<BookEntity> findByPublishDateAfter(LocalDate date);

    @Query("select b from BookEntity b where b.name like ?1%") //?1 : param 1, ?2 : param 2
    List<BookEntity> getBookNameStartWith(String name);

    @Query(value="select * from book b where b.price <?1 and b.numberPage >=?2",nativeQuery=true)
    List<BookEntity> getBookWherePriceLessThanAndNumOfPageGreaterThan(double price, int numOfPage);

}
