package programas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JAILSON
 */
public class Logs {

    public void gravarLog(String textoLog) throws IOException {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        FileWriter arq = new FileWriter("log/log_zini.txt", true);
//       FileWriter arq = new FileWriter("C:\Users\DOM\Documents\Bandtec Grupo 8 KeepCode\KeepCode-Grupo-08\log.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println(String.format("%s %s %s \n", data, hora, textoLog));

        arq.close();

    }

    void gravarLog(Exception e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
