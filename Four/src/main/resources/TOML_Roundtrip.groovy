import groovy.toml.TomlBuilder
import groovy.toml.TomlSlurper

def builder = new TomlBuilder()
builder.records {
    car {
        name 'HSV Maloo'
        make 'Holden'
        year 2006
        country 'Australia'
        homepage new URL('http://example.org')
        record {
            type 'speed'
            description 'production pickup truck 271kph'
        }
    }
}

def ts = new TomlSlurper()
def toml = ts.parseText(builder.toString())

assert 'HSV Maloo' == toml.records.car.name
assert 'Holden' == toml.records.car.make
assert 2006 == toml.records.car.year
assert 'Australia' == toml.records.car.country
assert 'http://example.org' == toml.records.car.homepage
assert 'speed' == toml.records.car.record.type
assert 'production pickup truck 271kph' == toml.records.car.record.description
