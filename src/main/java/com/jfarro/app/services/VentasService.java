package com.jfarro.app.services;

import com.jfarro.app.models.VentasCab;
import com.jfarro.app.models.VentasDet;

import java.sql.SQLException;
import java.util.List;

public interface VentasService {
    List<VentasDet> findAllVentasDet();
    VentasDet findByIdVentasDet(Long id);
    Long saveVentasDet(VentasDet t);
    void deleteVentasDet(Long id);

    List<VentasCab> findAllVentasCab();
    VentasCab findByIdVentasCab(Long id);
    Long saveVentasCab(VentasCab t);
    void deleteVentasCab(Long id);
}
