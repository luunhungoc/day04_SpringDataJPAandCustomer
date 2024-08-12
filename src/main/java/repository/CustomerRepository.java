package repository;

import entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {
    //Get all books where author = Roger
    List<CustomerEntity> findById(int id);
    List<CustomerEntity> findByName(String name);
    List<CustomerEntity> findByPhoneOrEmail(String phone, String email);
    List<CustomerEntity> findAll();

//    List<BookEntity> findByAuthorAndPrice(String author,double price);
//    List<BookEntity> findByPriceOrNumberOfPage(double price, int numOfPage);
//    List<BookEntity> findByPriceLessThan(double price);
//    List<BookEntity> findByPriceGreaterThanEqual(double price);
//    List<BookEntity> findByNameContaining(String searchWords);
//    BookEntity findByIsbn(String isbn);
//    List<BookEntity> findByPublishDateAfter(LocalDate date);

//    @Query("select b from BookEntity b where b.name like ?1%") //?1 : param 1, ?2 : param 2
//    List<BookEntity> getBookNameStartWith(String name);
//
    @Query(value="select * from customer c where c.sex = ?1 and (2024-year(c.birthday))>=?2 and  (2024-year(c.birthday)) <=?3",nativeQuery=true)
    List<CustomerEntity> findBySexAndAge(String sex,double fromAge, double toAge);

}

