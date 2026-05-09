package com.udacity.webcrawler;

import static com.google.common.truth.Truth.assertThat;

import com.google.inject.Guice;
import com.udacity.webcrawler.json.CrawlerConfiguration;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

public final class SequentialWebCrawlerTest {
@Inject private SequentialWebCrawler sequentialWebCrawler;

@Test
public void testMaxParallelism() {
CrawlerConfiguration config = new CrawlerConfiguration.Builder().build();
Guice.createInjector(new WebCrawlerModule(config), new NoOpProfilerModule())
.injectMembers(this);
assertThat(sequentialWebCrawler.getMaxParallelism()).isEqualTo(1);
}
}
