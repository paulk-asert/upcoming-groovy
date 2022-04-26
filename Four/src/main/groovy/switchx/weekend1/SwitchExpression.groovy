package switchx.weekend1

enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

import static Day.*
def isWeekend(Day d) {
    return switch(d) {
        case Monday..Friday -> false
        case [Sunday, Saturday] -> true
    }
}

assert [Sunday, Monday, Friday].collect{ isWeekend(it) }
        == [true, false, false]
