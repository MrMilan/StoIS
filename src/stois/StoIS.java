/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stois;

import entity.Persons;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryImpl;
import stois.login.Loginform;

/**
 *
 * @author Milhouse
 */
public class StoIS {

    /**
     * @param args the command line arguments
     */
    private static EntityManagerFactory emf = null;
    public final static int ITERATION_NUMBER_SALT = 36;
//    private static EntityManager em = null;

    public static HashMap<String,Object> container = new HashMap();
    
    public static void main(String[] args) {
        try {
            emf = Persistence.createEntityManagerFactory("StoISPU");
        } catch (Exception e) {
            System.err.println("No connection to database");
        }
        
        Loginform lm = new Loginform(emf);
        lm.setVisible(true);

        // TODO code application logic here
//        em = emf.createEntityManager();
//        Query q = em.createQuery("SELECT p FROM Persons AS p");
//        List<Persons> pe = q.getResultList();
//        for (Persons p : pe) {
//            System.out.println(p);
//        }
    }

}
