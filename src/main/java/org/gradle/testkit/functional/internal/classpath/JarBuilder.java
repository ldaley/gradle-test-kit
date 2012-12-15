package org.gradle.testkit.functional.internal.classpath;

import org.gradle.testkit.functional.ClasspathSpec;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import java.io.File;

public class JarBuilder {

    public void build(ClasspathSpec classpathSpec, File jarFileDestination) {
        JavaArchive jarSpec = ShrinkWrap.create(JavaArchive.class);
        for (Class<?> clazz : classpathSpec.getClasses()) {
            jarSpec.addClass(clazz);
        }
        for (Package pkg : classpathSpec.getPackages()) {
            jarSpec.addPackage(pkg);
        }
        for (String resource : classpathSpec.getResources()) {
            jarSpec.addAsResource(resource);
        }

        ZipExporter exporter = jarSpec.as(ZipExporter.class);
        exporter.exportTo(jarFileDestination, true);
    }
}
