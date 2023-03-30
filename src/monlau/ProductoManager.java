package monlau;

import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOImpl;
import monlau.model.Products;

public class ProductoManager {

    public static void main(String[] args) {
        ProductoDAO product = new ProductoDAOImpl();

        //agregar nuevo producto
        product.insert(new Products(666, "Funciona", 99.99));

        //obtener el producto con el ID = 100
        Products p = product.read(666);
        System.out.println(p);
    }
}
