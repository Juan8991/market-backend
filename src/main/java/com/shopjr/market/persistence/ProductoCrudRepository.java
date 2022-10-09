package com.shopjr.market.persistence;

import com.shopjr.market.domain.Product;
import com.shopjr.market.domain.repository.IProductRepository;
import com.shopjr.market.persistence.crud.IProductoCrudRepository;
import com.shopjr.market.persistence.entity.Producto;
import com.shopjr.market.persistence.mapper.IProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoCrudRepository implements IProductRepository {

    @Autowired
    private IProductoCrudRepository repository;
    @Autowired
    private IProductMapper  productMapper;

    /**
     * Esta en terminos de la persistencia.
     * @return lista de productos
     */
    public List<Producto> getProducts() {

        return (List<Producto>) repository.findAll();
    }

    /**
     * Orientamos el producto a termino del dominio Product mediante
     * la logica de Data mapper.
     * @return lista de products
     */
    @Override
    public List<Product> getAll() {
        List<Producto> listaDeProductos=(List<Producto>) repository.findAll();
        return productMapper.toProducts(listaDeProductos);
    }

    /**
     * Permite obtener productos por su categoria ordenados por nombre A-Z,
     * esta en terminos de la persistencia.
     * @param idCategoria
     * @return lista de productos
     */
    public List<Producto> getProductsByCategoryOrderByNombre(Long idCategoria){
        return repository.findByIdCategoriaOrderByNombreDesc(idCategoria);
    }

    /**
     * Obtiene productos por la categoria y los ordena por nombre A-Z, ademas lo mapea
     * en terminos del dominio.
     * @param categoryId
     * @return lista de products
     */
    @Override
    public Optional<List<Product>> getByCategory(Long categoryId) {
        List<Producto> listaDeProductosPorCategoria=repository.findByIdCategoriaOrderByNombreDesc(categoryId);
        return Optional.of(productMapper.toProducts(listaDeProductosPorCategoria));
    }


    public List<Producto> getProductsByCategoryOrderByPrecioVenta(Long idCategoria){
        return repository.findByIdCategoriaOrderByPrecioVentaAsc(idCategoria);
    }

    /**
     * Obtiene productos por la cantidad de stock menor a,
     * lo hace en terminos de la persistencia.
     * @param cantidadStock
     * @param estado
     * @return lista de productos
     */
    public Optional<List<Producto>> getProductsByCantidadStockLessThan(Integer cantidadStock, Boolean estado){
        return repository.findByCantidadStockLessThanAndEstado(cantidadStock, estado);
    }

    /**
     * Obtiene productos por la cantidad de stock menor a,
     * luego lo mapea a terminos del dominio
     * @param quantity
     * @return lista de products
     */
    @Override
    public Optional<List<Product>> getScaseProducts(Integer quantity) {
        /*Posible funcion-------------------
        Optional<List<Producto>> listaDeProductosPorStock=repository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productMapper.toOptionalProducts(listaDeProductosPorStock);*/
        Optional<List<Producto>> listaDeProductosPorStock=repository.findByCantidadStockLessThanAndEstado(quantity, true);
        return listaDeProductosPorStock.map(producto->productMapper.toProducts(producto));
    }

    /**
     * Obtiene producto por Id, en terminos de la persistencia
     * @param id
     * @return producto
     */
    public Optional<Producto> getProductoById(Long id){
        return repository.findById(id);
    }

    /**
     * obtiene produto por id y luego ,o mapea a terminos del dominio
     * @param productId
     * @return product
     */
    @Override
    public Optional<Product> getProduct(Long productId) {
        Optional<Producto> productoPorId=repository.findById(productId);
        return productoPorId.map(element->productMapper.toProduct(element));
    }

    /**
     *
     * @param producto
     * @return
     */
    public Producto saveProducto(Producto producto){
        return repository.save(producto);
    }

    /**
     * Guarda un product pero como el repositorio guarda producto, no product
     * se hace el mapeo a producto y luego de nuevo para devolver el product
     * @param product
     * @return
     */
    @Override
    public Product save(Product product) {
        Producto producto=productMapper.toProducto(product);
        return productMapper.toProduct(repository.save(producto));
    }

    @Override
    public void delete(Long idProducto){
        repository.deleteById(idProducto);
    }


}
