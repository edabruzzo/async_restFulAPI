/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Emm
 */
public class BookApplication extends ResourceConfig {
    
        //esta classe permitirá a compactação do código em Main e BookResourceTest
        //construtor 
        //esse cara é quem vai produzir um objeto do tipo ResourceConfig
        public BookApplication(final BookDAO bookDAO){
            
            //NÃO ENTENDI ESTA OMISSÃO DA INSTANCIA DE RESOURCECONFIG
            //ISTO É PORQUE A CLASSE JÁ ESTENDE O RESOURCECONFIG ???
            //  final ResourceConfig rc = new ResourceConfig()
                                   new ResourceConfig()
                                      .packages("com.pluralsight")
                                      .register(new AbstractBinder(){
                                                    //overrides the method configure()
                                                    protected void configure(){
                                                        bind(bookDAO).to(BookDAO.class);
                                                    }
                                      });
            
        }


}
