package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 * This is a general DAO interface for classes `Attraction` `Restaurant` and `Show`.
 */
public interface GeneralDAO<T> {
    /**
     * Gets all the elements from the database
     */
    public ArrayList<T> getAll();

    /**
     * Gets the element of id `id`
     *
     * @param id - the id of the element to get
     */
	public T getById(int id);

	/**
	 * Adds the element `new_item` to the database.
	 *
	 * @param new_item - the element to add
	 */
    public void add(T new_item);

    /**
     * TODO: describe how it works ?
     */
	public void edit(T item);

    /**
     * Deletes a row in the table `table`.
     *
     * @param table - the name of the table in which to look ;
     * @param id    - the id of the element to delete.
     */
    public static void delete(String table, int id) {
        Connection connexion = DBManager.getInstance().getConnection();

        try {
            Statement statement = connexion.createStatement();
            statement.executeUpdate("delete from "+ table +" where id = "+ id+";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }

    /**
     * Calculates the next available id for the given table
     *
     * @param table - the given table name
     *
     * @return the next available id
     */
    public static int unused_id(String table){
        Connection connexion = DBManager.getInstance().getConnection();
        int newID = 1;

        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select max(id) + 1 as next_id from " + table + ";");

            if (rs.next()) {
                newID = rs.getInt("next_id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
        return newID;
    }
}

