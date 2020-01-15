/**
* Esse código realiza algumas transformações para construir um conjunto de dados de pares (String, Int) chamado counts e em seguida salvá-o em um arquivo.
* Este é um exemplo simples de um Word Count, o Hello World em big data.
* @author leonciosr
*/

// Lê as linhas do arquivo de entrada, que está no formato HDFS, e cria	um RDD a partir dos dados carregados.
val​ ​​textFile​ ​​=​ ​​sc​.​textFile​(​"hdfs://..."​)

val​ ​​counts​ ​​=​ ​​textFile​
                .​flatMap​(​line​ ​​=>​ ​line​.​split​(​"​ ​"​))  // quebra cada linha do arquivo em uma sequência de palavras e as sequencias correspondentes à cada linha são transformadas em uma única coleção de palavras
​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​.​map​(​word​ ​​=>​ ​​(​word​,​ ​​1​))            // cada palavra da coleção é transformada em um conjunto de dados de pares (chave , valor) sendo a chave igual à própria palavra e valor 1
​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​ ​​.​reduceByKey​(​_​ ​​+​ ​​_​)                // os valores mapeados são agregados por chave por meio da operação de soma, ou seja, o comando reduceByKey reduzirá todas as ocorrências de palavras repetidas a um único elemento com contador refletindo esse número de ocorrências

//o RDD contendo a contagem de cada palavra é salvo em um HDFS
counts​.​saveAsTextFile​(​"hdfs://..."​)