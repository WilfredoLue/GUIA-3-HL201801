package SERVLET;

import Conexion.Conectar;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Imagen", urlPatterns = {"/Imagen"})
public class imagen extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        
        Conectar con = new Conectar();
        PreparedStatement pst = null;
        ResultSet rs = null;
        byte[] b = null;
         try{
             int id = Integer.parseInt(request.getParameter("id"));
             String sql = "SELECT archivoimg FROM imagen where codigoimg = ?";
             pst = con.getConnection().prepareStatement(sql);
             pst.setInt(1,id);
             rs = pst.executeQuery();
             while(rs.next()){
                 b = rs.getBytes(1);
             }
             InputStream bos= new ByteArrayInputStream(b);
             int tamanoInput = bos.available();
             byte [] datosIMAGEN = new byte[tamanoInput];
             bos.read(datosIMAGEN, 0, tamanoInput);
             
             response.getOutputStream().write(datosIMAGEN);
             bos.close();
             pst.close();
             rs.close();
             con.desconectar();
         }catch(Exception ex){
             System.out.println(ex.getMessage());
         }
        
    }

    
}
