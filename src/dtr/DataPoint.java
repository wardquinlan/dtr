package dtr;

import java.time.LocalDateTime;

public class DataPoint {
  private LocalDateTime dateTime;
  private float value;
  
  public DataPoint(LocalDateTime dateTime, Float value) {
    this.dateTime = dateTime;
    this.value = value;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }
  
  public float getValue() {
    return value;
  }
}
