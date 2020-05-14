package dtr;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dtr.SeriesDAO;

public class DTImport {
  private static Log log = LogFactory.getFactory().getInstance(DTImport.class);
  private static HelpFormatter formatter = new HelpFormatter();
      
  public DTImport(String[] args) throws Exception {
    loadProperties();
    new SeriesDAO();
    Options options = new Options();
    Option opt = new Option("u", "url", true, "url");
    opt.setArgName("url");
    opt.setRequired(true);
    options.addOption(opt);
    try {
      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = parser.parse(options, args);
      System.out.println(cmd.getOptionValue('u'));
    } catch(ParseException e) {
      formatter.printHelp("DTImport", options);
      System.exit(1);
    }
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
