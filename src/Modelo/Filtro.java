/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {



    private int id;
    private String nombre;
    private int AñoEstreno;
    private String Director;  
    private String PaisProcedencia;
    private String Clasificacion;
    private boolean Exibicion;
    
    public Filtro(){
    }

    public Filtro(int id, String nombre, int AñoEstreno, String Director, String PaisProcedencia, boolean Exibicion) {
        this.id = id;
        this.nombre = nombre;
        this.AñoEstreno = AñoEstreno;
        this.Director = Director;
        this.PaisProcedencia = PaisProcedencia;
        this.Exibicion = Exibicion;
    }

    public Filtro(String nombre, int AñoEstreno, String Director, String PaisProcedencia, String Clasificacion, boolean Exibicion) {
        this.nombre = nombre;
        this.AñoEstreno = AñoEstreno;
        this.Director = Director;
        this.PaisProcedencia = PaisProcedencia;
        this.Clasificacion = Clasificacion;
        this.Exibicion = Exibicion;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getAñoEstreno() {
        return AñoEstreno;
    }

    public void setAñoEstreno(int AñoEstreno) {
        this.AñoEstreno = AñoEstreno;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getPaisProcedencia() {
        return PaisProcedencia;
    }

    public void setPaisProcedencia(String PaisProcedencia) {
        this.PaisProcedencia = PaisProcedencia;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String Clasificacion) {
        this.Clasificacion = Clasificacion;
    }

    public boolean isExibicion() {
        return Exibicion;
    }

    public void setExibicion(boolean Exibicion) {
        this.Exibicion = Exibicion;
    }
    
    
}


