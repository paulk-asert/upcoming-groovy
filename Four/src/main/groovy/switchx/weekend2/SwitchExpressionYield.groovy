package switchx.weekend2

enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

import static Day.*
def isWeekend(Day d) {
    return switch(d) {
        case Monday..Friday: yield false
        case { it.toString()[0] == 'S' }: yield true
    }
}

assert [Sunday, Monday, Friday].collect{ isWeekend(it) }
        == [true, false, false]
