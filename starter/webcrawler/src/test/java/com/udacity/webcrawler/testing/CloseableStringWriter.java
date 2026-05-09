package com.udacity.webcrawler.testing;

import java.io.IOException;
import java.io.StringWriter;

public final class CloseableStringWriter extends StringWriter {
private boolean closed = false;

@Override
public void close() throws IOException {
if (closed) {
throw new IOException("stream is closed");
}
closed = true;
}

public boolean isClosed() { return closed; }
}
