package SERVLET;
import Conexion.sql;
import DAO.ImagenDAO;
import VO.ImagenVO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(name = "ControllerImagen", urlPatterns = {"/ControllerImagen"})
public class ControllerImagen extends HttpServlet {

    public static final String LIST_STUDENT= "/Pagina1.jsp";
    public static final String INSERT_OR_EDIT = "/Pagina2.jsp";
    
    String estado = null;
    ImagenDAO imagendao;
    int id_pdf= -1;
    
    public ControllerImagen(){
        imagendao = new ImagenDAO();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        
        if(action.equalsIgnoreCase("delete")){
            forward = LIST_STUDENT;
            int studentId = Integer.parseInt(request.getParameter("id"));
            imagendao.Eliminar_ImagenVO(studentId);
            
        }
        if(action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int studentIdM = Integer.parseInt(request.getParameter("id"));
            id_pdf = studentIdM;
            ImagenVO imagenvo = imagendao.getImagenVOById(studentIdM);
            request.setAttribute("row", imagenvo);
            boolean boo = false;
            
            
            if(imagenvo.getArchivoimg2()!= null){
                boo = true;
            }
            request.setAttribute("row2", boo);
            estado = "edit";
            
        }else if(action.equalsIgnoreCase("insert")){
            forward = INSERT_OR_EDIT;
            estado = "insert";
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ImagenVO imagenvo = new ImagenVO();
        sql auto = new sql();
        int nuevoid = auto.auto_increm("select max(codigoimg) from imagen;");
        
        try{
            String name = request.getParameter("txtName");
            imagenvo.setNombreimg(name);
        }catch(Exception ex){
            System.out.println("nombre: "+ex.getMessage());
        }
        
        InputStream inputstream = null;
        
        try{
           Part filepart = request.getPart("fichero");
           if(filepart.getSize()>0){
               System.out.println(filepart.getName());
               System.out.println(filepart.getSize());
               System.out.println(filepart.getContentType());
               inputstream=filepart.getInputStream();
           }
        }catch(Exception ex){
            System.out.println("archivo"+ex.getMessage());
        }
        
       try{
          if(estado.equalsIgnoreCase("insert")){
            imagenvo.setCodigoimg(nuevoid);
            if(inputstream != null){
                imagenvo.setArchivoimg(inputstream);
            }
            imagendao.Agregar_ImagenVO(imagenvo);
        }else{
              imagenvo.setCodigoimg(id_pdf);
            imagenvo.setCodigoimg(nuevoid);
            if(inputstream != null){
                imagenvo.setArchivoimg(inputstream);
                imagendao.Modificar_ImagenVO(imagenvo);
            }else{
            imagendao.Modificar_ImagenVO2(imagenvo); 
          }
          }
          
        } catch (Exception ex) {
            System.out.println("textos" + ex.getMessage());
        }
       
       RequestDispatcher view = request.getRequestDispatcher("/Pagina1.jsp");
        view.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
