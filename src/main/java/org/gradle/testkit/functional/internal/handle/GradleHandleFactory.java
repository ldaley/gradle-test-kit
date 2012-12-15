package org.gradle.testkit.functional.internal.handle;

import java.io.File;

public interface GradleHandleFactory {

    GradleHandle start(File dir, String... arguments);

}
