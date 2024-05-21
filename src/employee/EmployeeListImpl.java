package employee;


import java.util.*;
import java.io.*;

public class EmployeeListImpl implements EmployeeList {
    private static final String EMPLOYEE_ACCOUNT_FILE_PATH = "src/data/employee.txt";
    private static final int EMPLOYEE_COLUMN_NUM = 8;
    private ArrayList<Employee> employeeList;

    public EmployeeListImpl() {
        this.employeeList = new ArrayList<Employee>();
        loadEmployeeFromFile();
    }


    /**
     * 파일(employee.txt)에서 직원 정보를 읽어와 employeeList에 추가하는 메소드
     */
    private void loadEmployeeFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_ACCOUNT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] employeeRow = line.split("\\|");
                if (employeeRow.length == EMPLOYEE_COLUMN_NUM) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(Integer.parseInt(employeeRow[0]));
                    employee.setID(employeeRow[1]);
                    employee.setPassword(employeeRow[2]);
                    employee.setName(employeeRow[3]);
                    employee.setSex(employeeRow[4]);
                    employee.setPhoneNumber(employeeRow[5]);
                    employee.setDepartment(employeeRow[6]);
                    employee.setEnteringDate(employeeRow[7]);
                    employeeList.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 파일(employee.txt)에 새로운 직원 정보를 쓰는 메소드
     */
    private void writeEmployeeToFile(Employee employee) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE_ACCOUNT_FILE_PATH, true))) {
            String employeeRow = writeEmployeeRowForm(employee);
            bw.write(employeeRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 직원 정보의 변동사항을 반영허여 파일(employee.txt)에 작원 정보를 새로 쓰는 메소드 (delete, modify)
     */
    private void writeEmployeeListToFile(ArrayList<Employee> employeeList) {
        for (Employee employee : employeeList) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE_ACCOUNT_FILE_PATH, true))) {
                String customerRow = writeEmployeeRowForm(employee);
                bw.write(customerRow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 파일(employee.txt)에 있는 데이터 모두 지우기
     */
    private void clearFileContent() {
        try (FileWriter fw = new FileWriter(EMPLOYEE_ACCOUNT_FILE_PATH, false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String writeEmployeeRowForm(Employee employee) {
        return employee.getEmployeeID() + "|" +
                employee.getID() + "|" +
                employee.getPassword() + "|" +
                employee.getName() + "|" +
                employee.getSex() + "|" +
                employee.getPhoneNumber() + "|" +
                employee.getDepartment() + "|" +
                employee.getEnteringDate() + "\n";
    }

    public long lastOfIndex() {
        if (employeeList.size() > 0) {
            return employeeList.getLast().getEmployeeID();
        }
        return -1;
    }


    @Override
    public boolean add(Employee employee) {
        writeEmployeeToFile(employee);
        return this.employeeList.add(employee);
    }

    @Override
    public boolean delete(long employeeID) {
        for (Employee employee : this.employeeList) {
            if (employee.getEmployeeID() == employeeID) {
                this.employeeList.remove(employee);

                clearFileContent();
                writeEmployeeListToFile(employeeList);
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee get(long employeeID) {
        for (Employee employee : this.employeeList) {
            if (employee.getEmployeeID() == employeeID) return employee;
        }
        return null;
    }

    @Override
    public Employee get(String id, String password) {
        for (Employee employee : this.employeeList) {
            if (employee.getID().equals(id) && employee.getPassword().equals(password)) return employee;
        }
        return null;
    }

    @Override
    public boolean update(long employeeID, Employee employee) {
        for (Employee prevEmployee : this.employeeList) {
            if (prevEmployee.getEmployeeID() == employeeID) {
                prevEmployee.setDepartment(employee.getDepartment());
                prevEmployee.setName(employee.getName());
                prevEmployee.setPhoneNumber(employee.getPhoneNumber());

                clearFileContent();
                writeEmployeeListToFile(employeeList);
                return true;
            }
        }
        return false;
    }
}