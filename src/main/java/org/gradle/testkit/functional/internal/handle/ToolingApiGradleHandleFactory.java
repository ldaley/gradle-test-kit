package org.gradle.testkit.functional.internal.handle;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class ToolingApiGradleHandleFactory implements GradleHandleFactory {

    public GradleHandle start(File directory, String... arguments) {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(directory);
        ProjectConnection connection = connector.connect();
        BuildLauncher launcher = connection.newBuild();
        launcher.withArguments(arguments);
        return new BuildLauncherBackedGradleHandle(launcher);
    }
}
