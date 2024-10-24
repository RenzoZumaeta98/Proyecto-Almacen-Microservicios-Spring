
package com.proyecto.Proyecto.PApiKardex.service;

import com.proyecto.Proyecto.PApiKardex.dto.Transaction;
import com.proyecto.Proyecto.PApiKardex.dto.TransactionResponse;
import java.util.List;
import java.util.Map;

public interface KardexService {
        public TransactionResponse placeKardex(Transaction transaction);
        public List<TransactionResponse> findAll();
        public TransactionResponse findById(Long Kardexid);
        public TransactionResponse findByCodigoUsu(String codigoUsu);
        public List<TransactionResponse> findByNumeroDocumentoUsu(String numeroDocUsu);
        public String createJsonFileFromFindAll();
        public TransactionResponse updateKardexStatus(Long kardexId,String codigoAlm, String estadoPedido, String estadoRetiro, Map<Long, String> itemEstados);
}
