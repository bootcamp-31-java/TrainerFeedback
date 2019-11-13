/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import dao.GeneralDAO;
import idao.IGeneralDAO;
import java.math.BigDecimal;
import java.util.List;
import model.Account;
import model.Employee;
import model.Job;
import model.Role;
import org.hibernate.SessionFactory;

/**
 *
 * @author G551VW
 */
public class Tester {

    public static void main(String[] args) {
        IGeneralDAO igd = new GeneralDAO(HibernateUtil.getSessionFactory());
//        
//        Job job = (Job) igd.getUser(new Job(), "name", "OwO");
//        
//        System.out.println(job);
       
//        igd.insertUpdateDelete(new Role(new BigDecimal("0"),"Koko"), false);   
        
        Employee emp = (Employee) igd.getUser(new Employee(), "email", "asep@apa.com");
        
        Account account = (Account) igd.getUserById(new Account().getClass(), emp.getId());
//        System.out.println(emp.getId());
        System.out.println(account.getPassword());
    }
}
