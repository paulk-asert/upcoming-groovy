package contracts

import groovy.contracts.*

@Invariant({ speed() >= 0 })
class Rocket {
    int speed = 0
    boolean started = true

    @Requires({ isStarted() })
    @Ensures({ old.speed < speed })
    def accelerate(inc) { speed += inc }

    def isStarted() { started }

    def speed() { speed }
}

def r = new Rocket()
r.accelerate(5)
