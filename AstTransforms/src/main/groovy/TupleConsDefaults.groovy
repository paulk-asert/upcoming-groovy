import groovy.transform.TupleConstructor

@TupleConstructor(defaults = false)
class Flight {
    //Flight(String fromCity, String toCity, Date leaving){/* ... */}
    String fromCity, toCity
    Date leaving
}

@TupleConstructor(defaults = true)
class Cruise {
    //Cruise(String fromPort=null, String toPort=null, Date leaving=null){/* ... */}
    String fromPort, toPort
    Date leaving
}

println Flight.constructors.join('\n')
println Cruise.constructors.join('\n')

class MyPoint {
    int x, y

    MyPoint(int x = 40, int y = 50) {
        this.x = x
        this.y = y
    }
/*
    MyPoint(int x, int y) {
        this.x = x
        this.y = y
    }

    MyPoint(int x) {
        this (x, 50)
    }

    MyPoint() {
        this (40, 50)
    }
/* */
}
