package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.ProductDao;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDao productDao = new ProductDao();

    private ProductValidator productValidator = new ProductValidator();

    public List<Product> getAllProducts() throws FailedToGetProductsException {

        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProductsException();
        }

//        double totalPriceOfProduct = 0;

        /*
        for(int i = 0; i < productList.size(); i++){

            totalPriceOfProduct += productList.get(i).getPrice();

        }
        */

        /*
        for(Product product : productList){

            totalPriceOfProduct += product.getPrice();

        }
        */

        /*
        Iterator<Product> productIterator = productList.iterator();

        while (productIterator.hasNext()){
            Product product = productIterator.next();
            totalPriceOfProduct += product.getPrice();
        }
        */

        /*
        Iterator<Product> productIterator = productList.iterator();

        do{
            Product product = productIterator.next();
            totalPriceOfProduct += product.getPrice();
        }while (productIterator.hasNext());
        */

        /*
        totalPriceOfProduct = productList.stream().mapToDouble(product -> product.getPrice()).sum();
        */

        //System.out.println("Total price of all products: £" + totalPriceOfProduct);

        /*
        double totalPriceOfCheapProduct = 0;

        double totalPriceOfExpensiveProduct = 0;

        for(Product product : productList){

            if (product.getPrice()<100){
                totalPriceOfCheapProduct+=product.getPrice();
            } else {
                totalPriceOfExpensiveProduct += product.getPrice();
            }

        }

        System.out.println("Total price of cheap products: £" + totalPriceOfCheapProduct);
        System.out.println(("Total price of expensive products: £" + totalPriceOfExpensiveProduct));

        */

        /*
        for(Product product : productList){

            switch (product.getName()) {
                case("Cheese"):
                    System.out.println("This is the cheese price: £" + product.getPrice());
                    break;
                case ("CHOCOLATE"):
                    System.out.println("This is the chocolate price: £" + product.getPrice());
                    break;
                default:
                    System.out.println("This is the other price: " + product.getPrice());
            }

        }
        */

        //List<Integer> intList = Arrays.asList(1, 2, 2, 4, 5);

        /*
        intList.stream().forEach(System.out::println);
        */

        /*
        Set<Integer> intSet = new HashSet<>(intList);

        intSet.stream().forEach(System.out::println);
        */


        /*
        Collections.sort(productList);

        productList.stream().forEach(System.out::println);
        */

        /*
        System.out.println(Collections.min(productList));

        System.out.println(Collections.max(productList));
         */

        //productList.stream().filter(product -> product.getPrice() > 10).forEach(System.out::println);

        List<Product> cheapProducts = productList.stream().filter(product -> product.getPrice() < 10).collect(Collectors.toList());

        cheapProducts.stream().forEach(System.out::println);

        return productList;

    }

    public Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try {
            Product product = productDao.getProductById(id);

            if (product == null) {
                throw new ProductDoesNotExistException();
            }

            return product;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProductsException();
        }
    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException{
        try{
            String validation = productValidator.isValidProduct(product);
            if (validation!=null){
                throw new InvalidProductException(validation);
            }
            int id = productDao.createProduct(product);
            if (id == -1){
                throw new FailedToCreateProductException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateProductException();
        }
    }

    public void updateProduct(int id, ProductRequest product) throws InvalidProductException,
            ProductDoesNotExistException, FailedToUpdateProductException {

        try {
            String validation = productValidator.isValidProduct(product);

            if (validation!=null){
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductById(id);

            if (productToUpdate == null){
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);

        } catch (SQLException e){

            System.err.println(e.getMessage());

            throw new FailedToUpdateProductException();

        }

    }

    public void deleteProduct(int id) throws ProductDoesNotExistException, FailedToDeleteProductException {

        try{
            Product productToDelete = productDao.getProductById(id);

            if(productToDelete == null){

                throw new ProductDoesNotExistException();

            }

            productDao.deleteProduct(id);

        }catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToDeleteProductException();
        }


    }

}
