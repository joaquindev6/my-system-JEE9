package com.jfarro.app.services;

import com.jfarro.app.models.VentasCab;
import com.jfarro.app.models.VentasDet;

import java.sql.SQLException;
import java.util.List;

public interface VentasService {
    List<VentasDet> findAllVentasDet() throws SQLException;
    VentasDet findByIdVentasDet(Long id) throws SQLException;
    Long saveVentasDet(VentasDet t) throws SQLException;
    void deleteVentasDet(Long id) throws SQLException;

    List<VentasCab> findAllVentasCab() throws SQLException;
    VentasCab findByIdVentasCab(Long id) throws SQLException;
    Long saveVentasCab(VentasCab t) throws SQLException;
    void deleteVentasCab(Long id) throws SQLException;
}
