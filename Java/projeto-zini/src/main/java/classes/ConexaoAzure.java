package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoAzure {
    public  static JdbcTemplate jdbcTemplate;
    public  static BasicDataSource dataSource;
    
    public String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public String url = "jdbc:sqlserver://dbgsn.database.windows.net:1433;database=bd-gsn;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;";
    public String username = "guilherme";
    public String password = "#Gf49488852828";

    public ConexaoAzure() {
        Conectar();
//        ConexaoMysql();
    };
    

    public void Conectar(){
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    

//    private BasicDataSource banco;
//
//    public void ConexaoMysql() {
//        this.banco = new BasicDataSource();
//        banco.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        banco.setUrl("jdbc:mysql://172.17.0.1:3306/bd_gsn");
//        banco.setUsername("root");
//        banco.setPassword("urubu100");
//    }
//
//    public BasicDataSource getBanco() {
//        return banco;
//    }

}
