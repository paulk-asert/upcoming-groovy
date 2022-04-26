package sealed.material5

sealed abstract class Material permits Straw, Wood, Brick {
    String warranty() {
        switch(this) {
            case Straw -> 'Use only in light winds'
            case Wood -> 'Okay for light breezes'
            case Brick -> 'Withstands huffing and puffing'
        }
    }
}

class Straw extends Material { }
class Wood extends Material { }
class Brick extends Material { }

def materials = [new Straw(), new Wood(), new Brick()]
assert materials*.warranty() == [
        'Use only in light winds',
        'Okay for light breezes',
        'Withstands huffing and puffing'
]

//class Glass extends Material { }
//new Glass().warranty() // nothing or error
