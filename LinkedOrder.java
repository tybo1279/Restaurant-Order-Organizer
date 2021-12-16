
public class LinkedOrder {

  private final Order ORDER;

  private LinkedOrder previous;

  private LinkedOrder next;

  /**
   * A one argument constructor which sets previous and next to null
   * 
   * @Throws an IllegalArgumentException if order's timestamp is negative
   * @param order
   */
  public LinkedOrder(Order order) {
    this.ORDER = order;
    this.previous = null;
    this.next = null;
    Order testOrder = new Order("", 0);
    if (order.compareTo(testOrder) == -1) {
      throw new IllegalArgumentException("timestamp not valid");
    }
  }

  /**
   * A three argment constructor which sets all fields as specified
   * 
   * @Throws an IllegalArgumentException if order's timestamp is negative
   * @param order
   * @param prev
   * @param next
   */
  public LinkedOrder(Order order, LinkedOrder prev, LinkedOrder next) {
    this.ORDER = order;
    this.previous = prev;
    this.next = next;
    Order testOrder = new Order("", 0);
    if (order.compareTo(testOrder) == -1) {
      throw new IllegalArgumentException("timestamp not valid");
    }
  }

  /**
   * @return a reference to the order from this LinkedOrder
   */
  public Order getOrder() {
    return this.ORDER;
  }

  /**
   * @return a reference to the previous LinkedOrder attached to this one
   */
  public LinkedOrder getPrevious() {
    return this.previous;
  }

  /**
   * @return a reference to the next LinnkedOrder attached to this one
   */
  public LinkedOrder getNext() {
    return this.next;
  }

  /**
   * Sets the previous LinkedOrder attached to this one
   * 
   * @param previous
   */
  public void setPrevious(LinkedOrder previous) {
    this.previous = previous;
  }

  /**
   * Sets the next LinkedOrder to this one
   * 
   * @param next
   */
  public void setNext(LinkedOrder next) {
    this.next = next;
  }


}
