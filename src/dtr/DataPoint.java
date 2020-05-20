package dtr;

import java.util.Date;

public class DataPoint {
  private Date date;
  private float value;
  
  public DataPoint(Date date, Float value) {
    this.date = date;
    this.value = value;
  }

  public Date getDate() {
    return date;
  }
  
  public float getValue() {
    return value;
  }
}
