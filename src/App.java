public class App {
    public static void main(String[] args) throws Exception {
        GestionDueños gestion = new GestionDueños();
        formDueños form = new formDueños(gestion);
        form.setVisible(true);
        
    }
}
