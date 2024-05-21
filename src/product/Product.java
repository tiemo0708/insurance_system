package product;

public class Product {

    private long productID;
    private String category; // 유형 (자동차 1, 운전자 2)
    private String name; //  상품명
    private String description; // 상품설명
    private String insuranceFee; // 보험료(월)
    private String period; // 보험기간(개월)

    public Product(long productID, String category, String name, String description, String insuranceFee, String period) {
        this.productID = productID;
        this.category = category;
        this.name = name;
        this.description = description;
        this.insuranceFee = insuranceFee;
        this.period = period;
    }

    public Product() {
        super();
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(String insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void design() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", category(자차-1, 운전자-2)='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", insuranceFee(월)='" + insuranceFee + '\'' +
                ", period(개월)='" + period + '\'' +
                '}';
    }
}
