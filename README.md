# Movie Battle Api

## Projeto desenvolvido para seleção de professor JAVA: 
>Antonio Ezequiel de Mendonça

Este projeto foi desenvolvido com base nos seguinte requisitos: 
## Movies Battle
Crie uma API REST para uma aplicação ao estilo card game, onde serão informados dois filmes e o jogador deve acertar aquele que possui melhor avaliação no IMDB.

1. O jogador deve fazer login para iniciar uma nova partida. Portanto, cada partida sempre será identificada pela autenticação do usuário.
a. Não há restrições onde armazenar os usuários: em memória, ou em banco, etc.
2. Cada rodada do jogo consiste em informar um par de filmes, observando para não repetir o mesmo par nem formar um par com um único filme.
a. São sequências não-válidas: [A-A] o mesmo filme repetido; [A-B, A-B] pares repetidos – considere iguais os pares do tipo A-B e B-A.
b. Os seguintes pares são válidos: [A-B, B-C] o filme B é usado em pares diferentes.
3. O jogador deve tentar acertar qual filme possui maior pontuação, composta pela nota (0.0-10.0) multiplicado pelo total de votos.
4. Se escolher o vencedor correto, conta 1 ponto. São permitidos até três erros. Após responder, terá acesso a novo par de filmes quando acessar o endpoint do quiz.
5. Forneça endpoints específicos para iniciar e encerrar a partida a qualquer momento. Valide o momento em que cada funcionalidade pode ser acionada.
6. Não deve ser possível avançar para o próximo par sem responder o atual.
7. Deve existir uma funcionalidade de ranking, exibindo os melhores jogadores e suas pontuações.
8. A pontuação é obtida multiplicando a quantidade de quizzes respondidos pela porcentagem de acerto.

## Movies Battle - Modo de uso
A primeira ação que deve ser realizada para utilizar o sistema é executar o endpoint /carregar uma única vez. 
Esse endpoint carrega a banco com os usuários padrão (login: antonio, senha: 123456 - login: jose, senha: 123456) salvos em H2. Os filmes são extraidos do IMDB e carregados na tabela do H2. 
Após essa ação, é necessário realizar o login (/auth) no sistema para a geração do token. 
O token deve ser passado para os endpoints restritos(/iniciar, /sortear, /jogar, /finalizar). 
O jogo inicia com o endpoint /iniciar, e continua com os demais endpoits. 
Sempre deve usar o endpoint /sortear e /jogar até decidir /finalizar o jogo ou perder todas as vidas. 
O jogo pode ser finalizado a qualquer momento pelo jogador /finalizar e é possível consultar o /ranking a qualquer momento sem realizar login.
O sistema foi documentado com swagger, disponível no link: http://localhost:8080/swagger-ui/index.html.
O token tem validade de 5 minutos.
