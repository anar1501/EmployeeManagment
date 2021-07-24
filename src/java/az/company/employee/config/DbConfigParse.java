/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.company.employee.config;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author NAZIM
 */
public class DbConfigParse {

    private static final String XML_PATH = "C:\\Users\\NAZIM\\Desktop\\database_config.xml";

    public static DbConfig getDbConfig() {
        File file = new File(XML_PATH);
        try {
            JAXBContext context = JAXBContext.newInstance(DbConfig.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (DbConfig) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(DbConfigParse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
