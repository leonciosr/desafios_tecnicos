# Informações adicionais estão presentes no arquivo 'readme.md' na seção 'HTTP​ ​requests​ ​to​ ​the​ ​NASA​ ​Kennedy​ ​Space​ ​Center​ ​WWW​ ​server'
# @author leonciosr

from pyspark import SparkConf, SparkContext
from operations import *

conf: SparkConf = (SparkConf()
                    .setMaster("local")
                    .setAppName("semantix_spark_test")
                    .set("spark.executor.memory", "2g"))

sc = SparkContext(conf=conf)

july = sc.textFile('access_log_Jul95')
august = sc.textFile('access_log_Aug95')

july = july.cache()
august = august.cache()

# 1 - número​ ​de​ ​hosts​ ​únicos
july_count = july.flatMap(lambda line: line.split(' ')[0]).distinct().count()
august_count = august.flatMap(lambda line: line.split(' ')[0]).distinct().count()


print("\n\nNúmero de hosts únicos: ")
print("   * Julho - %s" % july_count)
print("   * Agosto - %s" % august_count)

july_qtde_error_404 = july.filter(response_with_code_404).cache()
august_qtde_error_404 = august.filter(lambda line: line.split(' ')[-2] == '404').cache()

print("\n\nTotal de erros 404: ")
print("   * Julho - %s" % july_qtde_error_404.count())
print("   * Agosto - %s" % august_qtde_error_404.count())

# 3 - As​ ​5​ ​URLs​ ​que​ ​mais​ ​causaram​ ​erro​ ​404.
print("\n\nAs 5 URLs que mais causaram erro 404 em julho :")
endpoints_with_more_error_404(july_qtde_error_404)
print("\n\nAs 5 URLs que mais causaram erro 404 em agosto :")
endpoints_with_more_error_404(august_qtde_error_404)

# 4 - quantidade​ ​de​ ​erros​ ​404​ ​por​ ​dia.
print("\n\nQuantidade de erros 404 por dia: ")
count_error_404_daily(july_qtde_error_404)
count_error_404_daily(august_qtde_error_404)

# 5 -​ ​total​ ​de​ ​bytes​ ​retornados.
print("\n\nTotal de bytes retornados: ")
print("   * Julho - %s" % count_all_byte(july))
print("   * Agosto - %s" % count_all_byte(august))

sc.stop()
