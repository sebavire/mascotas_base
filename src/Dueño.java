public class Dueño{
    private String nombre;
    private int cedula;
    private String telefono;

    public Dueño(String nombre, int cedula, String telefono){
        this.nombre=nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public int getCedula(){
        return cedula;
    }
    public void setCedula(int cedula){
        this.cedula=cedula;
    }

    public String getTelefono(){
        return telefono;
    }
    public void settelefono(String telefono){
        this.telefono=telefono;
    }
}