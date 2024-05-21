package employee;

import employee.Employee;


/**
 * @author tiemo
 * @version 1.0
 * @created 15-5-2024 ���� 3:20:19
 */
public interface EmployeeList {

    public boolean add(Employee employee);

    public boolean delete(long employeeID);

    public Employee get(long employeeID);

    public Employee get(String id, String password);

    public boolean update(long employeeID, Employee employee);

}