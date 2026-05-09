package com.udacity.webcrawler.parser;

import com.udacity.webcrawler.Timeout;
import com.udacity.webcrawler.profiler.Profiler;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import javax.inject.Inject;

final class PageParserFactoryImpl implements PageParserFactory {
private final Profiler profiler;
private final List<Pattern> ignoredWords;
private final Duration timeout;

@Inject
PageParserFactoryImpl(
Profiler profiler, @IgnoredWords List<Pattern> ignoredWords, @Timeout Duration timeout) {
this.profiler = profiler;
this.ignoredWords = ignoredWords;
this.timeout = timeout;
}

@Override
public PageParser get(String url) {
PageParser delegate = new PageParserImpl(url, timeout, ignoredWords);
return profiler.wrap(PageParser.class, delegate);
}
}
