package com.udacity.webcrawler;

import static com.google.common.truth.Truth.assertThat;

import com.google.inject.Guice;
import com.udacity.webcrawler.json.CrawlerConfiguration;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

public final class ParallelWebCrawlerTest {
@Inject private ParallelWebCrawler parallelWebCrawler;

@Test
public void testMaxParallelism() {
CrawlerConfiguration config = new CrawlerConfiguration.Builder().build();
Guice.createInjector(new WebCrawlerModule(config), new NoOpProfilerModule())
.injectMembers(this);
assertThat(parallelWebCrawler.getMaxParallelism()).isGreaterThan(1);
}
}
