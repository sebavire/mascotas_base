import java.util.ArrayList;

public class GestionDueños{

    private ArrayList<Dueño> dueños;

    public GestionDueños() {
        this.dueños = new ArrayList<Dueño>();
    }

    public ArrayList<Dueño> getDueños() {
        return dueños;
    }

    public void guardarDueño(Dueño dueño) {
        dueños.add(dueño); 
    }

    public void eliminarDueño(int posicion) {
        dueños.remove(posicion); 
    }

    public void modificarDueño(int posicion, Dueño dueño) {
        dueños.set(posicion, dueño); 
    }
    

}