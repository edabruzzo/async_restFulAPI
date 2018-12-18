package com.pluralsight;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MyResourceTest extends JerseyTest{

    /*
    //There's no need to setUp the server when you extend JerseyTest.
    //It takes care of it for you
    
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        //c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    
    protected Application configure(){
        return new ResourceConfig().packages("com.pluralsight");
    }
    
    @Test
    public void testGetIt() {
        //String responseMsg = target.path("myresource").request().get(String.class);
        String responseMsg = target("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
