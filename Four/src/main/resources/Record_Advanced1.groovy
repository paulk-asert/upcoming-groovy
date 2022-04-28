record Agenda(List topics) { }

def a = new Agenda(topics: ['Sealed', 'Records'])
assert a.topics().size() == 2
assert a.toString() == 'Agenda[topics=[Sealed, Records]]'

a.topics().clear()
a.topics() << 'Switch Expressions'
assert a.topics().size() == 1
