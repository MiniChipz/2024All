import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database.main(new String[]{""});
        ConfettiWindow window = new ConfettiWindow();
        window.setAlwaysOnTop(true);
        window.setVisible(true);
    }
}