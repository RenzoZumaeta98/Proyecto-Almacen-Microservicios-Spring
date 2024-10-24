
package com.proyecto.Proyecto.PApiKardex.controller;

import com.proyecto.Proyecto.PApiKardex.dto.KardexStatusRequest;
import com.proyecto.Proyecto.PApiKardex.dto.Transaction;
import com.proyecto.Proyecto.PApiKardex.dto.TransactionResponse;
import com.proyecto.Proyecto.PApiKardex.service.KardexService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kardex")
public class KardexController {
    
    @Autowired
    private KardexService kardexService;
    
    @PostMapping("/placeKardex")
    public TransactionResponse placeOrder(@RequestBody Transaction transaction){
        return kardexService.placeKardex(transaction);
        
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<TransactionResponse>>  findAll(){
        return new ResponseEntity<>(kardexService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping("/findAllJson")
    public ResponseEntity<List<TransactionResponse>>  findAllJson(){
        kardexService.createJsonFileFromFindAll();
        return new ResponseEntity<>(kardexService.findAll(), HttpStatus.OK); 
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(kardexService.findById(id),
                HttpStatus.OK);
    }
    
    @GetMapping("/findByCodigoUsu/{codigoUsu}")
    public ResponseEntity<TransactionResponse> findByCodigoUsu(@PathVariable String codigoUsu){
        return new ResponseEntity<>(kardexService.findByCodigoUsu(codigoUsu),
                HttpStatus.OK);
    }
    
    @GetMapping("/findByNumeroDocUsu/{numeroDocUsu}")
    public ResponseEntity<List<TransactionResponse>> findByNumeroDocumentoUsu(@PathVariable String numeroDocUsu){
        return new ResponseEntity<>(kardexService.findByNumeroDocumentoUsu(numeroDocUsu),
                HttpStatus.OK);
    }
    
    @PutMapping("/updateEstados")
    public ResponseEntity<TransactionResponse> updateKardexStatus(@RequestBody KardexStatusRequest request) {
        TransactionResponse response = kardexService.updateKardexStatus(
                request.getKardexId(),
                request.getCodigoAlm(),
                request.getEstadoPedido(),
                request.getEstadoRetiro(),
                request.getItemEstados()
        );
        return ResponseEntity.ok(response);
    }
}
