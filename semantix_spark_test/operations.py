from operator import add

'''
2 - Total de erros 404
'''
def response_with_code_404(line):
    try:
        code = line.split(' ')[-2]
        if code == '404':
            return True
    except:
        pass
    return False


'''
3 - As 5 URLs que mais causaram erro 404.
'''
def endpoints_with_more_error_404(rdd):
    endpoints = rdd.map(lambda line: line.split('"')[1].split(' ')[1])
    counts = endpoints.map(lambda endpoint: (endpoint, 1)).reduceByKey(add)
    top = counts.sortBy(lambda pair: -pair[1]).take(5)
    for endpoint, count in top:
        print(endpoint, " --- ", count)

    return top



'''
4 - Quantidade de erros 404 por dia.
'''
def count_error_404_daily(rdd):
    days = rdd.map(lambda line: line.split('[')[1].split(':')[0])
    counts = days.map(lambda day: (day, 1)).reduceByKey(add).collect()

    for day, count in counts:
        print(day, " --- ", count)

    return counts

'''
5 - Total de bytes retornados.
'''
def count_all_byte(rdd):
    return rdd.map(count_byte).reduce(add)

def count_byte(line):
    try:
        count = int(line.split(" ")[-1])
        if count < 0:
            raise ValueError()
        return count
    except:
        return 0