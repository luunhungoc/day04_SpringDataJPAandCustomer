package main;

import configuration.SpringConfig;

import entity.BookEntity;
import entity.CustomerEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

public class MainTestCustomer {
    static ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfig.class);
    static CustomerRepository customerRepository=(CustomerRepository) context.getBean("customerRepository");

    public static void main(String[] args){
        createNewCustomer();
        findAll();
//        findById(1);
        //findByName(String name);
        findByPhoneOrEmail("0236123456", "a@gmail.com");
        //List all the customers who are men and years old from 20 to 30.
        findBySexAndAge("male",20,30);


    }

    public static void createNewCustomer(){
        CustomerEntity customerDB=new CustomerEntity();
        customerDB.setName("A");
        customerDB.setBirthday(LocalDate.parse("2001-08-09"));
        customerDB.setSex("Male");
        customerDB.setEmail("123@gmail.com");
        customerDB.setPhone("0236123456");
        customerDB.setAddress("Danang");

        CustomerEntity result= customerRepository.save(customerDB);

        if(result!=null){
            System.out.println("A new customer saved successfully, customer ID= "+ customerDB.getId());
        }
    }

    public static void findBySexAndAge(String sex, double fromAge, double toAge){
        List<CustomerEntity> customerList=customerRepository.findBySexAndAge(sex,fromAge,toAge);
        if(customerList.size()!=0){
            System.out.println("Found "+customerList.size()+"customer(s) who are "+sex+" and ages from "+fromAge+" to "+toAge);
            System.out.println("They are: ");
            for(CustomerEntity c:customerList){
                System.out.println(c.toString());
            }

        }
        else  System.out.println("Not found "+customerList.size()+" customer(s) who are "+sex+" and ages from "+fromAge+" to "+toAge);
    }

    public static void findByPhoneOrEmail(String phone, String email){
        List<CustomerEntity> customerList=customerRepository.findByPhoneOrEmail(phone,email);
        if(customerList.size()!=0){
            System.out.println("Found "+customerList.size()+"customer(s) with phone: "+phone+" or email: "+email);
            System.out.println("They are: ");
            for(CustomerEntity c:customerList){
                System.out.println(c.toString());
            }

        }
        else  System.out.println("Not found "+customerList.size()+" phone: "+phone+" or email: "+email);
    }
    public static void findById(int id){
        CustomerEntity customerEntity = (CustomerEntity) customerRepository.findById(1);
        if(customerEntity !=null){
            System.out.println("Found a customer with customer ID= "+id);
            System.out.println(customerEntity.toString());
        }
        else{
            System.out.println("Not found any customer with customer Id= "+id);
        }
    }

    public static void findAll(){

        List<CustomerEntity> customberList= (List<CustomerEntity>) customerRepository.findAll();
        if(customberList.size()!=0){
            System.out.println("Found "+customberList.size()+" customer(s)");
            System.out.println("They are: ");
            for(CustomerEntity customer:customberList){
                System.out.println(customer.toString());
            }
        }
}

}
