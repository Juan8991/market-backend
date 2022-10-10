package com.shopjr.market.persistence.mapper;

import com.shopjr.market.domain.Product;
import com.shopjr.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring", uses= {ICategoryMapper.class})
public interface IProductMapper {
    @Mappings({
            @Mapping(source = "idProducto",target = "productId"),
            @Mapping(source = "idCategoria",target = "categoryId"),
            @Mapping(source = "nombre",target = "name"),
            @Mapping(source = "precioVenta",target = "price"),
            @Mapping(source = "cantidadStock",target = "stock"),
            @Mapping(source = "estado",target = "active"),
            @Mapping(source = "categoria",target = "category")

    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    /**
     * Probar...
     * @param
     * @return
     */
    //Optional<List<Product>> toOptionalProducts(Optional<List<Producto>> productos);

    @InheritInverseConfiguration
    @Mapping(target="codigoBarras",ignore = true)
    Producto toProducto(Product product);
}
