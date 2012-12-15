package org.gradle.testkit.functional;

import org.gradle.testkit.functional.internal.DefaultGradleRunnerBuilder;

public abstract class GradleRunnerBuilder {

    public static GradleRunnerBuilder builder() {
        return new DefaultGradleRunnerBuilder();
    }

    public abstract GradleRunner build();

}
