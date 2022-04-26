package sealed.material1

abstract class Material {
    abstract String getColor()
    String toString() {
        "${getClass().simpleName} with color ${color.toLowerCase()}"
    }
}

class Straw extends Material { String color = "Yellow" }
class Wood extends Material { String color = "Brown" }
class Brick extends Material { String color = "Red" }

def materials = [new Straw(), new Wood(), new Brick()]
assert materials*.toString() == [
        'Straw with color yellow',
        'Wood with color brown',
        'Brick with color red'
]
