sealed abstract class Material permits Straw, Wood, Brick {
    abstract String getColor()
    String toString() {
        "${getClass().name} with color ${color.toLowerCase()}"
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

//class Glass extends Material {
//    String color = null // transparent
//}
