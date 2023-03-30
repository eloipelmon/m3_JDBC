
package monlau.dao;

import monlau.model.Products;

public interface ProductoDAO {
    public void insert(Products product);
    public void update(Products product);
    public void delete(Products product);
    public Products read(Integer id);
}