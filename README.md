# Alocar Turmas IA

## Objetivo
Mostrar horários de alocação de docentes a disciplinas (turmas) considerando as estratégias
de “problemas de satisfação de restrições”.

## Contexto
Você foi contatado para elaborar os horarios de uma Instituição de Ensino Superior onde
deve mostrar a alocação de docentes às disciplinas. Portanto você deve considerar, segundo a resolução
do curso, somente as discplinas obrigatórias do curso. Observar que algumas disciplinas podem ser de
outros departamentos e dificilmente mudam de alocação de dia e horário (portanto podem ser
consideradas fixas). Ainda antes da alocação cada docente tem de 3 a 5 disciplinas de preferência.
Considerar a flexibilidade do sistema ao momento de acrecentar ou diminuir docentes e/ou disciplinas.
Considerar as restrições descritas nos próximos parágrafos.

## Considerações preliminares do problema
Para efeitos de redução de complexidade, somente
considerar 1 curso (SI ou EC ou CC), além disso somente escolher 1 período com uma quantidade
media de disciplinas. Considerar somente uma turma por disciplina.

## Deve ser utilizado
O código AIMA, Java, Elaborar um FrontEnd [ i) coleta de dados, ii) mostrar o
horario ou horarios resposta e iii)uma lista de docentes e sua carga horária], Elaborar o BackEnd em
função dos algoritmos do Cap de “problema de satisfação de restrições”

## Armzenamento de dados secundários
O armazenamento dos dados secundários (em disco) pode ser
utilizado arquivos formato texto ou formato xml( onde você deve acrecentar os tags) ou utilizar banco
de dados. Pode utilizar dentro de estas opções o que lhe for mais conveniente para um desenvolvimento
rápido.

## Relatório Detalhado
Deve conter uma introdução e o contexto do problema, assim como detalhar a
solução do problema. No detalhamento deve incluir a modelagem do problema especificando os 3
componentes indicados no capítulo como X (variáveis), D (domínios), C (restrições), ver mais detalhes
no próprio capítulo “problemas de satisfação de restrições”.

Espeficicar os algoritmos utilizados assim como a metodologia utilizada para resolver o problema. No
final do relatório deve conter um pequeno manual de funcionamento do sistema.

O relatório deve conter a lista de participantes que efetivamente “participaram e colaboraram
efetivamente no trabalho”. Não é obrigatório colocar um integrante se este não participou na
elaboração do projeto final.

Todos os integrantes devem estar aptos a responder perguntar específicamente dos algoritmos de
“problemas de satisfação de restrições” , não sendo justifictiva o fato de ter feito ou participado
somente por exemplo da interfáce gráfica, já que a nossa disciplina é de Inteligência Artificial.
O que não faz parte do trabalho: Voce não precisa se preocupar sobre a matrícula do discente,
reserva de vagas. Tudo isso é resolvido pelo Sistema Acadêmico do DAA, você deve considerar que
tem alunos matriculados na turma, portanto tem turma cheia. Voce não deve se preocupar na alocação
de número de Sala, isso também é feito pelo Sistema Acadêmico do DAA e não faz parte do nosso
trabalho final.

## Sobre as Restrições
Foi indicado em sala de aula a restrição base de prefrencia pessoal dos
professores (de 3 a 5 disciplinas de preferência) para ministrar disciplinas. Sobre outras restrições
diferentes fica em aberto a quantidade de restrições a serem adicionadas. Algumas foram comentadas
em sala de aula e dependerá do grupo avaliar quais considerar.

É necessário elaborar um frontend para registrar as restrições e poder acrescentar ou remover as
mesmas.

## Considerações finais
A resposta final pode ser um horário ou depedendendo das restrições podem
existir mais de uma resposta, a leitura do Capítulo “problemas de satisfação de restrições” é importante
para observar esse detalhe.

Pode acontecer de uma disciplina não ter docente no final do algoritmo então no horário é necessário
mostrar abaixo do nome da disciplina(turma) o texto “* Sem Docente” para que o Chefe de
Departamento veja essa situação específica.

Não olvidar de mostrar uam interface gráfica final a “iii) lista de docentes e sua carga horária”.

## Forma de Avaliação

Na avaliação será considerado o Relatório Detalhado + Código Fonte (incluindo pequeno manual de
funcionamento). Ver tópico “Relatório Detalhado” acima para maiores informações assi como cada um
dos tópicos aqui relatados.

Para avaliação do trabalho final o sistema precisa estar funcional.

O grupo ou os integrantes do grupo podem vir a ser convocados de forma presencial para avaliação do
projeto final sobre pontos específicos do trabalho final.

A nota do Projeto final será para a unidade 3 considerando o peso descrito no plano de ensino.
