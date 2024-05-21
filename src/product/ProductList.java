package product;


import java.util.*;

public interface ProductList {

    /**
     *
     * @param productID
     */
    public boolean add(Product product);

    public boolean delete(long productID);

    public Product get(long productID);

    public ArrayList<Product> get();

    public boolean update(long productID, Product product);
}
