package dtr;

import java.util.ArrayList;
import java.util.List;

public class Series {
  private String id;
  private String source;
  private String sourceId;
  private List<DataPoint> dataPoints = new ArrayList<DataPoint>();
  
  public Series(String id, String source, String sourceId) {
    this.id = id;
    this.source = source;
    this.sourceId = sourceId;
  }

  public String getId() {
    return id;
  }

  public String getSource() {
    return source;
  }

  public String getSourceId() {
    return sourceId;
  }

  public List<DataPoint> getDataPoints() {
    return dataPoints;
  }
}
