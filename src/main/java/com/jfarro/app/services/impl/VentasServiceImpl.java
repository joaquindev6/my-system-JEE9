package com.jfarro.app.services.impl;

import com.jfarro.app.annotations.ServiceMysql;
import com.jfarro.app.exceptions.ServiceJdbcException;
import com.jfarro.app.models.VentasCab;
import com.jfarro.app.models.VentasDet;
import com.jfarro.app.repositorys.VentasCabRepository;
import com.jfarro.app.repositorys.VentasDetRepository;
import com.jfarro.app.services.VentasService;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ServiceMysql
public class VentasServiceImpl implements VentasService {

    @Inject
    private VentasCabRepository ventasCabRepository;

    @Inject
    private VentasDetRepository ventasDetRepository;

    @Override
    public List<VentasDet> findAllVentasDet() throws SQLException {
        try {
            return this.ventasDetRepository.findAll();
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public VentasDet findByIdVentasDet(Long id) throws SQLException {
        try {
            return this.ventasDetRepository.findById(id);
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Long saveVentasDet(VentasDet t) throws SQLException {
        try {
            return this.ventasDetRepository.save(t);
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void deleteVentasDet(Long id) throws SQLException {
        try {
            this.ventasDetRepository.delete(id);
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public List<VentasCab> findAllVentasCab() throws SQLException {
        try {
            return this.ventasCabRepository.findAll();
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public VentasCab findByIdVentasCab(Long id) throws SQLException {
        try {
            return this.ventasCabRepository.findById(id);
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Long saveVentasCab(VentasCab t) throws SQLException {
        try {
            return this.ventasCabRepository.save(t);
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void deleteVentasCab(Long id) throws SQLException {
        try {
            this.ventasCabRepository.delete(id);
        } catch (ServiceJdbcException ex) {
            throw new ServiceJdbcException(ex.getMessage(), ex.getCause());
        }
    }
}
