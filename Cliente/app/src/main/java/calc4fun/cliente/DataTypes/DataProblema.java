package calc4fun.cliente.DataTypes;


public class DataProblema {

    private Integer id;
    private String descripcion;
    private String contenido;


    public DataProblema() {
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getContenido() {
        return contenido;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
