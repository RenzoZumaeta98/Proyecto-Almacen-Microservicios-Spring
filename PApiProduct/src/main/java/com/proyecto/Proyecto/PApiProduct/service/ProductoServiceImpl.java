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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductoServiceImpl implements ProductoService {

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
    @CircuitBreaker(name = "productService", fallbackMethod = "falla")
    public ProductoDto findById(Long id) {
        var message = "Product with id =" + id.toString() + "" + "Not Found";
        Producto producto = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(message));
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
    @CircuitBreaker(name = "productService", fallbackMethod = "falla2")
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
    @CircuitBreaker(name = "productService", fallbackMethod = "falla3")
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

    public ProductoDto falla(Long id, Throwable throwable) {
        System.out.println("Servicio category no disponible. Fallback activado debido a: " + throwable.getMessage());

        // Creamos un ProductoDto con valores de error o valores predeterminados
        ProductoDto productoDtoFallback = new ProductoDto();
        productoDtoFallback.setId(-1L);  // Indica que es un valor de error
        productoDtoFallback.setNombreProd("Producto no disponible");
        productoDtoFallback.setProductoSK("N/A");
        productoDtoFallback.setUnidadMedida("N/A");

        // También puedes asignar una categoría y proveedor predeterminados o dejarlos como null
        productoDtoFallback.setCategory(null);  // O una Category de error si lo prefieres
        productoDtoFallback.setProveedor(null); // O un Proveedor de error si lo prefieres

        return productoDtoFallback;
    }
    
    public ProductoDto falla2(String nombreProd, Throwable throwable) {
        System.out.println("Servicio category no disponible. Fallback activado debido a: " + throwable.getMessage());

        // Creamos un ProductoDto con valores de error o valores predeterminados
        ProductoDto productoDtoFallback = new ProductoDto();
        productoDtoFallback.setId(-1L);  // Indica que es un valor de error
        productoDtoFallback.setNombreProd("Producto no disponible");
        productoDtoFallback.setProductoSK("N/A");
        productoDtoFallback.setUnidadMedida("N/A");

        // También puedes asignar una categoría y proveedor predeterminados o dejarlos como null
        productoDtoFallback.setCategory(null);  // O una Category de error si lo prefieres
        productoDtoFallback.setProveedor(null); // O un Proveedor de error si lo prefieres

        return productoDtoFallback;
    }
    
    public ProductoDto falla3(String productoSK, Throwable throwable) {
        System.out.println("Servicio Category no disponible. Fallback activado debido a: " + throwable.getMessage());

        // Creamos un ProductoDto con valores de error o valores predeterminados
        ProductoDto productoDtoFallback = new ProductoDto();
        productoDtoFallback.setId(-1L);  // Indica que es un valor de error
        productoDtoFallback.setNombreProd("Producto no disponible");
        productoDtoFallback.setProductoSK("N/A");
        productoDtoFallback.setUnidadMedida("N/A");

        // También puedes asignar una categoría y proveedor predeterminados o dejarlos como null
        productoDtoFallback.setCategory(null);  // O una Category de error si lo prefieres
        productoDtoFallback.setProveedor(null); // O un Proveedor de error si lo prefieres

        return productoDtoFallback;
    }

}
