package org.gradle.testkit.functional.internal;

import org.gradle.testkit.functional.GradleRunner;
import org.gradle.testkit.functional.GradleRunnerBuilder;

public class DefaultGradleRunnerBuilder extends GradleRunnerBuilder {

    @Override
    public GradleRunner build() {
        return new ToolingApiGradleRunner();
    }

}
