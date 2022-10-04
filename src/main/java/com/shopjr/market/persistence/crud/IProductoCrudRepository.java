package com.shopjr.market.persistence.crud;

import com.shopjr.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface IProductoCrudRepository extends CrudRepository<Producto,Long> {

    /**
     * Trae una lista de productos por categoria y los ordena
     * por precio de venta de manera ascendete.
     * @param idCategoria
     * @return
     */
    List<Producto> findByIdCategoriaOrderByPrecioVentaAsc(Long idCategoria);

    /**
     * Traer una lista de productos por categoria y los ordena por nombre
     * de manera descendente.
     * @param idCategoria
     * @return
     */
    List<Producto> findByIdCategoriaOrderByNombreDesc(Long idCategoria);

    /**
     * Obtiene una lista de productos de tipo Optional por cantidad de stock
     * menor a el pasado por parametro y segun el estado del produtcto.
     * @param cantidadStock
     * @param estado
     * @return
     */
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);
}
