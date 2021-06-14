/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programas;

import classes.AzureUser;
import classes.Componentes;
import classes.ConexaoAzure;
import classes.ConexaoConteiner;
import com.github.britooo.looca.api.core.Looca;
import javax.swing.ImageIcon;
import classes.DataAtual;
import classes.Leitura;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;

/**
 *
 * @author JAILSON e Vizer
 */
public class TelaDash extends javax.swing.JFrame {

    DataAtual data = new DataAtual(new Date());

    Integer alerta = Integer.parseInt(data.getMinuto()) + 1;
    Integer agora;

    Timer timerLeitura = new Timer();

    TimerTask taskLeitura = new TimerTask() {
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

                    try {
                        mensagem();
                    } catch (IOException ex) {
                        Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (AWTException e) {
                }
            }
        }
    };
    final long SecondsLeitura = (1000 * 3);

    //DataAtual data = new DataAtual(new Date());
    SystemInfo systeminfo = new SystemInfo();

    ConexaoAzure conectar = new ConexaoAzure();
    Double memoria;
    Double disco;
    Double cpu;

//    ConexaoConteiner conn = new ConexaoConteiner();
//    Connection connection = conn.conexaoConteiner();
    AzureUser consulta = new AzureUser();
    Looca comp = new Looca();
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            try {

                memoria = comp.getMemoria().getEmUso().doubleValue() / 1000000000;

            } catch (Exception e) {
                Logs log = new Logs();
                try {
                    log.gravarLog(String.format("Não foi possível coletar os dados da memória, erro gerado:\n\n"
                            + "%s", e.toString()));
                } catch (IOException ex) {
                    Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {

                disco = comp.getGrupoDeDiscos().getTamanhoTotal().doubleValue() / 1000000000;

            } catch (Exception e) {
                Logs log = new Logs();
                try {
                    log.gravarLog(String.format("Não foi possível coletar os dados do disco, erro gerado:\n\n"
                            + "%s", e.toString()));
                } catch (IOException ex) {
                    Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            lbHDD.setText(String.format("%.0f GB", disco));
            lbRAM.setText(String.format("%.2f %s", memoria, porcent));


            if (memoria > 50) {

                List<AzureUser> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                        "Select nomeUsuario from tbUsuario where idUsuario = ?",
                        new BeanPropertyRowMapper(AzureUser.class),
                        fkUsuario);

                for (AzureUser azureUser : consultaCadastro) {

                    try {
                        enviarMensagemAlertaSlack(azureUser.getNomeUsuario(), "Memoria", memoria);
                    } catch (IOException | InterruptedException e) {

                    }

                }
            }

        }
    };
    TimerTask taskcpu = new TimerTask() {
        @Override
        public void run() {

            try {

                cpu = comp.getProcessador().getUso();

            } catch (Exception e) {
                Logs log = new Logs();
                try {
                    log.gravarLog(String.format("Não foi possível coletar os dados do processador, erro gerado:\n\n"
                            + "%s", e.toString()));
                } catch (IOException ex) {
                    Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            lbProcessador.setText(String.format("%.1f %s", cpu, porcent));

            if (cpu > 50) {

                List<AzureUser> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                        "Select nomeUsuario from tbUsuario where idUsuario = ?",
                        new BeanPropertyRowMapper(AzureUser.class),
                        fkUsuario);

                for (AzureUser azureUser : consultaCadastro) {

                    try {
                        enviarMensagemAlertaSlack(azureUser.getNomeUsuario(), "Processador", cpu);
                    } catch (IOException | InterruptedException e) {

                    }

                }
            }
        }
    };
    final long Seconds = (3000 * 1);
    final long SecondsCpu = (2000 * 1);
    String porcent = "%";
    String SO = comp.getSistema().getSistemaOperacional();
    String ultimaverif = data.getDiaHora();
    String fabSO = comp.getSistema().getFabricante();
//    Long tempoIniciado = comp.getSistema().getTempoDeAtividade();
    String nomeComputador;
    String Memoriacad = "Memoria";
    String Processcad = "Processador";
    String Discocad = "Disco";
    Integer fkEquipe = 1;
    Integer fkUsuario = 2;
    Integer contador = 1;
    DecimalFormat deci = new DecimalFormat("####0.00");

    /**
     * Creates new form TelaDash
     */
    public TelaDash() {

        this.timerLeitura = new Timer();
        timerLeitura.schedule(taskLeitura,0, Seconds);
        this.timer = new Timer();
        initComponents();
        timer.scheduleAtFixedRate(task, 0, Seconds);
        timer.scheduleAtFixedRate(taskcpu, 0, SecondsCpu);

        nomeComputador = JOptionPane.showInputDialog("Digite o nome do seu desktop");

        List<Leitura> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                "Select nomeComputador, idComputador from tbComputador where nomeComputador = ?",
                new BeanPropertyRowMapper(Leitura.class
                ),
                nomeComputador);

        List<Leitura> consultaDataHora = ConexaoAzure.jdbcTemplate.query(
                "Select ultimaVerificacaoComponentes from tbComputador where nomeComputador = ?",
                new BeanPropertyRowMapper(Leitura.class
                ),
                nomeComputador);

        for (Leitura leitura : consultaDataHora) {
            lbData.setText(leitura.getUltimaVerificacao());
        }

        if (!consultaCadastro.isEmpty()) {
            ConexaoAzure.jdbcTemplate.update(
                    "update tbComputador set fabricanteSO = ?, tipoSistemaOperacional = ?, ultimaVerificacaoComponentes = ?, fkEquipe  = ?, fkUsuario  = ? "
                    + "where nomeComputador = ?",
                    fabSO, SO, ultimaverif, fkEquipe, fkUsuario, nomeComputador);

            for (Leitura leitura : consultaCadastro) {
                System.out.println(leitura.getIdComputador());
                List<Leitura> consultaIdComputador = ConexaoAzure.jdbcTemplate.query(
                        "select idComponente from tbComponentes "
                        + "join tbComputador on fkComputador = idComputador\n"
                        + "where nomeComponente in('Memoria', 'Processador', 'Disco') and fkComputador = ?", new BeanPropertyRowMapper(Leitura.class
                        ), leitura.getIdComputador());

                Timer timer = new Timer();

                TimerTask InsertLeitura = new TimerTask() {
                    @Override
                    public void run() {
                        for (Leitura leitura1 : consultaIdComputador) {

                            switch (contador) {
                                case 1:
                                    
                                try {

                                    ConexaoAzure.jdbcTemplate.update("insert into tbLeituras values ( ?, ?, ?, ?)",
                                            data.getDiaHora(), leitura.getIdComputador(), leitura1.getIdComponente(), deci.format(memoria));

                                } catch (DataAccessException e) {
                                    Logs log = new Logs();
                                    try {
                                        log.gravarLog(String.format("Não foi possível enviar os dados da memoria para o banco de dados, erro gerado:\n\n"
                                                + "%s", e.toString()));
                                    } catch (IOException ex) {
                                        Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println(leitura1.getIdComponente());

                                contador++;
                                break;

                                case 2:
                                try {

                                    ConexaoAzure.jdbcTemplate.update("insert into tbLeituras values ( ?, ?, ?, ?)",
                                            data.getDiaHora(), leitura.getIdComputador(), leitura1.getIdComponente(), deci.format(cpu));

                                } catch (DataAccessException e) {
                                    Logs log = new Logs();
                                    try {
                                        log.gravarLog(String.format("Não foi possível enviar os dados do processador para o banco de dados, erro gerado::\n\n"
                                                + "%s", e.toString()));
                                    } catch (IOException ex) {
                                        Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                System.out.println(leitura1.getIdComponente());
                                contador++;
                                break;
                                case 3:
                                
                                try {

                                    ConexaoAzure.jdbcTemplate.update("insert into tbLeituras "
                                            + "values (?, ?, ?, ?)",
                                            data.getDiaHora(), leitura.getIdComputador(), leitura1.getIdComponente(), deci.format(disco));

                                } catch (DataAccessException e) {
                                    Logs log = new Logs();
                                    try {
                                        log.gravarLog(String.format("Não foi possível enviar os dados do disco para o banco de dados, erro gerado:\n\n"
                                                + "%s", e.toString()));
                                    } catch (IOException ex) {
                                        Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                contador = 1;
                                System.out.println(leitura1.getIdComponente());
                                break;
                            }

                        }
                    }
                };

                final long SecondsCpu = (3400 * 1);
                timer.scheduleAtFixedRate(InsertLeitura, 0, SecondsCpu);

                break;

            }

        } else {
            nomeComputador = JOptionPane.showInputDialog("Computador nao encontrado!! Registre um novo desktop");

            ConexaoAzure.jdbcTemplate.update(
                    "insert into tbComputador"
                    + "(nomeComputador, fabricanteSO, tipoSistemaOperacional, ultimaVerificacaoComponentes, fkEquipe, fkUsuario)"
                    + " values (?, ?, ?, ?, ? ,?)", nomeComputador, fabSO, SO, ultimaverif, fkEquipe, fkUsuario);

//            try {
//                
//                String insercao = String.format("insert into tbComputador"
//                        + "(idComputador, nomeComputador, fabricanteSO, tipoSistemaOperacional, ultimaVerificacaoComponentes, fkEquipe, fkUsuario)"
//                        + " values (null, '%s', '%s', '%s', '%s', %d ,%d)", nomeComputador, fabSO, SO, ultimaverif, fkEquipe, fkUsuario);
//                System.out.println(insercao);
//                PreparedStatement statement = connection.prepareStatement(insercao);
////                System.out.println(insercao);
//                statement.execute();
//                System.out.println("DEU CERTO!!!!!!!!");
//                
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
            List<Leitura> id = ConexaoAzure.jdbcTemplate.query(
                    "select top 1 idComputador from tbComputador order by idComputador desc",
                    new BeanPropertyRowMapper(Leitura.class
                    ));

            for (Leitura caco : id) {

                try {

                    ConexaoAzure.jdbcTemplate.update(
                            "Insert into tbComponentes values (?,?,?,?,?)",
                            Memoriacad, Memoriacad, 75, 90, caco.getIdComputador());
                    ConexaoAzure.jdbcTemplate.update(
                            "Insert into tbComponentes values (?,?,?,?,?)",
                            Processcad, Processcad, 75, 90, caco.getIdComputador());
                    ConexaoAzure.jdbcTemplate.update(
                            "Insert into tbComponentes values (?,?,?,?,?)",
                            Discocad, Discocad, 75, 90, caco.getIdComputador());

                } catch (DataAccessException e) {
                    Logs log = new Logs();
                    try {
                        log.gravarLog(String.format("Não foi possível cadastrar os componentes novos, erro gerado: \n\n"
                                + "%s", e.toString()));
                    } catch (IOException ex) {
                        Logger.getLogger(TelaDash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbicon3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbProcessador = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbHDD = new javax.swing.JLabel();
        lbRAM = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        lbicon4 = new javax.swing.JLabel();
        lbicon5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(252, 197, 119));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 110, 760, 1);

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dashboards");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(240, 40, 300, 50);

        lbicon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Grafico.png"))); // NOI18N
        jPanel1.add(lbicon3);
        lbicon3.setBounds(90, 190, 110, 90);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Processador");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(580, 300, 150, 40);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Disco Rígido");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(70, 300, 150, 40);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Memória");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(350, 300, 120, 40);

        lbProcessador.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbProcessador.setForeground(new java.awt.Color(255, 255, 255));
        lbProcessador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProcessador.setText("-----");
        jPanel1.add(lbProcessador);
        lbProcessador.setBounds(520, 340, 260, 40);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(252, 197, 119));
        jLabel12.setText("Ultima data visualizada:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(220, 450, 170, 40);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Copyright ©2020  ini. Designed By   INI DEVELOPMENT"); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(190, 510, 400, 50);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(252, 197, 119));
        jLabel2.setText("Z");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(428, 527, 10, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(252, 197, 119));
        jLabel3.setText("Z");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(304, 527, 10, 17);

        lbHDD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbHDD.setForeground(new java.awt.Color(255, 255, 255));
        lbHDD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHDD.setText("-----");
        jPanel1.add(lbHDD);
        lbHDD.setBounds(10, 340, 260, 40);

        lbRAM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbRAM.setForeground(new java.awt.Color(255, 255, 255));
        lbRAM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRAM.setText("-----");
        jPanel1.add(lbRAM);
        lbRAM.setBounds(270, 340, 260, 40);

        lbData.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        lbData.setForeground(new java.awt.Color(252, 197, 119));
        jPanel1.add(lbData);
        lbData.setBounds(390, 450, 220, 40);

        lbicon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Grafico.png"))); // NOI18N
        jPanel1.add(lbicon4);
        lbicon4.setBounds(600, 200, 110, 90);

        lbicon5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Grafico.png"))); // NOI18N
        jPanel1.add(lbicon5);
        lbicon5.setBounds(350, 190, 110, 90);

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sair");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 40, 80, 50);

        jLabel10.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Configurações");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10);
        jLabel10.setBounds(620, 50, 140, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, -10, 790, 570);

        setSize(new java.awt.Dimension(803, 601));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        int trocaUsuario = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja trocar de usúario ?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (trocaUsuario == JOptionPane.YES_OPTION) {

            List<AzureUser> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                    "Select nomeUsuario from tbUsuario where idUsuario = ?",
                    new BeanPropertyRowMapper(AzureUser.class
                    ),
                    fkUsuario);

            for (AzureUser azureUser : consultaCadastro) {

                try {

                    enviarMensagemSairSlack(azureUser.getNomeUsuario());

                } catch (IOException | InterruptedException e) {

                }

            }

            // System.exit(0);
            task.cancel();
            taskcpu.cancel();
            TelaLogin log = new TelaLogin();
            log.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        int trocaUsuario = JOptionPane.showConfirmDialog(null, "Gostaria de acessar a tela de Configurações ?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (trocaUsuario == JOptionPane.YES_OPTION) {
            ConfiguraçãoAlerta logg = new ConfiguraçãoAlerta();
            logg.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    /**
     * @param args the command line arguments
     */
    public void enviarMensagemSairSlack(String nome) throws IOException, InterruptedException {
        JSONObject json = new JSONObject();

        json.put("text", String.format("O usuário %s deslogou agora do software Zini!!!", nome));
        Slack.enviarMensagem(json);
    }

    public void enviarMensagemAlertaSlack(String nome, String componente, Double uso) throws IOException, InterruptedException {
        JSONObject json = new JSONObject();

        json.put("text", String.format("⚠️⚠️⚠️⚠️⚠️ ATENÇÃO ⚠️⚠️⚠️⚠️⚠\n"
                + "O computador do usuário %s \nteve um pico alto de consumo do componente %s em %.2f️", nome, componente, uso));
        Slack.enviarMensagem(json);
    }

    public static void main(String args[]) throws AWTException, IOException {
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
            java.util.logging.Logger.getLogger(TelaDash.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDash.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDash.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDash.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDash().setVisible(true);

            }
        });

        if (SystemTray.isSupported()) {
            TelaDash td = new TelaDash();
            td.timer();

        } else {
            System.out.println("System not supported");
        }

        Logs gravandoLog = new Logs();
        try {
            gravandoLog.gravarLog("Usuário realizou login !!");

        } catch (IOException ex) {

            gravandoLog.gravarLog("usuário nao encontrado");
            //   Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void timer() throws IOException {

        this.timerLeitura = new Timer();
        timerLeitura.scheduleAtFixedRate(taskLeitura, 0, SecondsLeitura);

        Logs gravandoLog = new Logs();
        try {
            gravandoLog.gravarLog("Sistema zini está com o horário correto!!");

        } catch (IOException ex) {

            gravandoLog.gravarLog("Horário incorreto, erro gerado:\n\n" + ex.toString());
            //   Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mensagem() throws AWTException, IOException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        List<AzureUser> consultaCadastro = ConexaoAzure.jdbcTemplate.query(
                "Select nomeUsuario from tbUsuario where idUsuario = ?",
                new BeanPropertyRowMapper(AzureUser.class
                ),
                fkUsuario);

        for (AzureUser azureUser : consultaCadastro) {

            trayIcon.displayMessage("Olá " + azureUser.getNomeUsuario(),
                    "Dê uma pausa no seu trabalho!", TrayIcon.MessageType.INFO);

        }

        Logs gravandoLog = new Logs();
        try {
            gravandoLog.gravarLog("Sistema zini está exibindo mensagens corretamente!!");

        } catch (IOException ex) {

            gravandoLog.gravarLog("Sistema zini não está exibindo mensagens corretamente, erro gerado:\n\n" + ex.toString());
            //   Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbHDD;
    private javax.swing.JLabel lbProcessador;
    private javax.swing.JLabel lbRAM;
    private javax.swing.JLabel lbicon3;
    private javax.swing.JLabel lbicon4;
    private javax.swing.JLabel lbicon5;
    // End of variables declaration//GEN-END:variables
}
