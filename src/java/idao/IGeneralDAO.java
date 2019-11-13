/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author G551VW
 * @param <E>
 */
public interface IGeneralDAO<E> {

    /**
     * ini fungsi untuk menampilkan semua data dari object model
     *
     * @param entity entity merupakan object model
     * @return return berupa daftar dari object model
     */
    public List<E> getAll(E entity);

    /**
     * ini fungsi untuk menampilkan semua data atau mencari data
     *
     * @param entity parameter entity berupa pbject model
     * @param category parameter category berupa nama atribut model
     * @param key parameter key berupa string
     * @return return berupa daftar dari parameter entity
     */
    public List<E> search(E entity, String category, Object key);

    /**
     * ini fungsi untuk meanmbah, mengubah, menghapus data
     *
     * @param entity parameter entity berupa object dari class
     * @param isDel parameter isDel berupa true / false
     * @return return berupa true / false
     */
    public boolean insertUpdateDelete(E entity, boolean isDel);

    /**
     * ini fungsi untuk mendapatkan hasil yang unik sesuai kata kunci
     *
     * @param entity parameter entity merupakan object model
     * @param category parameter category berupa atribut yang ada di object
     * model
     * @param key parameter key berupa String
     * @return return berupa daftar dari parameter entity
     */
    public E getUser(E entity, String category, Object key);

    /**
     * ini fungsi untuk mendapatkan hasil yang persis dengan kata kunci
     *
     * @param entity parameter entity merupakan object model
     * @param category parameter category berupa atribut yang ada di object
     * model
     * @param key parameter key berupa String
     * @return return berupa daftar dari parameter entity
     */
    public List<E> getValid(E entity, String category, Object key);
    
    public E getUserById(Class clas, Serializable id);
    
}
