
package com.proyecto.Proyecto.PApiKardex.service;

import com.proyecto.Proyecto.PApiKardex.dao.KardexRepository;
import com.proyecto.Proyecto.PApiKardex.dto.Transaction;
import com.proyecto.Proyecto.PApiKardex.dto.TransactionResponse;
import com.proyecto.Proyecto.PApiKardex.entity.Almacen;
import com.proyecto.Proyecto.PApiKardex.entity.Kardex;
import com.proyecto.Proyecto.PApiKardex.entity.KardexItem;
import com.proyecto.Proyecto.PApiKardex.entity.Producto;
import com.proyecto.Proyecto.PApiKardex.entity.Usuario;
import com.proyecto.Proyecto.PApiKardex.exception.EntityNotFoundException;
import com.proyecto.Proyecto.PApiKardex.file.FileMethods;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KardexServiceImpl implements KardexService{

    @Autowired
    private KardexRepository kardexRepository;
    
    @Autowired
    private RestClientUsuario restClientUsuario;
    
    @Autowired
    private RestClientProducto restClientProducto;
    
    @Autowired
    private RestClientAlmacen restClientAlmacen;
  
    private FileMethods fileMethods = new FileMethods();
    
    @Override
    @Transactional
    public TransactionResponse placeKardex(Transaction transaction) {
        Kardex kardex = transaction.getKardex();
        transaction.getKardexItems().forEach(i->{kardex.addKardexItem(i);});
        Usuario usuario = restClientUsuario.findByCodigoUsu(kardex.getCodigoUsu());
        Almacen almacen = restClientAlmacen.findByCodigoAlm(kardex.getCodigoAlm());
        
        List<Producto> productos = new ArrayList<>();
        transaction.getKardexItems().forEach(i->{productos.add(restClientProducto.findByProductoSK(i.getProductoSK()));});
        
        kardexRepository.save(kardex);
        
        //Colocar metodos para sacar usuario, almacen kardexItems
        return new TransactionResponse(kardex, usuario, almacen, productos);
    }
    
    @Override
    @Transactional
    public List<TransactionResponse> findAll() {
    List<Kardex> ordenes = kardexRepository.findAll(); // Obtener todas las órdenes (kardexes)
    List<TransactionResponse> ordenesConvertidas = new ArrayList<>();

    // Iteramos sobre cada Kardex y lo convertimos en un TransactionResponse
    for (Kardex kardex : ordenes) {
        // Obtener el usuario asociado al Kardex
        Usuario usuario = restClientUsuario.findByCodigoUsu(kardex.getCodigoUsu());

        // Obtener el almacén asociado al Kardex
        Almacen almacen = restClientAlmacen.findByCodigoAlm(kardex.getCodigoAlm());

        // Obtener la lista de productos asociados a los KardexItems
        List<Producto> productos = new ArrayList<>();
        for (KardexItem item : kardex.getKardexItems()) {
            productos.add(restClientProducto.findByProductoSK(item.getProductoSK()));
        }

        // Crear una nueva instancia de TransactionResponse
        TransactionResponse transactionResponse = new TransactionResponse(kardex, usuario, almacen, productos);

        // Añadir la respuesta convertida a la lista final
        ordenesConvertidas.add(transactionResponse);
    }

    return ordenesConvertidas; // Devolver la lista de TransactionResponse
}

    @Override
    @Transactional
    public TransactionResponse findById(Long kardexId) {
    var message = "Pedido con id =" + kardexId.toString() + "" + "Not Found";
    Kardex kardex = kardexRepository.findById(kardexId).orElseThrow(()-> new EntityNotFoundException(message));

    Usuario usuario = restClientUsuario.findByCodigoUsu(kardex.getCodigoUsu());
    Almacen almacen = restClientAlmacen.findByCodigoAlm(kardex.getCodigoAlm());

    List<Producto> productos = new ArrayList<>();
    for (KardexItem item : kardex.getKardexItems()) {
        productos.add(restClientProducto.findByProductoSK(item.getProductoSK()));
    }

    return new TransactionResponse(kardex, usuario, almacen, productos);
}

    @Override
    @Transactional
    public TransactionResponse findByCodigoUsu(String codigoUsu) {
    Kardex kardex = kardexRepository.findByCodigoUsu(codigoUsu);

    Usuario usuario = restClientUsuario.findByCodigoUsu(kardex.getCodigoUsu());
    Almacen almacen = restClientAlmacen.findByCodigoAlm(kardex.getCodigoAlm());

    List<Producto> productos = new ArrayList<>();
    for (KardexItem item : kardex.getKardexItems()) {
        productos.add(restClientProducto.findByProductoSK(item.getProductoSK()));
    }

    return new TransactionResponse(kardex, usuario, almacen, productos);
}

    @Override
    @Transactional
    public List<TransactionResponse> findByNumeroDocumentoUsu(String numeroDocUsu) {
    List<Kardex> ordenes = kardexRepository.findAll(); // Obtener todas las órdenes de kardexes
    List<TransactionResponse> ordenesFiltradas = new ArrayList<>();

    for (Kardex kardex : ordenes) {
        Usuario usuario = restClientUsuario.findByCodigoUsu(kardex.getCodigoUsu());

        if (usuario != null && usuario.getNumeroDocUsu().equals(numeroDocUsu)) {
            Almacen almacen = restClientAlmacen.findByCodigoAlm(kardex.getCodigoAlm());

            List<Producto> productos = new ArrayList<>();
            for (KardexItem item : kardex.getKardexItems()) {
                productos.add(restClientProducto.findByProductoSK(item.getProductoSK()));
            }

            TransactionResponse transactionResponse = new TransactionResponse(kardex, usuario, almacen, productos);
            ordenesFiltradas.add(transactionResponse);
        }
    }

    return ordenesFiltradas;
}
    
    @Override
    public String createJsonFileFromFindAll() {
    List<TransactionResponse> transactionResponses = findAll();
    fileMethods.createFolder("ReportesGenerales");
    String jsonFilePath = fileMethods.createFile("Reporte.txt", transactionResponses);
    return jsonFilePath;
}
    
    
@Override
@Transactional
public TransactionResponse updateKardexStatus(Long kardexId,String codigoAlm, String estadoPedido, String estadoRetiro, Map<Long, String> itemEstados) {
    // Obtener la orden existente por su ID
    var message = "Pedido con id =" + kardexId.toString() + "" + "Not Found";
    Kardex kardexExistente = kardexRepository.findById(kardexId)
            .orElseThrow(()-> new EntityNotFoundException(message));

    // Actualizar los valores de estadoPedido y estadoRetiro en Kardex
    kardexExistente.setCodigoAlm(codigoAlm);
    kardexExistente.setEstadoPedido(estadoPedido);
    kardexExistente.setEstadoRetiro(estadoRetiro);

    // Actualizar el estado de cada KardexItem
    kardexExistente.getKardexItems().forEach(item -> {
        // Verificar si el item está en el map de itemEstados
        if (itemEstados.containsKey(item.getKardexItemid())) {
            // Actualizar el estado del item
            item.setEstadoItem(itemEstados.get(item.getKardexItemid()));
        }
    });

    // Guardar los cambios en la base de datos
    kardexRepository.save(kardexExistente);

    // Obtener el usuario y almacén relacionado para retornar en la respuesta
    Usuario usuario = restClientUsuario.findByCodigoUsu(kardexExistente.getCodigoUsu());
    Almacen almacen = restClientAlmacen.findByCodigoAlm(kardexExistente.getCodigoAlm());

    // No es necesario actualizar productos, solo devolvemos los actuales
    List<Producto> productos = kardexExistente.getKardexItems().stream()
            .map(item -> restClientProducto.findByProductoSK(item.getProductoSK()))
            .collect(Collectors.toList());

    // Retornar la respuesta de la transacción actualizada
    return new TransactionResponse(kardexExistente, usuario, almacen, productos);
}


    
    
    
}
