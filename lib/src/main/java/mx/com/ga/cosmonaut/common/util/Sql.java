package mx.com.ga.cosmonaut.common.util;

import mx.com.ga.cosmonaut.common.dto.SqlDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Sql {

    private Sql() {
        super();
    }

    public static ResultSet ejecucionQuery(SqlDto sqlDto, PreparedStatement statement) throws SQLException {
        int i = 1;
        for (Object dato : sqlDto.getListaDatos()) {
            if (dato instanceof Integer){
                statement.setInt(i, (Integer) dato);
            }else if(dato instanceof String){
                statement.setString(i, (String) dato);
            }else if(dato instanceof Long){
                statement.setLong(i, (Long) dato);
            }else if(dato instanceof Date){
                java.sql.Date fecha = new java.sql.Date(((Date) dato).getTime());
                statement.setDate(i, fecha);
            }else if(dato instanceof Boolean){
                statement.setBoolean(i, (Boolean) dato);
            }else if(dato instanceof Timestamp){
                statement.setTimestamp(i, (Timestamp) dato);
            }
            i++;
        }
        return statement.executeQuery();
    }

    public static String eliminarUltimoAnd(String query) {
        int i;
        query = query.trim();
        i = query.lastIndexOf(Constantes.ESPACIO);
        if (i != -1) {
            query = query.substring(0, i);
        } else {
            query = "";
        }
        return query;
    }
}
