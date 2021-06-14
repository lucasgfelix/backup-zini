#!/bin/bash

PURPLE='0;35'
NC='\033[0m' 
VERSAO=11

echo  "
______       _   ____        _   
|__  (_)_ __ (_) | __ )  ___ | |_ 
  / /| | '_ \| | |  _ \ / _ \| __|
 / /_| | | | | | | |_) | (_) | |_ 
/____|_|_| |_|_| |____/ \___/ \__|
"
echo  "$(tput setaf 10)[Zini]:$(tput setaf 7) Olá, serei seu assistente java!;"
echo  "$(tput setaf 10)[Zini]:$(tput setaf 7)  Verificando aqui se você possui o Java instalado...;"

java -version
if [ $? -eq 0 ]
	then
		echo "$(tput setaf 10)[Zini]:$(tput setaf 7) : Java encontrado!"
		echo "$(tput setaf 10)[Zini]:$(tput setaf 7) Deseja abrir o software zini (S/N)?"
                read inst
                if [ \"$inst\" == \"s\" ]
                        then
                            cd /home/ubuntu/Desktop/2ADSB-2021-1-Grupo-03/Java/projeto-zini/target
                            java -jar projeto-zini-1.0-SNAPSHOT-jar-with-dependencies.jar
                        else
                            echo "$(tput setaf 10)[Zini]:$(tput setaf 7) Obrigado pela preferencia, ate mais!! :)"
                        fi
	else
		echo "$(tput setaf 10)[Zini]:$(tput setaf 7)  Java nao identificado, mas sem problemas, irei resolver isso agora!"
		echo "$(tput setaf 10)[Zini]:$(tput setaf 7)  realmente deseja instalar o Java (S/N)?"
	read inst
	if [ \"$inst\" == \"s\" ] 
		then
			echo "$(tput setaf 10)[Zini]:$(tput setaf 7)  Adicionando o repositório!"
			sleep 2
			sudo add-apt-repository ppa:webupd8team/java -y
			clear
			echo "$(tput setaf 10)[Zini]:$(tput setaf 7)  Atualizando..."
			sleep 2
			sudo apt update -y
			clear
			
			if [ $VERSAO -eq 11 ]
				then
					echo "$(tput setaf 10)[Zini]:$(tput setaf 7) instalar a versão 11 do Java. Confirme a instalação quando solicitado ;D"
					sudo apt install default-jre ; apt install openjdk-11-jre-headless; -y
					clear
					echo "$(tput setaf 10)[Zini]:$(tput setaf 7) Java instalado com sucesso!"
					echo "$(tput staf 10)[Zini]:$(tput setaf 7) Deseja abrir o software zini (S/N)?"
					read inst
					if [ \"$inst\" == \"s\" ]
					      then
						      cd /home/ubuntu/Desktop/2ADSB-2021-1-Grupo-03/Java/projeto-zini/target
						      java -jar projeto-zini-1.0-SNAPSHOT-jar-with-dependencies.jar
					      else
						      echo "$(tput setaf 10)[Zini]:$(tput setaf 7) Obrigado pela preferencia, ate mais!! :)"
					fi	      
				fi
		else 	
		echo "$(tput setaf 10)[Zini]:$(tput setaf 7)  Você optou por não instalar o Java por enquanto, até a próxima então!"
	fi
fi

# ===================================================================
# Todos direitos reservados para o autor: Dra. Profa. Marise Miranda.
# Sob licença Creative Commons @2020
# Podera modificar e reproduzir para uso pessoal.
# Proibida a comercialização e a exclusão da autoria.
# ===================================================================
