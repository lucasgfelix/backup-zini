	'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Usuario = sequelize.define('Usuario',{
		idUsuario: {
			field: 'idUsuario',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeUsuario: {
			field: 'nomeUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		emailUsuario: {
			field: 'emailUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		cpfUsuario: {
			field: 'cpfUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		celularUsuario: {
			field: 'celularUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		cep: {
			field: 'cep',
			type: DataTypes.STRING,
			allowNull: false
		},
		rua: {
			field: 'rua',
			type: DataTypes.STRING,
			allowNull: false
		},
		bairro: {
			field: 'bairro',
			type: DataTypes.STRING,
			allowNull: false
		},
		cidade: {
			field: 'cidade',
			type: DataTypes.STRING,
			allowNull: false
		},
		senha: {
			field: 'senha',
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		tableName: 'tbUsuario', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Usuario;
};
