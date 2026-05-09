package com.udacity.webcrawler.profiler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

final class ProfilingMethodInterceptor implements InvocationHandler {
private final Clock clock;
private final ProfilingState state;
private final Object delegate;

ProfilingMethodInterceptor(Clock clock, ProfilingState state, Object delegate) {
this.clock = Objects.requireNonNull(clock);
this.state = Objects.requireNonNull(state);
this.delegate = Objects.requireNonNull(delegate);
}

@Override
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
if (method.getDeclaringClass().equals(Object.class) && method.getName().equals("equals")) {
return invokeDelegate(method, args);
}
if (!method.isAnnotationPresent(Profiled.class)) {
return invokeDelegate(method, args);
}
Instant start = clock.instant();
try {
return invokeDelegate(method, args);
} finally {
state.record(delegate.getClass(), method, Duration.between(start, clock.instant()));
}
}

private Object invokeDelegate(Method method, Object[] args) throws Throwable {
try {
return method.invoke(delegate, args);
} catch (InvocationTargetException e) {
throw e.getCause();
} catch (IllegalAccessException e) {
throw new IllegalStateException(e);
}
}
}
