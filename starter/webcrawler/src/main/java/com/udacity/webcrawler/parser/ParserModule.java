package com.udacity.webcrawler.parser;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class ParserModule extends AbstractModule {
private final Duration timeout;
private final List<Pattern> ignoredWords;

private ParserModule(Duration timeout, List<Pattern> ignoredWords) {
this.timeout = timeout;
this.ignoredWords = ignoredWords;
}

@Override
protected void configure() {
bind(Key.get(Duration.class, ParseDeadline.class)).toInstance(timeout);
bind(new Key<List<Pattern>>(IgnoredWords.class) {}).toInstance(ignoredWords);
bind(PageParserFactory.class).to(PageParserFactoryImpl.class);
}

public static final class Builder {
private Duration timeout;
private List<Pattern> ignoredWords;

public Builder setTimeout(Duration timeout) {
this.timeout = Objects.requireNonNull(timeout);
return this;
}

public Builder setIgnoredWords(List<Pattern> ignoredWords) {
this.ignoredWords = Objects.requireNonNull(ignoredWords);
return this;
}

public ParserModule build() { return new ParserModule(timeout, ignoredWords); }
}
}
