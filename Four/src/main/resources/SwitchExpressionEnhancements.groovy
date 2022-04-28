enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

import static Day.*
def isWeekend(Day d) {
    return switch(d) {
        case Monday..Friday -> false
        case [Sunday, Saturday] -> true
    }
}

def days = [Sunday, Monday, Friday]
assert days.collect(this::isWeekend) == [true, false, false]
