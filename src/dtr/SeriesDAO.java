package dtr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SeriesDAO {
  private static Log log = LogFactory.getFactory().getInstance(SeriesDAO.class);
  
  public SeriesDAO() throws Exception {
    Class.forName("org.postgresql.Driver");
  }

  private Connection open() throws SQLException {
    log.info("attempting to connect to database with host=" + System.getProperty("dtr.db.host") + 
        ", name=" + System.getProperty("dtr.db.name") +
        ", username=" + System.getProperty("dtr.db.username"));
    String url = "jdbc:postgresql://" + System.getProperty("dtr.db.host") +  
     "/" + System.getProperty("dtr.db.name") + 
     "?user=" + System.getProperty("dtr.db.username") +
     "&password=" + System.getProperty("dtr.db.password");
    return DriverManager.getConnection(url);
  }
  
  private void close(Connection c) {
    try {
      if (c != null) {
        c.close();
      }
    } catch(SQLException e) {
      log.error("close failed", e);
    }
  }
  
  public void insert(String id, DataPoint dataPoint) throws SQLException {
    Connection c = null;
    try {
      c = open();
      PreparedStatement ps = c.prepareStatement("insert into series_data(series_id, ts, val) values(?, ?, ?)");
      ps.setString(1, id);
      ps.setTimestamp(2, new Timestamp(dataPoint.getDate().getTime()));
      ps.setFloat(3, dataPoint.getValue());
      ps.executeUpdate();
    } finally {
      close(c);
    }
  }
  
  public Series findSeriesById(String id) {
    return null;
  }
}
