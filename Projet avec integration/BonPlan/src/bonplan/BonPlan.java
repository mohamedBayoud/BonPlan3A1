/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import java.io.ByteArrayOutputStream;
import java.io.File;
import static java.lang.System.out;
import javax.imageio.stream.FileImageOutputStream;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Asus Pc
 */
public class BonPlan {
  String details="Votre Code est 2907";
        ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
        File f =new File("C:\\Users\\Asus Pc\\Desktop\\BonPlan\\hhhhh.jpg");
       /* FileImageOutputStream fos = new FileImageOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
