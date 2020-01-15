
# Teste de proficiência sobre Spark

## Informações Gerais

O teste consiste em algumas perguntas e exercícios práticos sobre Spark e as respostas e códigos implementados devem ser armazenados​ ​no​ ​GitHub.​ ​O​ ​link​ ​do​ ​seu​ ​repositório​ ​deve​ ​ser​ ​compartilhado​ ​conosco​ ​ao​ ​final​ ​do​ ​teste.  
Quando usar alguma referência ou biblioteca externa, informe no arquivo README do seu projeto. Se tiver alguma dúvida,​ ​use​ ​o​ ​bom​ ​senso​ ​e​ ​se​ ​precisar​ ​deixe​ ​isso​ ​registrado​ ​na​ ​documentação​ ​do​ ​projeto.


## Questionário

**Qual o objetivo do comando *cache* em Spark?**

Basicamente o seu objetivo é alocar os arquivos na memória. Isso é feito para melhorar a eficiência do código que faz muito uso de operações *lazy* em um RDD, uma vez que permite que resultados intermediários de operações *lazy* possam ser armazenados e reutilizados repetidamente.
Um ponto relevante é que o cache do Spark é tolerante a falhas, ou seja, se qualquer partição de um RDD for perdida, ele será automaticamente recalculado usando as transformações originalmente criadas. Ele também é uma forma abreviada de usar o nível de armazenamento padrão, que é StorageLevel.MEMORY_ONLY (armazena objetos desserializados na memória).

**O mesmo código implementado em Spark é normalmente mais rápido que a implementação equivalente em *MapReduce*. Por quê?**

O Spark é normalmente muito mais rápido que o MapReduce devido à forma como processa os dados. Enquanto o MapReduce opera em etapas, o Spark opera a partir de todo o conjunto de dados de um só vez. O fluxo de trabalho do MapReduce acontece da seguinte forma: ler dados do cluster, realizar uma operação, inscrever os resultados no agrupamento, ler os dados atualizados a partir do agrupamento, realizar a próxima operação, inscrever os resultados no cluster, e assim por diante. Enquanto o Spark, por outro lado, completa as operações de análise de dados na memória e em tempo quase real, ocorrendo da seguinte forma: ler os dados do cluster, realizar todas as operações analíticas necessárias, escrever os resultados no cluster. O Spark pode ser até 10 vezes mais rápido que o MapReduce para o processamento em lote e até 100 vezes mais rápido para análises in-memory.


**Qual é a função do SparkContext ?**

O SparkContext é o objeto que conecta o Spark ao programa que está sendo desenvolvido. Ele funciona como um cliente do ambiente de execução Spark. Através dele, passam-se as configurações que vão ser utilizadas na alocação de recursos, como memória e processadores, pelos *executors*. Também usa-se o SparkContext para criar RDDs, colocar *jobs* em execução, criar variáveis de *broadcast* e acumuladores.


**Explique com suas palavras o que é Resilient Distributed Datasets (RDD)**

Formalmente é uma coleção de objetos somente leitura, particionados em um conjunto de nodos do cluster, podendo somente  ser criado através de funções determinísticas (map, filter, join, groupBy) executadas em outros RDDs ou meios de armazenamentos estáveis. Eles são a principal abstração de dados do Spark. São chamados de *Resilient* por serem tolerantes à falha e de *Distributed* por que podem estar divididos em partições através de diferentes nós em um cluster. Eles podem ser operados em paralelo, ou seja, operações podem ser executadas sobre diferentes partições de um mesmo RDD ao mesmo tempo.
 

**GroupByKey é menos eficiente que reduceByKey em grandes dataset. Por quê?**

O groupByKey e o reduceByKey produzem os mesmos resultados. No entanto, o groupByKey transferirá todo o Dataset pela rede, enquanto que o reduceByKey calcula somas locais para cada chave em cada partição e combina essas somas locais em somas maiores após o shuffle, ou seja,  enquanto o GroupByKey não realiza nenhum agrupamento de informações já no resultado de uma tarefa dentro de um nó do cluster, o reduceByKey promove um primeiro agrupamento dentro do contexto de cada nó para ao final realizar uma única operação de união das informações coletadas de cada nó. Dessa forma, o groupByKey acaba trafegando muito mais dados de cada uma das pontas para apenas no final realizar um agrupamento.
Há outras duas funções que possuem preferência em relação ao groupByKey: combineByKey e foldByKey.


## Explique o que o código Scala abaixo faz 

Ver arquivo ***codigoscala.scala***


## HTTP​ ​requests​ ​to​ ​the​ ​NASA​ ​Kennedy​ ​Space​ ​Center​ ​WWW​ ​server

**Fonte​ ​oficial​ ​do​ ​dateset**​:​ ​​http://ita.ee.lbl.gov/html/contrib/NASA-HTTP.html  

**Dados​:**  
 - Jul​ ​01​ ​to​ ​Jul​ ​31,​ ​ASCII​ ​format,​ ​20.7​ ​MB​ ​gzip​   ​compressed​,​ ​200.4​ ​MB (ftp://ita.ee.lbl.gov/traces/NASA_access_log_Jul95.gz)
 - Aug​ ​04​ ​to​ ​Aug​ ​31,​ ​ASCII​   ​format,​ 16.3​ ​MB​ ​gzip​ ​compressed​,​ ​163.8​ ​MB (ftp://ita.ee.lbl.gov/traces/NASA_access_log_Aug95.gz).

**Sobre o dataset**​: Esses dois conjuntos de dados possuem todas as requisições HTTP para o servidor da NASA Kennedy Space​ ​Center​ ​WWW​ ​na​ ​Flórida​ ​para​ ​um​ ​período​ ​específico.  

Os​ ​logs​ ​estão​ ​em​ ​arquivos​ ​ASCII​ ​com​ ​uma​ ​linha​ ​por​ ​requisição​ ​com​ ​as​ ​seguintes​ ​colunas:  

 - **Host fazendo a requisição**​. Um hostname quando possível, caso contrário o endereço de internet se o nome  
não​ ​puder​ ​ser​ ​identificado.  
- **Timestamp**​ ​​no​ ​formato​ ​"DIA/MÊS/ANO:HH:MM:SS​ ​TIMEZONE"  
- **Requisição​ ​(entre​ ​aspas)**  
- **Código​ ​do​ ​retorno​ ​HTTP**  
- **Total​ ​de​ ​bytes​ ​retornados**  


**Questões**  

​Responda​ ​as​ ​seguintes​ ​questões​ ​devem​ ​ser​ ​desenvolvidas​ ​em​ ​Spark​ ​utilizando​ ​a​ ​sua​ ​linguagem​ ​de​ ​preferência.  

1. Número​ ​de​ ​hosts​ ​únicos.  
2. O​ ​total​ ​de​ ​erros​ ​404.  
3. Os​ ​5​ ​URLs​ ​que​ ​mais​ ​causaram​ ​erro​ ​404.  
4. Quantidade​ ​de​ ​erros​ ​404​ ​por​ ​dia.  
5. O​ ​total​ ​de​ ​bytes​ ​retornados.