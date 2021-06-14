var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Usuario = require('../models').Usuario;
const nodemailer = require("nodemailer");
// const idUsu;

let sessoes = [];

/* Recuperar usuário por login e senha */
router.post('/autenticar', function (req, res, next) {
	console.log('Recuperando usuário por login e senha');

	var login = req.body.login; // depois de .body, use o nome (name) do campo em seu formulário de login
	var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login	

	let instrucaoSql = `select * from tbUsuario where emailUsuario='${login}' and senha='${senha}'`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: Usuario
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);

		if (resultado.length == 1) {
			sessoes.push(resultado[0].dataValues.emailUsuario);
			console.log('sessoes: ', sessoes);
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Login e/ou senha inválido(s)');
		} else {
			res.status(403).send('Mais de um usuário com o mesmo login e senha!');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

/* Cadastrar usuário */
router.post('/cadastrar', function (req, res, next) {
	console.log('Criando um usuário');

	Usuario.create({
		nomeUsuario: req.body.nome,
		emailUsuario: req.body.email,
		cpfUsuario: req.body.cpf,
		celularUsuario: req.body.celular,
		cep: req.body.cep,
		rua: req.body.rua,
		bairro: req.body.bairro,
		cidade: req.body.cidade,
		senha: req.body.senha
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
		res.send(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

// Update usuario

router.post('/alterar', function (req, res, next) {
	console.log('Recuperando usuário por login e senha');

	var nome = req.body.nome;
	var cpf = req.body.cpf;
	var celular = req.body.celular;
	var senha = req.body.senha;
	// var telefone = req.body.telefone;
	var login = req.body.email; // depois de .body, use o nome (name) do campo em seu formulário de login
	// var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login	

	let instrucaoSql = `select idUsuario from tbUsuario where cpfUsuario='${cpf}'`;
	// let instrucaoSql = `update tbUsuario set nome = '${nome}' where idUsuario = 7`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: Usuario
	}).then(resultado => {
		console.log(`Encontrados: ${resultado[0].dataValues.idUsuario}`);

		if (resultado.length == 1) {
			// sessoes.push(resultado[0].dataValues.login);
			console.log('ID: ', resultado[0].dataValues.idUsuario);
			let id = resultado[0].dataValues.idUsuario;
			if (nome != "") {
				let update = `update tbUsuario set nomeUsuario = '${nome}' where idUsuario = ${id}`;
				sequelize.query(update)
				res.send(resultado);
			}
			if (login != "") {
				let update = `update tbUsuario set emailUsuario = '${login}' where idUsuario = ${id}`;
				sequelize.query(update)
				res.send(resultado);
			}
			if (celular != "") {
				let update = `update tbUsuario set celularUsuario = '${celular}' where idUsuario = ${id}`;
				sequelize.query(update)
				res.send(resultado);
			}
			if (senha != "") {
				let update = `update tbUsuario set senha = '${senha}' where idUsuario = ${id}`;
				sequelize.query(update)
				res.send(resultado);
			}


		} else if (resultado.length == 0) {
			res.status(403).send('Email não encontrado');
		} else {
			res.status(403).send('Mais de um usuário com o mesmo login e senha!');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send("Insira o seu email cadastrado");
	});
});


/* Verificação de usuário */
router.get('/sessao/:login', function (req, res, next) {
	let login = req.params.login;
	console.log(`Verificando se o usuário ${login} tem sessão`);

	let tem_sessao = false;
	for (let u = 0; u < sessoes.length; u++) {
		if (sessoes[u] == login) {
			tem_sessao = true;
			break;
		}
	}

	if (tem_sessao) {
		let mensagem = `Usuário ${login} possui sessão ativa!`;
		console.log(mensagem);
		res.send(mensagem);
	} else {
		res.sendStatus(403);
	}

});


/* Logoff de usuário */
router.get('/sair/:login', function (req, res, next) {
	let login = req.params.login;
	console.log(`Finalizando a sessão do usuário ${login}`);
	let nova_sessoes = []
	for (let u = 0; u < sessoes.length; u++) {
		if (sessoes[u] != login) {
			nova_sessoes.push(sessoes[u]);
		}
	}
	sessoes = nova_sessoes;
	res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});


/* Recuperar todos os usuários */
router.get('/', function (req, res, next) {
	console.log('Recuperando todos os usuários');
	Usuario.findAndCountAll().then(resultado => {
		console.log(`${resultado.count} registros`);

		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

router.post('/sendEmail', function (req, res, next) {
	console.log('Enviando email');

	var email = req.body.email; // depois de .body, use o nome (name) do campo em seu formulário de login

	var codeAleatorio = Math.trunc(Math.random() * 100001 + 992);

	console.log(codeAleatorio)

	try {
		let instrucaoSql = `select idUsuario from tbUsuario where emailUsuario='${email}'`;
		// let instrucaoSql = `update tbUsuario set nome = '${nome}' where idUsuario = 7`;
		console.log(instrucaoSql);

		sequelize.query(instrucaoSql, {
			model: Usuario

		}).then(resultado => {

			// console.log(`Encontrados: ${resultado[0].dataValues.idUsuario}`);

			if (resultado.length === 1) {
				sessoes.push(resultado[0].dataValues.login);
				// console.log('ID: ',resultado[0].dataValues.idUsuario);
				let id = resultado[0].dataValues.idUsuario;
				// idUsu = id;
				if (id != "") {

					let update = `update tbUsuario set codigoRecuperarSenha = '${codeAleatorio}' where idUsuario = ${id}`;
					sequelize.query(update)

					let transporter = nodemailer.createTransport({
						host: "smtp-mail.outlook.com",
						port: 587,
						auth: {
							user: "projeto.zini@hotmail.com",
							pass: "#Gfgrupo3"
						}

					});

					transporter.sendMail({

						from: "Zini <projeto.zini@hotmail.com>",
						to: `${email}`,
						subject: "ZINI - CÓDIGO REQUISIÇÃO NOVA SENHA",
						text: "Código de requisição para nova senha",
						html: `Prezado (a), <br>Insira o código em nosso site para alterar a sua senha,<br>Código: ${codeAleatorio}<br><br>
				<img src="../public/img/Original on Transparent.png" alt="">`

					}).then(message => {
						console.log(message);
					}).catch(err => {
						console.log(err);
					});
					res.json(resultado[0]);
				}


			} else if (resultado.length == 0) {
				res.status(403).send('Email não encontrado');
			}
		})

	} catch (error) {
		console.log(erro)
		res.status(403).send('Email não encontrado');
	}





});

router.post('/autenticarCodigo', function (req, res, next) {
	console.log('buscando código de recuperação');

	var codigo = req.body.codigo;

	let instrucaoSql = `select codigoRecuperarSenha from tbUsuario where codigoRecuperarSenha = ${codigo}`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: Usuario
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);

		if (resultado.length == 1) {
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Código Incorreto');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

router.post('/alterarSenha/:codigo', function (req, res, next) {
	console.log('Trocando sua senha');

	var senha = req.body.senha;
	var confSenha = req.body.senha2;


	var codigo2 = req.params.codigo;
	console.log(codigo2)
	let update = `update tbUsuario set senha = '${senha}' where codigoRecuperarSenha = ${codigo2}`;
	console.log(update);
	sequelize.query(update);
	let instrucaoSql = `select senha from tbUsuario where senha = '${senha}' and codigoRecuperarSenha = ${codigo2}`;


	sequelize.query(instrucaoSql, {
		model: Usuario
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);

		if (senha == confSenha) {
			if (senha.length >= 8) {
				res.json(resultado[0]);
			} else {
				res.status(403).send('Não foi possível alterar sua senha no momento, tente mais tarde');
			}
		} else {
			res.status(403).send('Não foi possível alterar sua senha no momento, tente mais tarde');
		}

		// if (resultado.length == 1) {
		// 	res.json(resultado[0]);
		// } else if (resultado.length == 0) {
		// 	res.status(403).send('Não foi possível alterar sua senha no momento, tente mais tarde');
		// }

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

module.exports = router;