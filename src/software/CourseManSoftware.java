package software;

import domainapp.basics.apps.tool.DomainAppTool;
import domainapp.basics.exceptions.ApplicationRuntimeException;
import domainapp.basics.setup.Cmd;
import model.bill.Bill;
import model.bill.InternetTimeOrder;
import model.equipment.Equipment;
import model.equipment.Hardware;
import model.equipment.Software;
import model.people.Customer;
import model.product.Drink;
import model.product.Food;
import model.product.Product;

/**
 * @overview 
 *  Encapsulate the basic functions for setting up and running a software for the CourseMan domain model.  
 *  
 * @author dmle
 *
 * @version 
 */
public class CourseManSoftware {
  
  // CourseMan's domain model
  private static final Class[] model = {
          Customer.class,
          Product.class,
          Food.class,
          Drink.class,
          Equipment.class,
          Software.class,
          Hardware.class,
          Bill.class,
          InternetTimeOrder.class
  };
  
  public static void main(String[] args) {
    // 1: configure and run software
    //configure();
    
    // 2: run software
    run();
    
    //deleteDomainData(Student.class, City.class);
    
    //deleteDomainSchema(Enrolment.class);
    
    //createDomainSchema(Enrolment.class, Student.class, City.class, SClass.class);
    
    //deleteApplicationConfig();
  }

  /**
   * @effects 
   *  configure and run the software.
   *  
   *  <p>Throws ApplicationRuntimeException if an error occured.
   * 
   * @version 
   */
  private static void configure() throws ApplicationRuntimeException {
    DomainAppTool.runSetUp(Cmd.Configure, model);    
  }
  
  /**
   * @effects 
   *  Run the software.
   *  
   *  <p>Throws ApplicationRuntimeException if an error occured.
   *  
   * @version 
   * 
   */
  private static void run() throws ApplicationRuntimeException {
    DomainAppTool.run(model);    
  }

  ////// Other set-up commands ///////
  /**
   * @effects 
   *  Delete domain data of the domain classes specified in <tt>domainClasses</tt>.
   *  
   *  <p>Throws ApplicationRuntimeException if an error occured.
   * 
   * @version 
   */
  private static void deleteDomainData(Class...domainClasses) throws ApplicationRuntimeException {
    if (domainClasses == null || domainClasses.length == 0)
      return;
    
    DomainAppTool.runSetUp(Cmd.DeleteDomainData, domainClasses);
  }
  
  /**
   * @effects 
   *  Delete domain schema of the domain classes specified in <tt>domainClasses</tt>.
   *  
   *  <p>Throws ApplicationRuntimeException if an error occured.
   * 
   * @version 
   */
  private static void deleteDomainSchema(Class...domainClasses) throws ApplicationRuntimeException {
    if (domainClasses == null || domainClasses.length == 0)
      return;
    
    DomainAppTool.runSetUp(Cmd.DeleteDomainSchema, domainClasses);    
  }

  /**
   * @effects 
   *  Create domain schema of the domain classes specified in <tt>domainClasses</tt>.
   *  
   *  <p>Throws ApplicationRuntimeException if an error occured.
   * 
   * @version 
   */
  private static void createDomainSchema(Class...domainClasses) throws ApplicationRuntimeException {
    if (domainClasses == null || domainClasses.length == 0)
      return;
    
    DomainAppTool.runSetUp(Cmd.CreateDomainSchema, domainClasses);    
  }

  /**
   * @effects 
   *  Delete application configuration
   *  
   *  <p>Throws ApplicationRuntimeException if an error occured.
   * 
   * @version 
   */
  private static void deleteApplicationConfig() throws ApplicationRuntimeException {
    DomainAppTool.runSetUp(Cmd.DeleteConfig);    
  }
}
