/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.samples;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.samples.entities.Customer;

/**
 *
 * @author praveen
 */
@Stateless
@javax.inject.Named
public class CustomerSessionBean {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Customer> getCustomers() {
        return (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
    }
}
