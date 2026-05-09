package com.udacity.webcrawler.profiler;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import javax.inject.Inject;

public final class FakeClock extends Clock {
private Instant now;
private ZoneId zoneId;

@Inject
public FakeClock() {
this(Instant.now(), ZoneId.systemDefault());
}

public FakeClock(Instant now, ZoneId zoneId) {
this.now = Objects.requireNonNull(now);
this.zoneId = Objects.requireNonNull(zoneId);
}

@Override
public ZoneId getZone() { return zoneId; }

@Override
public Clock withZone(ZoneId zone) { return new FakeClock(now, zone); }

@Override
public Instant instant() { return now; }

public void tick(Duration duration) {
now = now.plus(Objects.requireNonNull(duration));
}

public void setTime(Instant instant) {
this.now = Objects.requireNonNull(instant);
}

public void setZone(ZoneId zoneId) {
this.zoneId = Objects.requireNonNull(zoneId);
}
}
