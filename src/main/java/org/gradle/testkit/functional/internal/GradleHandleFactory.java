package org.gradle.testkit.functional.internal;

import java.io.File;

public interface GradleHandleFactory {

    GradleHandle start(File dir, String... arguments);

}
