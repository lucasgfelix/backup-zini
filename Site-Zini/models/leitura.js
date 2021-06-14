'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Leitura = sequelize.define('Leitura',{	
		idLeitura: {
			field: 'idLeitura',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},	
		dataLeitura: {
			field: 'temp_atual',
			type: DataTypes.STRING,
			allowNull: false
		},
		fkComputador: {
			field: 'hr_temp',
			type: DataTypes.INTEGER,
			allowNull: false
		},
		fkComponentes: {
			type: DataTypes.INTEGER, 
			allowNull: true
		},
		usoPorcentagemAtual: {
			type: DataTypes.STRING, 
			allowNull: true
		}
	}, 
	{
		tableName: 'tbLeituras', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Leitura;
};
