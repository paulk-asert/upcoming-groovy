import groovy.transform.MapConstructor
import groovy.transform.ToString

@ToString(includeNames = true)
@MapConstructor
class Conference {
    String name
    String city
    Date start
}

println new Conference(name: 'Gr8confUS',
        city: 'Minneapolis',
        start: new Date() - 2)
println Conference.constructors

//Conference(name:Gr8confUS, city:Minneapolis, start:Wed Jul 26 ...)
//[public Conference(java.util.Map)]
