/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight;

import java.util.Date;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Emm
 */
public class BookResourceTest extends JerseyTest{
    
    BookDAO bookDAO = new BookDAO();
    
    protected Application configure(){
        
         enable(TestProperties.LOG_TRAFFIC);
         enable(TestProperties.RECORD_LOG_LEVEL);
         enable(TestProperties.DUMP_ENTITY);
         
       return new BookApplication(bookDAO); 
        /* 
         return new ResourceConfig().packages("com.pluralsight")
                                    .register(new AbstractBinder(){
              //overrides the method configure()
              //it assures that the object Book will be instantiated just once
              //and not each time we instantiate bookDAO, what would cause the test to fail
                                                    protected void configure(){
                                                        bind(bookDAO).to(BookDAO.class);
                                                    }
                                      });
       */  
        
    }
    
    
        
        
     @Test
     public void testGetBook(){
         
        Book response = target("books").path(book1_id).request().get(Book.class);
        /*
        BookDAO bookDAO = new BookDAO();
        boolean test = bookDAO.getBook("1").equals(response);
        if(test) System.out.println("Teste passou !");
        else System.out.println("Teste não passou !");
        */
        assertNotNull(response);
     }
    
    
     @Test
     public void testGetBooks(){
         
         List<Book> response = (List<Book>) target("books").request().get(new GenericType<List<Book>>() {});
         assertEquals(2, response.size());
         assertNotNull(response);

     }
     
     
     
     protected Response addBook(String titulo, String autor, String isbn, Date published){
         
         Book book = new Book();
         book.setAuthor(autor);
         book.setTitle(titulo);
         book.setIsbn(isbn);
         book.setPublished(published);
         
        //Antes de fazer um POST com o livro criado, para adicioná-lo na coleção 
        //lá no DAO, precisamos criar uma Entidade (Entity)
        Entity<Book> bookEntity = Entity.entity(book, MediaType.APPLICATION_JSON_TYPE);
        //quando eu faço o post request ele invoca o método addBook lá no BookResource
        //mas, e se eu tiver mais de um método POST e recebendo mais de um tipo de entidade ?????
         return target("/books").request().post(bookEntity);
     }
     
     String book1_id;
     String book2_id;
     //Adiciona método para ser executado antes dos testes
     @Before
     public void setUpBooks(){
         
         
         book1_id = this.addBook("xxxxxxx", "xxxxxxx", "xxxxxxx", new Date())
                         .readEntity(Book.class)
                         .getId();
         
         book2_id = this.addBook("AAAAAA", "AAAAAA", "AAAAAAAA", new Date())
                         .readEntity(Book.class)
                         .getId();
         
     }
     
     
     
     @Test
     public void testAddBook(){
         
        Date thisDate = new Date(); 
        String autor = "SSSSSSS";
        String titulo = "SSSSSS";
        String isbn = "ssssssssss";
        Response response = this.addBook(titulo, autor, isbn, thisDate);
        
        assertEquals(200, response.getStatus());
        
        //Desserializa a entidade para um objeto do tipo livro
        Book responseBook = response.readEntity(Book.class);
        assertNotNull(responseBook.getId());
        assertEquals(autor, responseBook.getAuthor());
        assertEquals(titulo, responseBook.getTitle());
        assertEquals(isbn, responseBook.getIsbn());
        assertEquals(thisDate, responseBook.getPublished());
        
     }
    
}
