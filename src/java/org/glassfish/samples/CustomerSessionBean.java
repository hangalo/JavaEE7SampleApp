/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.samples;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import org.glassfish.samples.entities.Customer;
import org.glassfish.samples.entities.Customer_;

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
@Inject CustomerName name;

private List<Customer> cust;
    public List<Customer> getCustomers() {
        return (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
    }
    
    
    public void matchCustomers(AjaxBehaviorEvent evt){
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery criteria = builder.createQuery(Customer.class);
    
    Root root =criteria.from(Customer.class);
    criteria.select(root);
    Predicate condition = builder.like(root.get(Customer_.name), "%"+name.getValue()+"%");
    
    criteria.where(condition);
    
    TypedQuery query =  em.createQuery(criteria);
    cust = query.getResultList();
    return;
    }
}
