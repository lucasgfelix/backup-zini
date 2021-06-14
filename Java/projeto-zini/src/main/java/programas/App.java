package programas;

import classes.ConexaoAzure;
import classes.ConexaoConteiner;
import classes.DataAtual;
import classes.Leitura;
import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.JdbcTemplate;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author guilherme.nsantos
 */
public class App {

//    ConexaoAzure conectar = new ConexaoAzure();
    public static void main(String[] args) throws IOException {

        Timer timer = new Timer();
        DataAtual data = new DataAtual(new Date());
        DecimalFormat deci = new DecimalFormat("####0.00");

//        String SO = comp.getSistema().getSistemaOperacional();
//        String ultimaverif = data.getDiaHora();
//        String fabSO = comp.getSistema().getFabricante();
//        String nomeComputador = "PC-Container";
//        Integer fkEquipe = 1;
//        Integer fkUsuario = 2;
//            ConexaoAzure.jdbcTemplate.update(
//                    "insert into tbComputador"
//                    + "(nomeComputador, fabricanteSO, tipoSistemaOperacional, ultimaVerificacaoComponentes, fkEquipe, fkUsuario)"
//                    + " values (?, ?, ?, ?, ? ,?)", nomeComputador, fabSO, SO, ultimaverif, fkEquipe, fkUsuario);
//        try {
//
//            String insercao = String.format("insert into tbComputador"
//                    + "(idComputador, fabricanteSO, tipoSistemaOperacional, ultimaVerificacaoComponentes, fkEquipe, fkUsuario)"
//                    + " values (null, '%s', '%s', '%s', %d ,%d)", fabSO, SO, ultimaverif, fkEquipe, fkUsuario);
//            System.out.println(insercao);
//            PreparedStatement statement = connection.prepareStatement(insercao);
//            statement.execute();
//            System.out.println("DEU CERTO!!!!!!!!");
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
        TimerTask InsertLeitura = new TimerTask() {

            ConexaoConteiner conn = new ConexaoConteiner();
            Connection connection = conn.conexaoConteiner();
            Integer contador = 1;

            @Override
            public void run() {

                Integer idComputador = 1;
                Integer idMemoria = 1;
                Integer idProcessador = 2;
                Integer idDisco = 3;

                Looca comp = new Looca();

                Double cpu = comp.getProcessador().getUso();
                System.out.println(cpu);
                Double memoria = comp.getMemoria().getEmUso().doubleValue() / 1000000000;
                System.out.println(memoria);
                Double disco = comp.getGrupoDeDiscos().getTamanhoTotal().doubleValue() / 1000000000;
                System.out.println(disco);

                switch (contador) {
                    case 1:
        try {

                        String insercao = String.format("insert into tbLeituras"
                                + " values (null, '%s', %d, %d, '%s')", data.getDataHora(), idComputador, idMemoria, deci.format(memoria));
                        System.out.println(insercao);
                        PreparedStatement statement = connection.prepareStatement(insercao);
                        statement.execute();

                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    contador++;
                    break;
                    case 2:
                         try {

                        String insercao = String.format("insert into tbLeituras"
                                + " values (null, '%s', %d, %d, '%s')", data.getDataHora(), idComputador, idProcessador, deci.format(cpu));
                        System.out.println(insercao);
                        PreparedStatement statement = connection.prepareStatement(insercao);
                        statement.execute();

                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    contador++;
                    break;
                    case 3:

                         try {

                        String insercao = String.format("insert into tbLeituras"
                                + " values (null, '%s', %d, %d, '%s')", data.getDataHora(), idComputador, idDisco, deci.format(disco));
                        System.out.println(insercao);
                        PreparedStatement statement = connection.prepareStatement(insercao);
                        statement.execute();

                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    contador = 1;
                    break;
                }

            }
        };

        final long SecondsCpu = (3000 * 1);
        timer.scheduleAtFixedRate(InsertLeitura, 0, SecondsCpu);
    }
    
    
    
}
