package com.shopjr.market.persistence;

import com.shopjr.market.domain.Purchase;
import com.shopjr.market.domain.repository.IPurchaseRepository;
import com.shopjr.market.persistence.crud.ICompraCrudRepository;
import com.shopjr.market.persistence.entity.Compra;
import com.shopjr.market.persistence.mapper.IPurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements IPurchaseRepository {

    @Autowired
    private ICompraCrudRepository compraCrudRepository;

    @Autowired
    private IPurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
