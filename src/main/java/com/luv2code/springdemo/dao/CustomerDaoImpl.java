package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory; //patrz plik web-customer-tracker-servlet.xml bean id="sessionFactory"


    @Override
    public List<Customer> getAllCustomers() {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create query
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute query
        List<Customer> resultList = query.getResultList();

        //resurn results
        return resultList;
    }

    @Override
    public void saveCustomer(Customer customer) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //save or update object in DB
        currentSession.saveOrUpdate(customer); //jeśli parametr, któy przyjdzie będzie już zawierał "id" to wykona się update istniejącego customera
    }

    @Override
    public Customer getCustomer(int customerId) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create query
        Customer customer = currentSession.get(Customer.class, customerId);

        //resurn results
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create query
        Query<Customer> query = currentSession.createQuery("delete from Customer where id = :idToDelete");
        query.setParameter("idToDelete", id);

        //delete
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchForCustomers(String searchName) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create query
        Query<Customer> query = null;

        if (StringUtils.isNotBlank(searchName)) {

            query = currentSession.createQuery("from Customer " +
                    "where lower(firstName) like :searchFirstName or lower(lastName) like :searchLastName " +
                    "order by lastName", Customer.class);
            query.setParameter("searchFirstName", "%" + searchName.toLowerCase() + "%");
            query.setParameter("searchLastName", "%" + searchName.toLowerCase() + "%");
        } else {
            query = currentSession.createQuery("from Customer order by lastName", Customer.class);
        }

        // execute query
        List<Customer> resultList = query.getResultList();

        //resurn results
        return resultList;
    }
}
