package com.proyecto.Proyecto.PApiKardex.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KardexStatusRequest {
    private Long kardexId;
    private String codigoAlm;
    private String estadoPedido;
    private String estadoRetiro;
    private Map<Long, String> itemEstados;
}
