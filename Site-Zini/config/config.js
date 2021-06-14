module.exports = {
  production: {
    username: 'guilherme',
    password: '#Gf49488852828',
    database: 'bd-gsn',
    host: 'dbgsn.database.windows.net',
    dialect: 'mssql',
    xuse_env_variable: 'DATABASE_URL',
    dialectOptions: {
      options: {
        encrypt: true
      }
    },
    pool: { 
      max: 5,
      min: 1,
      acquire: 5000,
      idle: 30000,
      connectTimeout: 5000
    }
  }
};
 
