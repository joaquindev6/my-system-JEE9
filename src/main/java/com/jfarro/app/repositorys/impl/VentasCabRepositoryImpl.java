package com.jfarro.app.repositorys.impl;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.Repository;
import com.jfarro.app.models.User;
import com.jfarro.app.models.VentasCab;
import com.jfarro.app.repositorys.VentasCabRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VentasCabRepositoryImpl implements VentasCabRepository {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @Override
    public List<VentasCab> findAll() throws SQLException {
        List<VentasCab> ventas = new ArrayList<>();
        try (Statement stm = this.conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery("SELECT * FROM tbl_ventas_cab")) {
                while (rs.next()) {
                    ventas.add(getVentasCab(rs));
                }
            }
        }
        return ventas;
    }

    @Override
    public VentasCab findById(Long id) throws SQLException {
        VentasCab ventas = null;
        try (PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM tbl_ventas_cab WHERE id = ?")) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    ventas = getVentasCab(rs);
                }
            }
        }
        return ventas;
    }

    @Override
    public Long save(VentasCab ventasCab) throws SQLException {
        Long id = 0L;
        String sql;
        if (ventasCab.getId() != null && ventasCab.getId() > 0) {
            sql = "UPDATE tbl_ventas_cab SET id_usuario=?, fecha=?, prectotal=? WHERE id=?";
        } else {
            sql = "INSERT INTO tbl_ventas_cab(id_usuario, fecha, prectotal) VALUES (?,?,?)";
        }
        try (PreparedStatement pstm = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setLong(1, ventasCab.getUser().getId());
            pstm.setString(2, ventasCab.getDateTime());
            pstm.setDouble(3, ventasCab.getPriceTotal());
            if (ventasCab.getId() != null && ventasCab.getId() > 0) {
                pstm.setLong(4, ventasCab.getId());
            }
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        }
        return id;
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement pstm = this.conn.prepareStatement("DELETE FROM tbl_ventas_cab WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        }
    }

    private VentasCab getVentasCab(ResultSet rs) throws SQLException {
        VentasCab ventas = new VentasCab();
        ventas.setId(rs.getLong("id"));
        ventas.setDateTime(rs.getString("fecha"));
        ventas.setPriceTotal(rs.getDouble("prectotal"));

        User user = new User();
        user.setId(rs.getLong("id_usuario"));
        ventas.setUser(user);

        return ventas;
    }
}
