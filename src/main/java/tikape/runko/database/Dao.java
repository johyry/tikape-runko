package tikape.runko.database;

import java.sql.*;
import java.util.*;

public interface Dao<T, K> {
    List<T> listaaKaikki() throws SQLException;
}
