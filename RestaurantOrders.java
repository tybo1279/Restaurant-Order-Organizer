
public class RestaurantOrders implements SortedListADT<Order> {

  private LinkedOrder head;

  private LinkedOrder tail;

  private int size;

  private final int CAPACITY;

  /**
   * A no argument constructor which creates a list with capacity 20
   */
  public RestaurantOrders() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.CAPACITY = 20;
  }

  /**
   * A one argument constructor which creates a list with the provided positive capacity
   * 
   * @Throws an IllegalArgumentException if the provided capacity is 0 or negative
   * @param capacity
   */
  public RestaurantOrders(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("invalid capacity");
    }
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.CAPACITY = capacity;
  }

  /**
   * @return the capacity of this list
   */
  public int capacity() {
    return this.CAPACITY;
  }

  /**
   * @return a String representation of the orders in this list from head to tail, space seperated
   */
  public String readForward() {

    String orders = "";
    for (int i = 0; i < size; ++i) {
      orders = orders.concat(" " + get(i).toString());
    }
    return orders;
  }

  /**
   * @return a String representation of the orders in this list from tail to head, space seperated
   */
  public String readBackward() {

    String orders = "";
    for (int i = size; i < size - 1 && i >= 0; --i) {
      orders = orders.concat(" " + get(i).toString());
    }
    return orders;
  }

  /**
   * Removes all orders from this list
   */
  @Override
  public void clear() {
    this.size = 0;
    this.head = null;
    this.tail = null;
  }

  /**
   * @return the order at position index of this list, without removing it
   */
  @Override
  public Order get(int index) {

    LinkedOrder runner = this.head;
    int counter = 0;
    while (counter < index - 1) {
      ++counter;
      runner = runner.getNext();
    }
    if (runner == null) {
      return null;
    }
    return runner.getOrder();

  }

  /**
   * @return the index of the specified order's dishes in the list, or -1 if not found.
   */
  @Override
  public int indexOf(Order findOrder) {

    String match = findOrder.getDishes();
    for (int i = 0; i < size - 1; ++i) {
      Order test = get(i);
      if (test.getDishes().equals(match)) {
        return i;
      }
    }
    return -1;

  }

  /**
   * @return true if and only if the list is currently empty; false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Adds a new Order to this sorted list in the correct position if there is room in the list
   * 
   * @Throws an IllegalArgumentException if newOrder has the same timestamp as an existing order
   */
  @Override
  public void placeOrder(Order newOrder) {

    ++this.size;
    if (size > this.CAPACITY) {
      throw new IllegalArgumentException("list at capacity");
    }
    if (newOrder == null) {
      throw new IllegalArgumentException("not a valid order");
    }
    LinkedOrder insert = new LinkedOrder(newOrder);
    LinkedOrder runner = this.head;
    int index = 0;
    if (runner == null) { // if list has no orders
      this.head = insert;
      this.tail = insert;
      return;
    }
    if (runner.getOrder().compareTo(newOrder) == 0) { // compatibility checker
      throw new IllegalArgumentException("incompatible order");
    }
    while (runner.getOrder().compareTo(newOrder) == 1) {// iterator through the list to find index
                                                        // of insertion
      ++index;
      runner = runner.getNext();
    }
    if (size == 2) { // if the list has a head but no tail (1 order)
      this.tail = insert;
      return;
    }
    if (runner.equals(null) && index > 1) {// if the order should be placed at the end of the list
      runner = runner.getPrevious();
      runner.setNext(insert);
      return;
    }

  }

  /**
   * @return the order at the given index and removes it
   */
  @Override
  public Order removeOrder(int index) {

    --this.size;
    LinkedOrder runner = this.head;
    LinkedOrder runnerTail = this.tail;
    if (index < 0) {
      throw new IndexOutOfBoundsException("invalid index");
    }
    if (get(index) == null) {
      return null;
    }
    if (runner == null) {
      return null;
    }

    Order runnerOrder = runner.getOrder();
    if (index > 0) {
      runnerOrder = get(index);
      if (runnerOrder.equals(this.tail.getOrder())) {
        this.tail = this.tail.getPrevious();
        return runnerTail.getOrder();
      }
      runner = null;
      return runnerOrder;
    }
    if (index == 0) {
      this.head = this.head.getNext();
      if (size == 0) {
        this.tail = this.tail.getPrevious();
        return runnerTail.getOrder();
      }
      return runner.getOrder();
    }

    return null;
  }

  /**
   * @return the number of orders currently in the list
   */
  @Override
  public int size() {
    return this.size;
  }
}
