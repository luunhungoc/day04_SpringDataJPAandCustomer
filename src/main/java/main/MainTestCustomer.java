package main;

import configuration.SpringConfig;

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
//        List<CustomerEntity> customberList= (List<CustomerEntity>) customerRepository.findAll();
//        if(customerRepository.size()!=0){
//            System.out.println("Found "+customberList.size()+" customer(s)");
//            System.out.println("They are: ");
//            for(CustomerEntity customer:customberList){
//                System.out.println(customer.toString());
//            }
//        }

        System.out.println(customerRepository.findById(2));
        System.out.println(customerRepository.findByName("A"));


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


}
