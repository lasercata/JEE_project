package utils;

import java.util.ArrayList;

/**
 * This is a general class to manage the different activities in the parc (the attractions, restaurants and shows). 
 * Its goal is to list, add, remove and edit items of the given type.
 */
public class Management <T>{
    /** 
     * ArrayList containing element of type T.
     */
    private ArrayList<T> items;

    /** 
     * Mangement class constructor.
     */
    public Management() {
        this.items = new ArrayList<T>();
    }

    /**
     * Mangement class constructor.
     * @return The ArrayList of items.
     */
    public ArrayList<T> getItems() {
        return this.items;
    }

    /**
     * List setter.
     * @param items The ArrayList of items we want to set our list to.
     */
    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    /**
     * Adds an element to the list.
     * @param element The element we add to the list.
     */
    public void addElement(T element) {
        this.getItems().add(element);
    }

    /**
     * Deletes an element from the list.
     * @param index The index of the element we want to remove.
     */
    public void delElement(int index) {
        this.getItems().remove(index);
    }

    /**
     * toString method that calls the toString methos of the T class.
     * @return a String representation of the list.
     */
    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < this.getItems().size(); i++) {
            res += String.format("%d) %s", i+1, this.getItems().get(i).toString());
        }
        return res;
    }
}
