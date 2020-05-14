package dtr;

import java.time.LocalDate;

public class Series {
  private String id;
  private LocalDate date;
  private int idx;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public LocalDate getDate() {
    return date;
  }
  public void setDate(LocalDate date) {
    this.date = date;
  }
  public int getIdx() {
    return idx;
  }
  public void setIdx(int idx) {
    this.idx = idx;
  }
}
