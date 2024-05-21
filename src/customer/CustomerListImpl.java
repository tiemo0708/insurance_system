package customer;

import java.util.*;
import java.io.*;

public class CustomerListImpl implements CustomerList {
    private static final String CUSTOMER_ACCOUNT_FILE_PATH = "src/data/customer.txt";
    private static final int CUSTOMER_COLUMN_NUM = 8;
    private ArrayList<Customer> customerList;

    public CustomerListImpl() {
        this.customerList = new ArrayList<Customer>();
        loadCustomersFromFile();
    }


    /**
     * 파일(customer.txt)에서 고객 정보를 읽어와 customerList에 추가하는 메소드
     */
    private void loadCustomersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_ACCOUNT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] customerRow = line.split("\\|");
                if (customerRow.length == CUSTOMER_COLUMN_NUM) {
                    Customer customer = new Customer();
                    customer.setCustomerID(Long.parseLong(customerRow[0]));
                    customer.setID(customerRow[1]);
                    customer.setPassword(customerRow[2]);
                    customer.setName(customerRow[3]);
                    customer.setSex(customerRow[4]);
                    customer.setPhoneNumber(customerRow[5]);
                    customer.setBirthDay(customerRow[6]);
                    customer.setIsMember(customerRow[7].equals("T") ? true : false);
                    customerList.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 파일(customer.txt)에 새로운 고객 정보를 쓰는 메소드
     */
    private void writeCustomerToFile(Customer customer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_ACCOUNT_FILE_PATH, true))) {
            String customerRow = writeCustomerRowForm(customer);
            bw.write(customerRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 고객 정보의 변동사항을 반영허여 파일(customer.txt)에 고객 정보를 새로 쓰는 메소드 (delete, modify)
     */
    private void writeCustomerListToFile(ArrayList<Customer> customerList) {
        for (Customer customer : customerList) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_ACCOUNT_FILE_PATH, true))) {
                String customerRow = writeCustomerRowForm(customer);
                bw.write(customerRow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 파일(customer.txt)에 있는 데이터 모두 지우기
     */
    private void clearFileContent() {
        try (FileWriter fw = new FileWriter(CUSTOMER_ACCOUNT_FILE_PATH, false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String writeCustomerRowForm(Customer customer) {
        String isMember = customer.isMember() ? "T" : "F";
        return customer.getCustomerID() + "|" +
                customer.getID() + "|" +
                customer.getPassword() + "|" +
                customer.getName() + "|" +
                customer.getSex() + "|" +
                customer.getPhoneNumber() + "|" +
                customer.getBirthDay() + "|" +
                isMember + "\n";
    }


    public long lastOfIndex() {
        if (customerList.size() > 0) {
            return customerList.getLast().getCustomerID();
        }
        return -1;
    }

    @Override
    public boolean add(Customer customer) {
        writeCustomerToFile(customer);
        return this.customerList.add(customer);
    }

    @Override
    public boolean delete(long customerID) {
        for (Customer customer : this.customerList) {
            if (customer.getCustomerID() == customerID) {
                this.customerList.remove(customer);

                clearFileContent();
                writeCustomerListToFile(customerList);
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer get(long customerID) {
        for (Customer customer : this.customerList) {
            if (customer.getCustomerID() == customerID) return customer;
        }
        return null;
    }

    @Override
    public Customer get(String id, String password) {
        for (Customer customer : this.customerList) {
            if (customer.getID().equals(id) && customer.getPassword().equals(password)) return customer;
        }
        return null;
    }

    @Override
    public Customer get(String id) {
        for (Customer customer : this.customerList) {
            if (customer.getID().equals(id)) return customer;
        }
        return null;
    }

    @Override
    public boolean update(long customerID, Customer customer) {
        for (Customer prevCustomer : this.customerList) {
            if (prevCustomer.getCustomerID() == customerID) {
                prevCustomer.setID(customer.getID());
                prevCustomer.setPassword(customer.getPassword());
                prevCustomer.setName(customer.getName());
                prevCustomer.setPhoneNumber(customer.getPhoneNumber());
                prevCustomer.setSex(customer.getSex());
                prevCustomer.setIsMember(customer.isMember());

                clearFileContent();
                writeCustomerListToFile(customerList);
                return true;
            }
        }
        return false;
    }
}