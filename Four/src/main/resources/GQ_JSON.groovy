import groovy.json.JsonSlurper

def json = new JsonSlurper().parseText('''
{
    "prices": [
        {"name": "Kakuda plum",      "price": 13},
        {"name": "Camu camu",        "price": 25},
        {"name": "Acerola cherries", "price": 39},
        {"name": "Guava",            "price": 2.5},
        {"name": "Kiwifruit",        "price": 0.4},
        {"name": "Orange",           "price": 0.4}
    ],
    "vitC": [
        {"name": "Kakuda plum",      "conc": 5300},
        {"name": "Camu camu",        "conc": 2800},
        {"name": "Acerola cherries", "conc": 1677},
        {"name": "Guava",            "conc": 228},
        {"name": "Kiwifruit",        "conc": 144},
        {"name": "Orange",           "conc": 53}
    ]
}
''')

assert GQ {
    from p in json.prices
    join c in json.vitC on c.name == p.name
    orderby c.conc / p.price in desc
    limit 2
    select p.name
}.toList() == ['Kakuda plum', 'Kiwifruit']
