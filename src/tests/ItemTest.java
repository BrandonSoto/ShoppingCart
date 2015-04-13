/*
 * TCSS 305 - Winter 2014
 * Assignment 
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import model.Item;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Brandon Soto
 * @version Jan 16, 2014
 */
public class ItemTest {
    
    /**
     * Default name for Item. 
     */
    private static final String NAME = "button";
    
    /** 
     * Default price for an individual item. 
     */
    private static final BigDecimal PRICE = new BigDecimal("0.95");
    
    /**
     * Default bulk quantity for an item.
     */
    private static final int BULK_QUANTITY = 10;
    
    /**
     * Default bulk price for an item. 
     */
    private static final BigDecimal BULK_PRICE = new BigDecimal("5.00");

    /**
     * Item object to be tested. 
     */
    private Item myItem;
    
    /**
     * Initializes item before every test. 
     */
    @Before
    public void setUp() {
        myItem = new Item(NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
    }

    /**
     * Tests the hashCode method of the Item class. 
     */
    @Test
    public final void testHashCode() {
        final Item testHash = new Item(NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
        
        assertEquals("Hash codes aren't equal", testHash.hashCode(), myItem.hashCode());
    }

    /**
     * Tests Item's calculateItemTotal method with an item that has a bulk quantity.
     */
    @Test
    public final void testCalculateItemTotalWithBulk() {
        final BigDecimal expTotal = new BigDecimal("11.90");
       
        assertEquals("Totals not equal", expTotal, myItem.calculateItemTotal(22));
    }
    
    /**
     * Tests Item's calculateItemTotal method with an item that does not have a bulk
     * quantity.
     */
    @Test
    public final void testCalculateItemTotalWithoutBulkTotal() {
        myItem = new Item(NAME, new BigDecimal("1.25"));
        final BigDecimal expTotal = new BigDecimal("12.50");
        
        assertEquals("Totals not equal", expTotal, myItem.calculateItemTotal(10));
    }

    /**
     * Test's Items' toStrin method with an item with name and price. 
     */
    @Test
    public final void testToStringDefaultItem() {
        final StringBuilder expString = new StringBuilder("button, $0.95");
        
        myItem = new Item(NAME, PRICE);
        
        assertEquals("Strings not equal", expString.toString(), myItem.toString());
    }
    
    /**
     * Test Item's toString method with an item with a name, price, bulk quantity, and
     * bulk price.
     */
    @Test
    public final void testToStringFullItem() {
        final StringBuilder expString = new StringBuilder("button, $0.95 (10 for $5.00)");
        
        assertEquals("Strings not equal", expString.toString(), myItem.toString());
    }

    /**
     * Tests Item's equals method with an "equal" object.
     */
    @Test
    public final void testEquals() {
        final Item testEquals = new Item(NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
        
        assertEquals("Items aren't equal", testEquals, myItem); 
    }
    
    /**
     * Tests Item's equals method with a different object.
     */
    @Test
    public final void testEqualsWithOtherObject() {
        final Double other = new Double(5.0);
        
        assertFalse("Objects equal", myItem.equals(other));
    }
    
    /**
     * Tests Item's equals method when an object is checked if it's equal to itself.
     */
    @Test
    public final void testEqualsWithSelf() {
        assertEquals("Different objects.", myItem, myItem);
    }
    
    /**
     * Tests Item's equals method with a different Item object.
     */
    @Test
    public final void testEqualsWithOtherItem() {
        final Item other = new Item("gum", new BigDecimal("1.05"));
        
        assertNotEquals("Objects are equal", myItem, other);
    }
    
    /**
     * Test Item's equals method with a null value.
     */
    @Test
    public final void testEqualsWithNull() {
        assertNotEquals("Objects are equal", myItem, null);
        assertNotNull("Item is null", myItem);
    }
    
    

}
