import groovy.xml.XmlSlurper

def root = new XmlSlurper().parseText('''
<root>
    <prices>
        <price name="Kakuda plum">13</price>
        <price name="Camu camu">25</price>
        <price name="Acerola cherries">39</price>
        <price name="Guava">2.5</price>
        <price name="Kiwifruit">0.4</price>
        <price name="Orange">0.4</price>
    </prices>
    <vitaminC>
        <conc name="Kakuda plum">5300</conc>
        <conc name="Camu camuum">2800</conc>
        <conc name="Acerola cherries">1677</conc>
        <conc name="Guava">228</conc>
        <conc name="Kiwifruit">144</conc>
        <conc name="Orange">53</conc>
    </vitaminC>
</root>
''')

assert GQ {
    from p in root.prices.price
    join c in root.vitaminC.conc on c.@name == p.@name
    orderby c.toInteger() / p.toDouble() in desc
    limit 2
    select p.@name
}.toList() == ['Kakuda plum', 'Kiwifruit']
