/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmdhmdsaxparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author tristanday
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button loadButton;
    
    @FXML
    private Button clearButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private TextArea text;
    
    @FXML
    private void handleLoad(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handle = new DefaultHandler() {
            public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                text.appendText("Start Element:" + qName);
                
            }
            public void endElement(String uri, String localName,String qName) throws SAXException {
                text.appendText("End Element:" + qName);
            }
 
            public void characters(char ch[], int start, int length) throws SAXException {
                text.appendText("" + new String(ch, start, length));
            }
        };
          try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(text.getScene().getWindow());            
            FileInputStream is = new FileInputStream(file);
            SAXParser parser = factory.newSAXParser();         
            parser.parse(is, handle);
          } catch (IOException | ParserConfigurationException | SAXException error) {
                throw (error);
            }
    }
    
    @FXML
    private void handleClear(ActionEvent event) {
        text.setText(" ");
    }
    
    @FXML
    private void handleExit(ActionEvent event) {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
