package com.udacity.webcrawler.parser;

import com.udacity.webcrawler.profiler.Profiled;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface PageParser {
@Profiled
Result parse();

final class Result {
private final Map<String, Integer> wordCounts;
private final List<String> links;

private Result(Map<String, Integer> wordCounts, List<String> links) {
this.wordCounts = Objects.requireNonNull(wordCounts);
this.links = Objects.requireNonNull(links);
}

public Map<String, Integer> getWordCounts() { return wordCounts; }

public List<String> getLinks() { return links; }

static final class Builder {
private final Map<String, Integer> wordCounts = new HashMap<>();
private final Set<String> links = new HashSet<>();

void addWord(String word) {
Objects.requireNonNull(word);
wordCounts.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
}

void addLink(String link) {
links.add(Objects.requireNonNull(link));
}

Result build() {
return new Result(
Collections.unmodifiableMap(wordCounts),
links.stream().collect(Collectors.toUnmodifiableList()));
}
}
}
}
