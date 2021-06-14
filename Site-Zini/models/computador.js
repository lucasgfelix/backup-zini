'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Computador = sequelize.define('Computador',{	
		idComputador: {
			field: 'idComputador',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},	
		nomeComputador: {
			field: 'nomeComputador',
			type: DataTypes.STRING,
			allowNull: false
		},
		fabricanteSO: {
			field: 'fabricanteSO',
			type: DataTypes.STRING,
			allowNull: false
		},
		tipoSistemaOperacional: {
            field: 'tipoSistemaOperacional',
			type: DataTypes.STRING, // campo 'falso' (não existe na tabela). Deverá ser preenchido 'manualmente' no select
			allowNull: false
		},
        ultimaVerificacaoComponentes: {
            field: 'ultimaVerificacaoComponentes',
			type: DataTypes.STRING, // campo 'falso' (não existe na tabela). Deverá ser preenchido 'manualmente' no select
			allowNull: false
		},
        tempoAtividade: {
            field: 'tempoAtividade',
			type: DataTypes.STRING, // campo 'falso' (não existe na tabela). Deverá ser preenchido 'manualmente' no select
			allowNull: false
		},
        fkEquipe: {
            field: 'fkEquipe',
			type: DataTypes.INTEGER, // campo 'falso' (não existe na tabela). Deverá ser preenchido 'manualmente' no select
			allowNull: false
		},
        fkUsuario: {
            field: 'fkUsuario',
			type: DataTypes.INTEGER, // campo 'falso' (não existe na tabela). Deverá ser preenchido 'manualmente' no select
			allowNull: false
		}
	}, 
	{
		tableName: 'tbComputador', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Computador;
};
