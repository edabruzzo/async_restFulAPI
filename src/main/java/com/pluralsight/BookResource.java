/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Emm
 */

@Path("/books")
public class BookResource {
    
    //instead of instantiate here an instance of bookDAO, we inject it via @Context
    @Context BookDAO bookDAO;
    //BookDAO bookDAO = new BookDAO();
    
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
       GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(this.bookDAO.getBooks()) {
        };

        return Response.ok(entity).build();
    }
    */
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks(){
        return bookDAO.getBooks();
    }
    
    
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("id") String id){
        return bookDAO.getBook(id);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book addBook(Book book){
        //A Serialização e Deserialização do objeto recebido e retornado é feita 
        //totalmente pelo Jersey. Não temos que nos preocupar com isso.
        return bookDAO.addBook(book);
    }
    
    
    
    
}
