package hotel.recommendation.system.databaseconfigration;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
public class getDBConnection {
	protected DataBaseConfigration databaseconfig=DataBaseConfigration.getInstance();
	protected Connection conn=databaseconfig.getConnection();
	protected PreparedStatement pstmt=databaseconfig.getPreparedStatement();
	protected Statement stmt=databaseconfig.getStatement();
	protected ResultSet rs=databaseconfig.getResultSet();
	protected CallableStatement cstmt=databaseconfig.getCallable();
}
