package com.proyecto.Proyecto.PApiProduct.service;

import com.proyecto.Proyecto.PApiProduct.entity.Producto;
import com.proyecto.Proyecto.PApiProduct.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.proyecto.Proyecto.PApiProduct.dao.ProductoRepository;
import com.proyecto.Proyecto.PApiProduct.dto.ProductoDto;
import com.proyecto.Proyecto.PApiProduct.entity.Category;
import com.proyecto.Proyecto.PApiProduct.entity.Proveedor;

@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoRepository productRepository;
    
    @Autowired
    private RestClientCategory restClientCategory;

    @Autowired
    private RestClientProveedor restClientProveedor;
    
    @Override
    public List<Producto> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Producto> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public ProductoDto findById(Long id) {
       var message = "Product with id =" + id.toString() + "" + "Not Found";
       Producto producto = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(message));
       Category category = restClientCategory.findByCodigoCat(producto.getCodigoCat());
       Proveedor proveedor = restClientProveedor.findByCodigoProv(producto.getCodigoProv());
       
       ProductoDto productoDto = new ProductoDto();
       productoDto.setId(producto.getId());
       productoDto.setNombreProd(producto.getNombreProd());
       productoDto.setProductoSK(producto.getProductoSK());
       productoDto.setUnidadMedida(producto.getUnidadMedida());
       productoDto.setCategory(category);
       productoDto.setProveedor(proveedor);
       
       return productoDto;
    }

    @Override
    public ProductoDto findByNombreProd(String nombreProd) {
         var message = "Product with name = " + nombreProd.toString() + "" + "Not Found";
         Producto producto = productRepository.findByNombreProd(nombreProd);
         Category category = restClientCategory.findByCodigoCat(producto.getCodigoCat());
         Proveedor proveedor = restClientProveedor.findByCodigoProv(producto.getCodigoProv());
         ProductoDto productoDto = new ProductoDto();
         
        productoDto.setId(producto.getId());
        productoDto.setNombreProd(producto.getNombreProd());
        productoDto.setProductoSK(producto.getProductoSK());
        productoDto.setUnidadMedida(producto.getUnidadMedida());
        productoDto.setCategory(category);
        productoDto.setProveedor(proveedor);
         
         return productoDto;
    }
    
    @Override
    public ProductoDto findByProductoSK(String productoSK) {
         var message = "Product with SK = " + productoSK.toString() + "" + "Not Found";
         
         Producto producto = productRepository.findByProductoSK(productoSK);
         Category category = restClientCategory.findByCodigoCat(producto.getCodigoCat());
         Proveedor proveedor = restClientProveedor.findByCodigoProv(producto.getCodigoProv());
         ProductoDto productoDto = new ProductoDto();
         
        productoDto.setId(producto.getId());
        productoDto.setNombreProd(producto.getNombreProd());
        productoDto.setProductoSK(producto.getProductoSK());
        productoDto.setUnidadMedida(producto.getUnidadMedida());
        productoDto.setCategory(category);
        productoDto.setProveedor(proveedor);
         
         return productoDto;
    }

    @Override
    public Producto add(Producto product) {
       return productRepository.save(product);
    }

    @Override
    public Producto update(Producto product) {
        var productDB = productRepository.findById(product.getId()).get();
        productDB.setNombreProd(product.getNombreProd());
        productDB.setCodigoCat(product.getCodigoCat());
        productDB.setCodigoProv(product.getCodigoProv());
        productDB.setProductoSK(product.getProductoSK());
        productDB.setUnidadMedida(product.getUnidadMedida());
        return productRepository.save(productDB);
    }

    @Override
    public void delete(Long id) {
        var productDB = productRepository.findById(id).get();
        productRepository.delete(productDB);
    }
    
}
