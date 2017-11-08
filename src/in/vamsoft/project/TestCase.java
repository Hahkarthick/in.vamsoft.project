/**
 * 
 */
package in.vamsoft.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author vamsoft.
 * JUnit Test Cases.
 *
 */
class TestCase {

  RetailStore Test=new RetailStore("Croma");
  /**
   * @throws java.lang.Exception.
   */
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception.
   */
  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }


  /**
   * Test method for
   * {@link in.vamsoft.project.RetailStore#addCustomer(java.lang.String, java.lang.String)}.
   */
  @Test
  void testAddCustomer() {
    
     boolean result= Test.addCustomer("Mani","98515744");
    
  }

  /**
   * Test method for
   * {@link in.vamsoft.project.RetailStore#addProduct(java.lang.String, int, float)}.
   */
  @Test
  void testAddProduct() {    
    boolean result= Test.addProduct("Ac", 10, 35000);
  }

  /**
   * Test method for
   * {@link in.vamsoft.project.RetailStore#checkProducts(java.lang.String)}.
   */
  @Test
  void testCheckProducts() {
    int result= Test.checkProducts("AC");
  }

  /**
   * Test method for
   * {@link in.vamsoft.project.RetailStore#bookProduct(int, java.lang.String, int)}.
   */
  @Test
  void testBookProduct() {
    boolean result=Test.bookProduct(1, "AC", 2);
  }

  /**
   * Test method for
   * {@link in.vamsoft.project.RetailStore#calculateNetAmount(int, double)}.
   */
  @Test
  void testCalculateNetAmount() {
    double result=Test.calculateNetAmount(1, 5);
  }


}
