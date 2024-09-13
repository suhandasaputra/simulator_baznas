/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asac.iso;

/**
 *
 * @author herry.suganda simelekete weleh weleh
 */
import com.asac.parameter.RuleNameParamater;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;
  
public class PackagerFactory {
    private static Logger log = Logger.getLogger(PackagerFactory.class);
    public static ISOPackager getPackager() {
        ISOPackager packager = null;
        {
            InputStream is = null;
            try {
                File f = new File(RuleNameParamater.PACKAGE_ISO_FILENAME);
//                System.out.println("f : " + f);
                is = new FileInputStream(f);
//             is = ClassLoader.getSystemResourceAsStream("/iso87ascii.xml");
//             System.out.println("-=======-=-=-=-=-=-=-=-=-=-= path : " + System.getProperty("user.dir"));
//                System.out.println("-=-=-=-==-=-=-== is " + is);
                packager = new GenericPackager(is);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                log.error(ex.getMessage());
            } catch (ISOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                log.error(ex.getMessage());
                }
            }
        }
         return packager;
     }
 }