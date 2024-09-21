# ms-rpe-client


#### Especificação técnica:
	Java 17
	Maven 3+
	Spring boot 2.6.7
	Postgres
	Redis
	AWS SQS via Localstack
	Docker
	Docker Compose
	Swagger


### Repositórios correlacionados

	MICROSERVICO DE PRODUTOS
	https://github.com/yurifws/ms-rpe-product
	
	MICROSERVICO DE CARTAO
	https://github.com/yurifws/ms-rpe-card


### Passo a passo de como iniciar a aplicação localmente

	#- Passo 1
	Baixar os projetos na maquina local de preferencia branch de 'develop' ou 'main';
	
	Dentro do projeto '/ms-rpe-client' contem o arquivo a pasta 'docker'
	Lá se encontram o arquivo 'init.sql' na pasta '/scripts'
	E
	o arquivo 'docker-compose.yml';
	
	#- Passo 2
	Executar o comando docker-compose up -d
	As imagens serão criadas
	
	#- Passo 3
	Rodar todos os projetos
	
	#- Passo 4
	Dentro do projeto '/ms-rpe-client' também contem a collection 'COLLECTION_RPE_SISTEMA.json' para execuçao dos endpoints já configurados.

	
Obs:Necessário ter Java, Docker e algum gerenciador de banco de dados para Postgresql(pgAdmin) e Insominia ou Postman para realizar as chamadas nos endpoints.


### Passo a passo para deploy pelo docker compose


	#- Passo 1
	Baixar os projetos na maquina local de preferencia branch de 'develop' ou 'main';
	
	
	#- Passo 2
	Dentro de cada projeto executar o comando para gerar o .jar 
	mvn clean package -DskipTests
	
	/ms-rpe-product
	mvn clean package -DskipTests
	
	
	/ms-rpe-client
	mvn clean package -DskipTests
	
	
	/ms-rpe-card
	mvn clean package -DskipTests
	
	#- Passo 3
	Pegar a pasta '/scripts' de está dentro da pasta 'docker', coloca-la na raiz de onde os projetos foram baixados;
	Pegar o 'docker-compose.yml'(o de fora tem a criação das imagens de cada projeto.) que está FORA da pasta 'docker', coloca-lo na raiz de onde os projetos foram baixados;
	
	Os diretórios devem ficar dessa forma:
	/git
	├── docker-compose.yml
	├── ms-rpe-product
	│   ├── Dockerfile
	│   ├── src
	│   ├── pom.xml 
	│   └── target
	│       └── ms-rpe-product.jar
	├── ms-rpe-client
	│   ├── Dockerfile
	│   ├── src
	│   ├── pom.xml
	│   └── target
	│       └── ms-rpe-client.jar
	├── ms-rpe-card
	│   ├── Dockerfile
	│   ├── src
	│   ├── pom.xml
	│   └── target
	│       └── ms-rpe-card.jar
	├── scripts
	│   └── init.sql
	└── localstack_data
	
	#- Passo 4
	Executar o comando docker-compose up -d
	As imagens serão criadas
	
	
## Geração de token para utilizar o clients

	Criei apenas um login para autenticação dos endpoints de clientes.
	
	
	Utilizando a url:
	
	localhost:9102/login
	
	e o body
	{
	"login" : "admin",
	"password" : "123"
	}
	
	