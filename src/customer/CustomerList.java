package customer;


public interface CustomerList {

	/**
	 * 
	 * @param customerID
	 */
	public boolean add(Customer customer);

	public boolean delete(long customerID);

	public Customer get(long customerID);

	public Customer get(String id, String password);

	public Customer get(String id);

	public boolean update(long customerID, Customer customer);

}