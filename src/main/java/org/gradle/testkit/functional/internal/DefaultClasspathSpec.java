package org.gradle.testkit.functional.internal;

import org.gradle.testkit.functional.ClasspathSpec;

import java.util.HashSet;
import java.util.Set;

public class DefaultClasspathSpec implements ClasspathSpec {

    private final Set<Class> classes = new HashSet<Class>();
    private final Set<Package> packages = new HashSet<Package>();
    private final Set<String> resources = new HashSet<String>();

    public Set<Class> getClasses() {
        return classes;
    }

    public Set<Package> getPackages() {
        return packages;
    }

    public Set<String> getResources() {
        return resources;
    }
}
