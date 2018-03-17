class FromResource extends ByteArrayInputStream {
    boolean closed = false

    @Override
    void close() throws IOException {
        super.close()
        closed = true
    }

    FromResource(String input) {
        super(input.bytes)
    }
}

class ToResource extends ByteArrayOutputStream {
    boolean closed = false

    @Override
    void close() throws IOException {
        super.close()
        closed = true
    }
}

try(
        FromResource from = new FromResource("ARM was here!")
        ToResource to = new ToResource()
) {
    to << from
} finally {
    assert from.closed
    assert to.closed
    assert to.toString() == 'ARM was here!'
}
