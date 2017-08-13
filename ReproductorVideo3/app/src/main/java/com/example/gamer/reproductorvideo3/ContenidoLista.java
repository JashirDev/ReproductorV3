package com.example.gamer.reproductorvideo3;

/**
 * Created by zigotero on 09/08/17.
 */

public class ContenidoLista {
    private String nombre;
    private String imagenurl;
    private String urlvideo;

    public ContenidoLista(String nombre, String imagenurl, String urlvideo) {
        this.nombre = nombre;
        this.imagenurl = imagenurl;
        this.urlvideo=urlvideo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenurl() {
        return imagenurl;
    }

    public void setImagenurl(String imagenurl) {
        this.imagenurl = imagenurl;
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public void setUrlvideo(String urlvideo) {
        this.urlvideo = urlvideo;
    }
}
