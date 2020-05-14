package dtr;

import java.time.LocalDate;
import java.time.LocalTime;

public class Series {
  private String id;
  private LocalDate date;
  private int idx;
  private float value;
  
  public Series(String id, Float value) {
    this.id = id;
    this.value = value;
    this.date = LocalDate.now();
    int hour = LocalTime.now().getHour();
    int min = LocalTime.now().getMinute();
    this.idx = (hour - 9) * 12 + (min / 5) - 6; 
  }
  public String getId() {
    return id;
  }
  public LocalDate getDate() {
    return date;
  }
  public int getIdx() {
    return idx;
  }
  public float getValue() {
    return value;
  }
}
