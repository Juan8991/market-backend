package com.shopjr.market.persistence;

import com.shopjr.market.persistence.crud.IProductoCrudRepository;
import com.shopjr.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoCrudRepository {

    private IProductoCrudRepository repository;

    public ProductoCrudRepository(IProductoCrudRepository repository) {
        this.repository = repository;
    }

    public List<Producto> getProducts() {
        return (List<Producto>) repository.findAll();
    }

    public List<Producto> getProductsByCategoryOrderByNombre(Long idCategoria){
        return repository.finfByIdCategoriaOrderByNombreDsc(idCategoria);
    }
    public List<Producto> getProductsByCategoryOrderByPrecioVenta(Long idCategoria){
        return repository.finfByIdCategoriaOrderByPrecioVentaAsc(idCategoria);
    }
    public Optional<List<Producto>> getProductsByCantidadStockLessThan(Integer cantidadStock, Boolean estado){
        return repository.finfByCantidadStockLessThanAndEstado(cantidadStock, estado);
    }
    public Optional<Producto> getProductoById(Long id){
        return repository.findById(id);
    }
    public Producto saveProducto(Producto producto){
        return repository.save(producto);
    }
    public void deleteProducto(Long idProducto){
        repository.deleteById(idProducto);
    }

}
