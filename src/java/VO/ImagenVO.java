package VO;

import java.io.InputStream;


public class ImagenVO {
    int codigoimg;
    String nombreimg;
    InputStream archivoimg;
    private byte[] archivoimg2;
    
    public ImagenVO(int id, String name, byte[] archivo){
        this.codigoimg = id;
        this.nombreimg = name;
        this.archivoimg2 = archivo;
    }
    public ImagenVO(){
    
}

    public int getCodigoimg() {
        return codigoimg;
    }

    public void setCodigoimg(int codigoimg) {
        this.codigoimg = codigoimg;
    }

    public String getNombreimg() {
        return nombreimg;
    }

    public void setNombreimg(String nombreimg) {
        this.nombreimg = nombreimg;
    }

    public InputStream getArchivoimg() {
        return archivoimg;
    }

    public void setArchivoimg(InputStream archivoimg) {
        this.archivoimg = archivoimg;
    }
    
    public byte[] getArchivoimg2() {
        return archivoimg2;
    }

    public void setArchivoimg2(byte[] archivoimg2) {
        this.archivoimg2 = archivoimg2;
    }
    
}
