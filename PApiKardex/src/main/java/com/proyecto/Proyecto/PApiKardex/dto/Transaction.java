package com.proyecto.Proyecto.PApiKardex.dto;

import com.proyecto.Proyecto.PApiKardex.entity.Kardex;
import com.proyecto.Proyecto.PApiKardex.entity.KardexItem;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Kardex kardex;
    private List<KardexItem> kardexItems;
}
