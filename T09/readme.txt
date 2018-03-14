O que fizemos?
- Alterámos a interface do TTTService para incluir uma nova chamada remota random(int player) que retorna uma play aleatória para o utilizador especificado.

O random(int player) gera um número aleatório entre 0 e 9, e invoca o play(row, column, player) com a jogada aleatória.

Cumprimos os Objetivos?
- Sim, quando é invocado o comando 99 este faz uma jogada aleatória em nome do utilizador.

Pós-Especificações
Tudo cumprido.

Respostas Questões do Enunciado
4.
(i) Em RMI não é gerado código para futura modificação pelo programador, mas são gerados objetos remotos que podem ser referenciados como argumentos ou retorno dos métodos invocados remotamente;
(ii) Cliente - Game; Servidor - TTTServant (implementa TTTService); Ambos - Interface TTTService;
