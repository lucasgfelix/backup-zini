
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programas;

import classes.DataAtual;
import java.util.Date;
import javax.swing.JOptionPane;
import classes.ConexaoAzure;
import classes.Leitura;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author Pichau
 */
public class ConfiguraçãoAlerta extends javax.swing.JFrame {

    DataAtual data = new DataAtual(new Date());

    Integer alerta = Integer.parseInt(data.getMinuto()) + 1;
    Integer agora;

    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            DataAtual data2 = new DataAtual(new Date());
            agora = Integer.parseInt(data2.getMinuto());
            System.out.println(alerta);
            System.out.println(agora);
            System.out.println("Ta testando");
            if (alerta.equals(agora)) {
                try {
//                    System.out.println("FOIIIIIIIIIIIIIIIIIIIIIIIIII");
                    alerta += 1;
                    if (alerta.equals(60)) {
                        alerta = 0;
                    }

                    mensagem();

                } catch (AWTException e) {
                }
            }
        }
    };
    final long Seconds = (1000 * 3);

    ConexaoAzure conectar = new ConexaoAzure();

    String dia = data.getDiaSemana();
    Date date;
    Integer fkUsuario = 2;
    //  Timer timer = new Timer();

// isso é 15 minutos
    /**
     * Creates new form ConfiguraçãoAlerta
     */
    public ConfiguraçãoAlerta() {
        initComponents();
        //    timer.scheduleAtFixedRate(task, 0, Seconds);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        primeira = new javax.swing.JRadioButton();
        segunda = new javax.swing.JRadioButton();
        quarta = new javax.swing.JRadioButton();
        terceira = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        primeiraAlerta = new javax.swing.JRadioButton();
        segundaAlerta = new javax.swing.JRadioButton();
        terceiraAlerta = new javax.swing.JRadioButton();
        quartaAlerta = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Copyright ©2020  ini. Designed By   INI DEVELOPMENT"); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(230, 510, 390, 50);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(252, 197, 119));
        jLabel2.setText("Z");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(468, 527, 10, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(252, 197, 119));
        jLabel3.setText("Z");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(344, 527, 10, 17);

        jPanel2.setBackground(new java.awt.Color(252, 197, 119));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 110, 750, 1);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setLayout(null);

        primeira.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(primeira);
        primeira.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        primeira.setForeground(new java.awt.Color(255, 255, 255));
        primeira.setText("Segunda a sexta");
        primeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primeiraActionPerformed(evt);
            }
        });
        jPanel3.add(primeira);
        primeira.setBounds(28, 111, 191, 29);

        segunda.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(segunda);
        segunda.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        segunda.setForeground(new java.awt.Color(255, 255, 255));
        segunda.setText("Segunda, quarta e sexta");
        segunda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaActionPerformed(evt);
            }
        });
        jPanel3.add(segunda);
        segunda.setBounds(28, 162, 281, 29);

        quarta.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(quarta);
        quarta.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        quarta.setForeground(new java.awt.Color(255, 255, 255));
        quarta.setText("Todos os dias");
        quarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaActionPerformed(evt);
            }
        });
        jPanel3.add(quarta);
        quarta.setBounds(28, 264, 169, 29);

        terceira.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(terceira);
        terceira.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        terceira.setForeground(new java.awt.Color(255, 255, 255));
        terceira.setText("Terça, quinta e sabado");
        terceira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terceiraActionPerformed(evt);
            }
        });
        jPanel3.add(terceira);
        terceira.setBounds(28, 213, 269, 29);

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rotina");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(84, 68, 74, 25);

        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Alertas de pausa ");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(340, 68, 190, 25);

        primeiraAlerta.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup2.add(primeiraAlerta);
        primeiraAlerta.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        primeiraAlerta.setForeground(new java.awt.Color(255, 255, 255));
        primeiraAlerta.setText("A cada 15 min");
        primeiraAlerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primeiraAlertaActionPerformed(evt);
            }
        });
        jPanel3.add(primeiraAlerta);
        primeiraAlerta.setBounds(340, 111, 169, 29);

        segundaAlerta.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup2.add(segundaAlerta);
        segundaAlerta.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        segundaAlerta.setForeground(new java.awt.Color(255, 255, 255));
        segundaAlerta.setText("A cada 20 min");
        segundaAlerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundaAlertaActionPerformed(evt);
            }
        });
        jPanel3.add(segundaAlerta);
        segundaAlerta.setBounds(340, 162, 169, 29);

        terceiraAlerta.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup2.add(terceiraAlerta);
        terceiraAlerta.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        terceiraAlerta.setForeground(new java.awt.Color(255, 255, 255));
        terceiraAlerta.setText("A cada 25 min");
        terceiraAlerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terceiraAlertaActionPerformed(evt);
            }
        });
        jPanel3.add(terceiraAlerta);
        terceiraAlerta.setBounds(340, 213, 169, 29);

        quartaAlerta.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup2.add(quartaAlerta);
        quartaAlerta.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        quartaAlerta.setForeground(new java.awt.Color(255, 255, 255));
        quartaAlerta.setText("A cada 30 min");
        quartaAlerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartaAlertaActionPerformed(evt);
            }
        });
        jPanel3.add(quartaAlerta);
        quartaAlerta.setBounds(340, 264, 169, 29);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Definir alertas");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(190, 0, 170, 50);

        button1.setBackground(new java.awt.Color(252, 197, 119));
        button1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        button1.setLabel("Salvar alterações");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel3.add(button1);
        button1.setBounds(190, 320, 200, 33);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(130, 140, 560, 380);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Configurações de alertas");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(260, 40, 320, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 790, 560);

        setSize(new java.awt.Dimension(803, 601));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void primeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primeiraActionPerformed


    }//GEN-LAST:event_primeiraActionPerformed

    private void terceiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceiraActionPerformed

    }//GEN-LAST:event_terceiraActionPerformed


    private void primeiraAlertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primeiraAlertaActionPerformed

    }//GEN-LAST:event_primeiraAlertaActionPerformed

    private void segundaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaActionPerformed

    }//GEN-LAST:event_segundaActionPerformed

    private void quartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaActionPerformed

    }//GEN-LAST:event_quartaActionPerformed

    private void segundaAlertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundaAlertaActionPerformed

    }//GEN-LAST:event_segundaAlertaActionPerformed

    private void terceiraAlertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceiraAlertaActionPerformed

    }//GEN-LAST:event_terceiraAlertaActionPerformed

    private void quartaAlertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartaAlertaActionPerformed

    }//GEN-LAST:event_quartaAlertaActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if (primeira.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {

                try {

                    ConexaoAzure.jdbcTemplate.update(
                            "update tbRotina set diasUteisRotina = ?, fkUsuario  = ? ",
                            "Segunda-a-Sexta", fkUsuario);
                    JOptionPane.showMessageDialog(null, "Segunda a Sexta !!! Selecionada");

                } catch (HeadlessException | DataAccessException e) {
                    Logs log = new Logs();
                    try {
                        log.gravarLog(String.format("Não foi possível atualizar os dados de rotina para o banco de dados, erro gerado:\n\n"
                                + "%s", e.toString()));
                    } catch (IOException ex) {
                        Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {

                try {

                    ConexaoAzure.jdbcTemplate.update(
                            "Insert into tbRotina "
                            + "( diasUteisRotina, fkUsuario ) values (?,?)",
                            "Segunda-a-Sexta", fkUsuario);

                    JOptionPane.showMessageDialog(null, "Segunda a Sexta !!! Selecionada");

                } catch (HeadlessException | DataAccessException e) {
                    Logs log = new Logs();
                    try {
                        log.gravarLog(String.format("Não foi possível realizar inserção dos dados de rotina para o banco de dados, erro gerado:\n\n"
                                + "%s", e.toString()));
                    } catch (IOException ex) {
                        Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        } else if (segunda.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {
                ConexaoAzure.jdbcTemplate.update(
                        "update tbRotina set diasUteisRotina = ?, fkUsuario  = ? ",
                        "Segunda-Quarta-Sexta", fkUsuario);
                JOptionPane.showMessageDialog(null, "Segunda,Quarta e Sexta !!! Selecionada");

            } else {
                ConexaoAzure.jdbcTemplate.update(
                        "Insert into tbRotina "
                        + "( diasUteisRotina, fkUsuario ) values (?,?)",
                        "Segunda-Quarta-Sexta", fkUsuario);
                JOptionPane.showMessageDialog(null, "Segunda,Quarta e Sexta !!! Selecionada");
            }

        } else if (terceira.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {
                ConexaoAzure.jdbcTemplate.update(
                        "update tbRotina set diasUteisRotina = ?, fkUsuario  = ? ",
                        "Terça-Quinta-Sabado", fkUsuario);
                JOptionPane.showMessageDialog(null, "Terça, Quinta e Sabado!!! Selecionada");

            } else {
                ConexaoAzure.jdbcTemplate.update(
                        "Insert into tbRotina "
                        + "( diasUteisRotina, fkUsuario ) values (?,?)",
                        "Terça-Quinta-Sabado", fkUsuario);
            }

            JOptionPane.showMessageDialog(null, "Terça Quinta e Sabado !!! Selecionada");
        } else if (quarta.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {
                ConexaoAzure.jdbcTemplate.update(
                        "update tbRotina set diasUteisRotina = ?, fkUsuario  = ? ",
                        "Todos-os-dias", fkUsuario);
                JOptionPane.showMessageDialog(null, "Todos os dias !!! Selecionada");

            } else {
                ConexaoAzure.jdbcTemplate.update(
                        "Insert into tbRotina "
                        + "( diasUteisRotina, fkUsuario ) values (?,?)",
                        "Todos-os-dias", fkUsuario);
            }

            JOptionPane.showMessageDialog(null, "Todos os dias !!! Selecionada");
        }

        if (primeiraAlerta.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {
                ConexaoAzure.jdbcTemplate.update(
                        "update tbRotina set tempoDePausa = ?, fkUsuario  = ? ",
                        "15", fkUsuario);
                JOptionPane.showMessageDialog(null, "A cada 15 minutos !!! Selecionada");

            } else {
                ConexaoAzure.jdbcTemplate.update(
                        "Insert into tbRotina "
                        + "( tempoDePausa, fkUsuario ) values (?,?)",
                        "15", fkUsuario);
                JOptionPane.showMessageDialog(null, "A cada 15 minutos !!! Selecionada");
            }

        } else if (segundaAlerta.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {
                ConexaoAzure.jdbcTemplate.update(
                        "update tbRotina set tempoDePausa = ?, fkUsuario  = ? ",
                        "20", fkUsuario);
                JOptionPane.showMessageDialog(null, "A cada 20 minutos !!! Selecionada");

            } else {
                ConexaoAzure.jdbcTemplate.update(
                        "Insert into tbRotina "
                        + "( tempoDePausa, fkUsuario ) values (?,?)",
                        "20", fkUsuario);
                JOptionPane.showMessageDialog(null, "A cada 20 minutos !!! Selecionada");
            }

        } else if (terceiraAlerta.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {
                ConexaoAzure.jdbcTemplate.update(
                        "update tbRotina set tempoDePausa = ?, fkUsuario  = ? ",
                        "25", fkUsuario);
                JOptionPane.showMessageDialog(null, "A cada 25 minutos !!! Selecionada");

            } else {
                ConexaoAzure.jdbcTemplate.update(
                        "Insert into tbRotina "
                        + "( tempoDePausa, fkUsuario ) values (?,?)",
                        "25", fkUsuario);
                JOptionPane.showMessageDialog(null, "A cada 25 minutos !!! Selecionada");
            }

            JOptionPane.showMessageDialog(null, "A cada 25 minutos Selecionados");
        } else if (quartaAlerta.isSelected()) {
            List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select *  from tbRotina where fkUsuario = ?",
                    new BeanPropertyRowMapper(Leitura.class),
                    fkUsuario);

            if (!consultaCadastro.isEmpty()) {
                ConexaoAzure.jdbcTemplate.update(
                        "update tbRotina set diasUteisRotina = ?, fkUsuario  = ? ",
                        "30", fkUsuario);
                JOptionPane.showMessageDialog(null, "A cada 30 minutos !!! Selecionada");

            } else {
                ConexaoAzure.jdbcTemplate.update(
                        "Insert into tbRotina "
                        + "( diasUteisRotina, fkUsuario ) values (?,?)",
                        "30", fkUsuario);
            }

            JOptionPane.showMessageDialog(null, "A cada 30 minutos!!! Selecionada");
        }


    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws AWTException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfiguraçãoAlerta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguraçãoAlerta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguraçãoAlerta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguraçãoAlerta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguraçãoAlerta().setVisible(true);

            }

        });
        if (SystemTray.isSupported()) {
            ConfiguraçãoAlerta td = new ConfiguraçãoAlerta();
            td.timer();

        } else {
            System.out.println("System not supported");
        }

    }

    public void timer() {

        this.timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, Seconds);

    }

    public void mensagem() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Hello World",
                "Isso é um teste da zini", TrayIcon.MessageType.INFO);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton primeira;
    private javax.swing.JRadioButton primeiraAlerta;
    private javax.swing.JRadioButton quarta;
    private javax.swing.JRadioButton quartaAlerta;
    private javax.swing.JRadioButton segunda;
    private javax.swing.JRadioButton segundaAlerta;
    private javax.swing.JRadioButton terceira;
    private javax.swing.JRadioButton terceiraAlerta;
    // End of variables declaration//GEN-END:variables
}
