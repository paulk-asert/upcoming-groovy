// ... create sql connection ...
def price = sql.rows('SELECT * FROM Price')
def vitC = sql.rows('SELECT * FROM VitaminC')
assert GQ {
    from p in price
    join c in vitC on c.name == p.name
    orderby c.per100g / p.per100g in desc
    limit 2
    select p.name
}.toList() == ['Kakuda plum', 'Kiwifruit']
// ... close connection ...