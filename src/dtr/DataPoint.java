package dtr;

import java.time.LocalDateTime;

public class DataPoint {
  private String id;
  private LocalDateTime dateTime;
  private float value;
  
  public DataPoint(String id, Float value) {
    this.id = id;
    this.value = value;
    this.dateTime = LocalDateTime.now();
  }

  public String getId() {
    return id;
  }
  
  public LocalDateTime getDateTime() {
    return dateTime;
  }
  
  public float getValue() {
    return value;
  }
}
