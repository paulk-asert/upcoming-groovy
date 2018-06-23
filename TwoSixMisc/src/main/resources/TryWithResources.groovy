class FromResource extends ByteArrayInputStream {
    @Override
    void close() throws IOException {
        super.close()
        println "FromResource closing"
    }

    FromResource(String input) {
        super(input.toLowerCase().bytes)
    }
}

class ToResource extends ByteArrayOutputStream {
    @Override
    void close() throws IOException {
        super.close()
        println "ToResource closing"
    }
}

def wrestle(s) {
    try (
            FromResource from = new FromResource(s)
            ToResource to = new ToResource()
    ) {
        to << from
        return to.toString()
    }
}

assert wrestle("ARM was here!").contains('arm')
// output:
//ToResource closing
//FromResource closing

def wrestle2(s) {
    new FromResource(s).withCloseable { from ->
        new ToResource().withCloseable { to ->
            to << from
            return to.toString()
        }
    }
}

assert wrestle2("ARM was here!").contains('arm')
