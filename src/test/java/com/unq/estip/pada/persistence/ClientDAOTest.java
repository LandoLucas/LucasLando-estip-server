package com.unq.estip.pada.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Client;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ClientDAOTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private ClientDAO clientDAO;
    
    public void setClientDao(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Before
    public void setUp() {
        for( Client c : clientDAO.findAll() ) {
            clientDAO.delete(c);
        }
    }
    
    @Test
    public void getClient() {
        Client c = new Client("lucas", "lando", "", "", "");
        clientDAO.save(c);

        assertEquals(1, clientDAO.findAll().size());
    }
    
    @Test
    public void removeClient() {
        Client c = new Client("lucas", "lando", "", "", "");
        clientDAO.save(c);
        List<Client> cs = clientDAO.findAll();
        assertEquals(1, cs.size());

        c.setDeleted(true);
        clientDAO.update(c);
        assertEquals(0, clientDAO.findAll().size());

        assertNull(clientDAO.findById(cs.get(0).getId()));
    }
    
    @Test
    public void getClients() {
        Client c = new Client("lucas", "lando", "", "", "");
        Client c2 = new Client("lucas2", "lando2", "1", "2", "3");
        
        clientDAO.save(c);
        clientDAO.save(c2);
        
        assertEquals(2, clientDAO.findAll().size());
    
        c.setDeleted(true);
        clientDAO.save(c);
        
        assertEquals(1, clientDAO.findAll().size());
        assertEquals("lucas2", clientDAO.findAll().get(0).getFirstName());
    }
    
    
    
    
    
    
}
