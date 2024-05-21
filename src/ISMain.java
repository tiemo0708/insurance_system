import customer.Customer;
import customer.CustomerListImpl;
import employee.EmployeeListImpl;
import product.ProductListImpl;

import java.rmi.RemoteException;
import java.io.*;
import java.util.*;

public class ISMain {
    static BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));

    private static CustomerListImpl customerList;
    private static EmployeeListImpl employeeList;
    private static ProductListImpl productList;


    private static void setData() {
        customerList = new CustomerListImpl();
        employeeList = new EmployeeListImpl();
        productList = new ProductListImpl();
    }


    public static void main(String[] args) {
        setData();
        try {
            while (true) {
                printMenu();
                String sChoice = objReader.readLine().trim();
                switch (sChoice) {
                    case "1":
                        printCustomerInitMenu();
                        break;
                    case "2":
                        printEmployeeInitMenu();
                        break;
                    case "3":
                        System.out.println("test: product 정보 불러오기");
                        showList(productList.get());
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invalid Choice !!!");
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printMenu() {
        System.out.println("********************** MENU ***********************");
        System.out.println("1. 고객");
        System.out.println("2. 직원");
        System.out.println("x. Exit");
    }

    static boolean customerMenuFlag = true;

    private static void printCustomerInitMenu() {
        try {
            while (customerMenuFlag) {
                System.out.println("********************** Customer MENU ***********************");
                System.out.println("1. 로그인");
                System.out.println("2. 회원 가입");
                System.out.println("x. Exit");

                String sChoice = objReader.readLine().trim();
                switch (sChoice) {
                    case "1":
                        loginCustomer();
                        break;
                    case "2":
                        registerCustomer();
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invalid Choice !!!");
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 고객 로그인
     * 로그인한 고객이 member인 경우에 printMemberMenu() 호출
     * member가 아닌 경우에 printCustomerMenu() 호출
     */
    private static void loginCustomer() {
        try {
            System.out.println("ID를 입력해주세요");
            String id = objReader.readLine().trim();
            System.out.println("Password를 입력해주세요");
            String password = objReader.readLine().trim();

            if (customerList.get(id, password) != null) {
                customerMenuFlag = false;
                if (customerList.get(id, password).isMember())
                    printMemberMenu();
                else printCustomerMenu();
            } else {
                System.out.println("로그인 실패");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 고객 등록
     * 예외 처리 해야 함
     */
    private static void registerCustomer() {
        try {
            System.out.println("ID를 입력해주세요");
            String id = objReader.readLine().trim();
            while (customerList.get(id) != null) {
                System.out.println("사용 불가능한 ID입니다. 다른 ID를 입력해주세요");
                id = objReader.readLine().trim();
            }

            System.out.println("Password를 입력해주세요");
            String password = objReader.readLine().trim();

            System.out.println("이름을 입력해주세요");
            String name = objReader.readLine().trim();

            System.out.println("성별을 선택해주세요");
            System.out.println("(1) 여자");
            System.out.println("(2) 남자");
            String sex = objReader.readLine().trim().equals("1") ? "F" : "M";

            System.out.println("전화번호를 입력해주세요");
            String phoneNumber = objReader.readLine().trim();

            System.out.println("생년월일을 입력해주세요");
            String birthDay = objReader.readLine().trim();

            customerList.add(new Customer(customerList.lastOfIndex() + 1,
                    id, password, name, sex, phoneNumber,
                    birthDay, false));

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void printCustomerMenu() {
        System.out.println("********************** Customer MENU ***********************");
        System.out.println("1. 상담 요청");
        System.out.println("2. 보험 가입");
        System.out.println("3. 가입삼사 확인");
        System.out.println("x. Exit");
    }

    private static void printMemberMenu() {
        System.out.println("********************** Member MENU ***********************");
        System.out.println("1. 사고 접수");
        System.out.println("2. 상담 요청");
        System.out.println("3. 내 보험 확인");
        System.out.println("4. 보험 가입");
        System.out.println("5. 가입심사 확인");
        System.out.println("6. 보험료 납부");
        System.out.println("7. 대출");
        System.out.println("x. Exit");
    }


    static boolean employeeMenuFlag = true;

    private static void printEmployeeInitMenu() {
        try {
            while (employeeMenuFlag) {
                System.out.println("********************** Employee MENU ***********************");
                System.out.println("1. 로그인");
                System.out.println("x. Exit");

                String sChoice = objReader.readLine().trim();
                switch (sChoice) {
                    case "1":
                        loginEmployee();
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invalid Choice !!!");
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 직원 로그인
     */
    private static void loginEmployee() {
        try {
            System.out.println("ID를 입력해주세요");
            String id = objReader.readLine().trim();
            System.out.println("Password를 입력해주세요");
            String password = objReader.readLine().trim();

            if (employeeList.get(id, password) != null) {
                employeeMenuFlag = false;
                printEmployeeMenu();
            } else {
                System.out.println("로그인 실패");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void printEmployeeMenu() {
        System.out.println("********************** Employee MENU ***********************");
        System.out.println("1. 사고 접수 관리"); // > 사고접수 확인, 보험금 산정
        System.out.println("2. 보험 설계"); // > 보험 설계서, 설계서 조회
        System.out.println("3. 고객 서비스"); // > 보험가입신청 내역, 상담요청 내역
        System.out.println("x. Exit");
    }


    private static void showList(ArrayList<?> dataList) {
        String list = "";
        for (int i = 0; i < dataList.size(); i++) {
            list += dataList.get(i) + "\n";
        }
        System.out.println(list);
    }
}

