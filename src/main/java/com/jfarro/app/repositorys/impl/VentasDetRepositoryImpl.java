package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.Repository;
import com.jfarro.app.models.Product;
import com.jfarro.app.models.VentasCab;
import com.jfarro.app.models.VentasDet;
import com.jfarro.app.repositorys.VentasDetRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VentasDetRepositoryImpl implements VentasDetRepository {

    @Inject
    private Connection conn;

    @Override
    public List<VentasDet> findAll() throws SQLException {
        List<VentasDet> ventas = new ArrayList<>();
        try (Statement stm = this.conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery("SELECT * FROM tbl_ventas_det")) {
                while (rs.next()) {
                    ventas.add((getVentasDet(rs)));
                }
            }
        }
        return ventas;
    }

    @Override
    public VentasDet findById(Long id) throws SQLException {
        VentasDet ventas = null;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM tbl_ventas_det WHERE id_venta_cab = ?")) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    ventas = getVentasDet(rs);
                }
            }
        }
        return ventas;
    }

    @Override
    public Long save(VentasDet ventasDet) throws SQLException {
        Long idVentaCab = 0L;
        String sql = "INSERT INTO tbl_ventas_det(id_venta_cab, id_producto, cantidad, precio) VALUES (?,?,?,?)";
        try (PreparedStatement pstm = this.conn.prepareStatement(sql)) {
            pstm.setLong(1, ventasDet.getVentasCab().getId());
            pstm.setLong(2, ventasDet.getProduct().getId());
            pstm.setLong(3, ventasDet.getAmount());
            pstm.setDouble(4, ventasDet.getPrice());
            pstm.executeUpdate();
        }
        return idVentaCab;
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("DELETE FROM tbl_ventas_det WHERE id_venta_cab = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        }
    }

    private VentasDet getVentasDet(ResultSet rs) throws SQLException {
        VentasDet ventas = new VentasDet();
        ventas.setAmount(rs.getInt("cantidad"));
        ventas.setPrice(rs.getDouble("precio"));

        VentasCab cabecera = new VentasCab();
        cabecera.setId(rs.getLong("id_venta_cab"));
        ventas.setVentasCab(cabecera);

        Product product = new Product();
        product.setId(rs.getLong("id_producto"));
        ventas.setProduct(product);

        return ventas;
    }
}
