package dtr;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dtr.SeriesDAO;

public class DTImport {
  private static Log log = LogFactory.getFactory().getInstance(DTImport.class);
  private static HelpFormatter formatter = new HelpFormatter();
  private SeriesDAO dao = new SeriesDAO();
      
  public DTImport(String[] args) throws Exception {
    loadProperties();
    Options options = new Options();
    Option opt = new Option("i", "id", true, "the series id");
    opt.setArgName("id");
    opt.setRequired(true);
    options.addOption(opt);
    opt = new Option("c", "class", true, "the HTML class containing price data");
    opt.setArgName("class");
    opt.setRequired(true);
    options.addOption(opt);
    opt = new Option("u", "url", true, "the url");
    opt.setArgName("url");
    opt.setRequired(true);
    options.addOption(opt);
    
    try {
      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = parser.parse(options, args);
      DataPoint dataPoint = extractDataPoint(cmd.getOptionValue("url"), cmd.getOptionValue("class"));
      dao.insert(cmd.getOptionValue("id"), dataPoint);
    } catch(ParseException e) {
      formatter.printHelp("DTImport", options);
      System.exit(1);
    }
  }
  
  private DataPoint extractDataPoint(String url, String cssClass) throws Exception {
    Document doc = Jsoup.connect(url).get();
    Elements elements = doc.select(cssClass);
    if (elements.size() == 0) {
      throw new Exception("no element found for class " + cssClass);
    }
    if (elements.size() > 1) {
      throw new Exception("multiple elements found for class " + cssClass);
    }
    Element element = elements.get(0);
    String price = element.text();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < price.length(); i++) {
      if (price.charAt(i) == '.' || (price.charAt(i) >= '0' && price.charAt(i) <= '9')) {
        sb.append(price.charAt(i));
      }
    }
    Float value = Float.parseFloat(sb.toString());
    return new DataPoint(new Date(), value);
  }
  
  private void loadProperties() {
    ClassLoader cl = ClassLoader.getSystemClassLoader();  
    try {
      InputStream is = cl.getResourceAsStream("dtr.properties");
      if (is == null) {
        log.error("cannot load properties");
        System.exit(1);
      }
      System.getProperties().load(is);    
    } catch(IOException e) {
      log.error("cannot load properties", e);
      System.exit(1);
    }
  }
  
  public static void main(String[] args) {
    try {
      new DTImport(args);
    } catch(Exception e) {
      log.error(e);
    }
  }
}
