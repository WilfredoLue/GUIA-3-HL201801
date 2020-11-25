package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class sql {
    public int auto_increm(String sql){
        int id =1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conectar bd = new Conectar();
        try{
                ps = bd.getConnection().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    id = rs.getInt(1)+1;
                }
        }catch(Exception ex){
            System.out.println("idmaximo"+ex.getMessage());
            id = 1;
        }
        finally{
            try{
                ps.close();
                rs.close();
                bd.desconectar();
            }catch(Exception ex){}
        }
        return id;
    }
    public static void main(String []args){
        sql s = new sql();
        int a = s.auto_increm("SELCT MAX(idEmpleado) FROM empleado;");
        System.out.println(a);
    }
}

