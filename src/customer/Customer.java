package customer;


public class Customer {
    private long customerID;
    private String id;
    private String password;
    private String name;
    private String sex;
    private String phoneNumber;
    private String birthDay;
    private boolean isMember;

    public Customer() {
        super();
    }

    public Customer(String id, String password, String name, String sex, String phoneNumber, boolean isMember) {
        super();
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.isMember = isMember;
    }


    public Customer(long customerID, String id, String password, String name, String sex, String phoneNumber, String birthDay, boolean isMember) {
        this.customerID = customerID;
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.isMember = isMember;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isMember() { return isMember; }

    public void setIsMember(boolean member) { isMember = member; }


    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", ID='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", isMember='" + isMember + '\'' +
                '}';
    }

}