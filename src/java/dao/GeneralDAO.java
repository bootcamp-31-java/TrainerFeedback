/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import idao.IGeneralDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author G551VW
 * @param <E>
 */
public class GeneralDAO<E> implements IGeneralDAO<E> {

    private SessionFactory sf;
    private Session s;
    private Transaction tran;

    public GeneralDAO(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * ini fungsi untuk meanmbah, mengubah, menghapus data
     *
     * @param entity parameter entity berupa object dari class
     * @param isDel parameter isDel berupa true / false
     * @return return berupa true / false
     */
    @Override
    public boolean insertUpdateDelete(E entity, boolean isDel) {
        boolean result = false;
        s = this.sf.openSession();
        tran = s.beginTransaction();
        try {
            if (isDel == true) {
                s.delete(entity);
            } else {
                s.saveOrUpdate(entity);
            }
            tran.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            s.close();
        }
        return result;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * ini fungsi untuk menampilkan semua data dari object model
     *
     * @param entity entity merupakan object model
     * @return return berupa daftar dari object model
     */
    @Override
    public List<E> getAll(E entity) {
        List<E> entities = new ArrayList<>();
        s = this.sf.openSession();
        tran = s.beginTransaction();

        try {
            entities = s.createQuery("FROM " + entity.getClass().getSimpleName() + " ORDER BY 1").list();
        } catch (Exception e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            s.close();
        }
        return entities;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * ini fungsi untuk menampilkan semua data atau mencari data
     *
     * @param entity parameter entity berupa object model
     * @param category parameter category berupa nama atribut model
     * @param key parameter key berupa string
     * @return return berupa daftar dari parameter entity
     */
    @Override
    public List<E> search(E entity, String category, Object key) {
        List<E> entities = new ArrayList<>();
        s = this.sf.openSession();
        tran = s.beginTransaction();
        try {
            if (key == null || category == null) {
                entities = s.createQuery("FROM " + entity.getClass().getSimpleName() + " ORDER BY 1").list();
            } else {
                Criteria crit = s.createCriteria(entity.getClass());
                Criterion cat = Restrictions.like(category, key + "%", MatchMode.ANYWHERE);
//                Criterion catInt = Restrictions.sqlRestriction(category+" LIKE %"+key+"%");
//                Criterion lName = Restrictions.like(category, key + "%", MatchMode.ANYWHERE);
                Disjunction disj = Restrictions.disjunction();
//                disj.add(catInt);
                disj.add(cat);
//                disj.add(lName);
                crit.add(disj);
                entities = crit.list();
            }
//            entities = s.createQuery("FROM " + entity.getClass().getSimpleName() + " WHERE " + entity.getClass() + " ORDER BY 1").list();
        } catch (Exception e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            s.close();
        }
        return entities;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * ini fungsi untuk mendapatkan hasil yang unik sesuai kata kunci
     *
     * @param entity parameter entity merupakan object model
     * @param category parameter category berupa atribut yang ada di object
     * model
     * @param key parameter key berupa String
     * @return return berupa daftar dari parameter entity
     */
    @Override
    public E getUser(E entity, String category, Object key) {
        s = this.sf.openSession();
        tran = s.beginTransaction();
        E entities = null;
        try {
            Criteria crit = s.createCriteria(entity.getClass());
            Criterion cat = Restrictions.like(category, key + "%", MatchMode.EXACT);
            crit.add(cat);
            entities = (E) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            s.close();
        }
        return entities;
    }

    /**
     * ini fungsi untuk mendapatkan hasil yang sesuai kata kunci
     *
     * @param entity parameter entity merupakan object model
     * @param category parameter category berupa atribut yang ada di object
     * model
     * @param key parameter key berupa String
     * @return return berupa daftar dari parameter entity
     */
    @Override
    public List<E> getValid(E entity, String category, Object key) {
        List<E> entities = new ArrayList<>();
        s = this.sf.openSession();
        tran = s.beginTransaction();
        try {

            Criteria crit = s.createCriteria(entity.getClass());
            Criterion cat = Restrictions.like(category, key + "%", MatchMode.EXACT);
            Disjunction disj = Restrictions.disjunction();
            disj.add(cat);
            crit.add(disj);
            entities = crit.list();

        } catch (Exception e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            s.close();
        }
        return entities;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E getUserById(Class clas, Serializable id) {
        s = this.sf.openSession();
        tran = s.beginTransaction();
        E entity = null;
        try {
            entity = (E) s.load(clas, id);
            Hibernate.initialize(entity);
        } catch (Exception e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            s.close();
        }
        return entity;
    }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}
