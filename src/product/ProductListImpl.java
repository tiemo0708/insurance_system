package product;


import java.io.*;
import java.util.*;

public class ProductListImpl implements ProductList {

    private static final String PRODUCT_FILE_PATH = "src/data/insuranceProduct.txt";
    private static final int PRODUCT_COLUMN_NUM = 6;
    private ArrayList<Product> productList;

    public ProductListImpl() {
        this.productList = new ArrayList<Product>();
        loadProductFromFile();
    }


    /**
     * 파일(insuranceProduct.txt)에서 보험 상품 정보를 읽어와 productList에 추가하는 메소드
     */
    private void loadProductFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productRow = line.split("\\|");
                if (productRow.length == PRODUCT_COLUMN_NUM) {
                    Product product = new Product();
                    product.setProductID(Long.parseLong(productRow[0]));
                    product.setCategory(productRow[1]);
                    product.setName(productRow[2]);
                    product.setDescription(productRow[3]);
                    product.setInsuranceFee(productRow[4]);
                    product.setPeriod(productRow[5]);
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 파일(insuranceProduct.txt)에 새로운 보험 상품 정보를 쓰는 메소드
     */
    private void writeProductToFile(Product product) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE_PATH, true))) {
            String productRow = writeProductRowForm(product);
            bw.write(productRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 보험 상품 정보의 변동사항을 반영허여 파일(insuranceProduct.txt)에 보험상품 정보를 새로 쓰는 메소드 (delete, modify)
     */
    private void writeProductListToFile(ArrayList<Product> productList) {
        for (Product product : productList) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE_PATH, true))) {
                String customerRow = writeProductRowForm(product);
                bw.write(customerRow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 파일(insuranceProduct.txt)에 있는 데이터 모두 지우기
     */
    private void clearFileContent() {
        try (FileWriter fw = new FileWriter(PRODUCT_FILE_PATH, false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String writeProductRowForm(Product product){
        return product.getProductID() + "|" +
                product.getCategory() + "|" +
                product.getName() + "|" +
                product.getDescription() + "|" +
                product.getInsuranceFee() + "|" +
                product.getPeriod() + "\n";
    }

    public long lastOfIndex(){
        if(productList.size() > 0){
            return productList.getLast().getProductID();
        }
        return -1;
    }


    @Override
    public boolean add(Product product) {
        writeProductToFile(product);
        return this.productList.add(product);
    }

    @Override
    public boolean delete(long productID) {
        for (Product product : this.productList) {
            if (product.getProductID() == productID) {
                this.productList.remove(product);
                clearFileContent();
                writeProductListToFile(productList);
                return true;
            }
        }
        return false;
    }

    @Override
    public Product get(long productID) {
        for(Product product : this.productList){
            if(product.getProductID() == productID) return product;
        }
        return null;
    }

    @Override
    public ArrayList<Product> get() {
        return productList;
    }

    @Override
    public boolean update(long productID, Product product) {
        for(Product prevProduct : this.productList){
            if(prevProduct.getProductID() == productID){
                prevProduct.setPeriod(product.getPeriod());
                prevProduct.setDescription(product.getDescription());
                prevProduct.setInsuranceFee(prevProduct.getInsuranceFee());
                prevProduct.setName(product.getName());
                clearFileContent();
                writeProductListToFile(productList);
                return true;
            }
        }
        return false;
    }
}
