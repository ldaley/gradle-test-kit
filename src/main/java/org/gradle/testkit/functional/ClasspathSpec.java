package org.gradle.testkit.functional;

import java.util.Set;

public interface ClasspathSpec {

    Set<Class> getClasses();

    Set<Package> getPackages();

    Set<String> getResources();

}
