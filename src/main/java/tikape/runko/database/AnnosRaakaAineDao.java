package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.AnnosRaakaAine;
import tikape.runko.domain.RaakaAine;

public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer> {

    private Database database;

    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

    public void uusi(AnnosRaakaAine ara) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO AnnosRaakaAine VALUES(?, ?, ?, ?, ?)");
        stmt.setInt(1, ara.getRaaka_aine_id());
        stmt.setInt(2, ara.getAnnos_id());
        stmt.setInt(3, 4);
        stmt.setString(4, ara.getMaara());
        stmt.setString(5, ara.getOhje());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public List<AnnosRaakaAine> listaaKaikki() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> raineet = new ArrayList<>();
        while (rs.next()) {
            Integer jarjestys = rs.getInt("jarjestys");
            String maara = rs.getString("maara");
            String ohje = rs.getString("ohje");
            Integer raaka_aine_id = rs.getInt("raaka_aine_id");
            Integer annos_id = rs.getInt("annos_id");
            raineet.add(new AnnosRaakaAine(jarjestys, maara, ohje, raaka_aine_id, annos_id));
        }

        rs.close();
        stmt.close();
        connection.close();

        return raineet;
    }

    public List<AnnosRaakaAine> raakisLista(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine WHERE annos_id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> raineet = new ArrayList<>();
        while (rs.next()) {
            Integer jarjestys = rs.getInt("jarjestys");
            String maara = rs.getString("maara");
            String ohje = rs.getString("ohje");
            Integer raaka_aine_id = rs.getInt("raaka_aine_id");
            Integer annos_id = rs.getInt("annos_id");
            raineet.add(new AnnosRaakaAine(jarjestys, maara, ohje, raaka_aine_id, annos_id));
        }

        rs.close();
        stmt.close();
        connection.close();

        return raineet;
    }

    public AnnosRaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine WHERE annos_id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer jarjestys = rs.getInt("jarjestys");
        String maara = rs.getString("maara");
        String ohje = rs.getString("ohje");
        Integer raaka_aine_id = rs.getInt("raaka_aine_id");
        Integer annos_id = rs.getInt("annos_id");

        AnnosRaakaAine o = new AnnosRaakaAine(jarjestys, maara, ohje, raaka_aine_id, annos_id);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    public int getId(String nimi) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos WHERE nimi = '" + nimi + "'");
        ResultSet rs = stmt.executeQuery();
        int palaute = rs.getInt("id");
        stmt.close();
        rs.close();
        connection.close();
        return palaute;
    }

}
