package dtr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SeriesDAO {
  private static Log log = LogFactory.getFactory().getInstance(SeriesDAO.class);
  private String url;
  
  public SeriesDAO() throws Exception {
    try
    {
      Class.forName("org.postgresql.Driver");
      log.info("attempting to connect to database with host=" + System.getProperty("dtr.db.host") + 
               ", name=" + System.getProperty("dtr.db.name") +
               ", username=" + System.getProperty("dtr.db.username"));
      url = "jdbc:postgresql://" + System.getProperty("dtr.db.host") +  
            "/" + System.getProperty("dtr.db.name") + 
            "?user=" + System.getProperty("dtr.db.username") +
            "&password=" + System.getProperty("dtr.db.password");
      Connection c = DriverManager.getConnection(url);  
      PreparedStatement ps = c.prepareStatement("select * from series");
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
      }
      c.close();
    }
    catch(Exception e)
    {
      throw new Exception(e);
    }
  }

  private Connection open() throws SQLException {
    return DriverManager.getConnection(url);
  }
  
  private void close(Connection c) {
    try {
      c.close();
    } catch(SQLException e) {
      log.error("close failed", e);
    }
  }
  
  public void addDataPoint(DataPoint dataPoint) {
  }
  
  public Series findSeriesById(String id) {
    return null;
  }
}
