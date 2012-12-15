package org.gradle.testkit.functional.internal;

import org.gradle.testkit.functional.ClasspathSpec;
import org.gradle.testkit.functional.internal.classpath.ClasspathAddingInitScriptBuilder;
import org.gradle.testkit.functional.internal.classpath.JarBuilder;
import org.gradle.util.GFileUtils;

import java.io.File;

public class ClasspathInjectingGradleHandleFactory implements GradleHandleFactory {

    private final ClasspathSpec classpathSpec;
    private final GradleHandleFactory delegateFactory;

    public ClasspathInjectingGradleHandleFactory(ClasspathSpec classpathSpec, GradleHandleFactory delegateFactory) {
        this.classpathSpec = classpathSpec;
        this.delegateFactory = delegateFactory;
    }

    public GradleHandle start(File directory, String... arguments) {
        File testKitDir = new File(directory, ".gradle-test-kit");
        if (!testKitDir.exists()) {
            GFileUtils.mkdirs(testKitDir);
        }

        File jarFile = new File(testKitDir, "injected.jar");
        new JarBuilder().build(classpathSpec, jarFile);
        File initScript = new File(testKitDir, "init.gradle");
        new ClasspathAddingInitScriptBuilder().build(initScript, jarFile);

        String[] ammendedArguments = new String[arguments.length + 2];
        ammendedArguments[0] = "-I";
        ammendedArguments[1] = initScript.getAbsolutePath();
        System.arraycopy(arguments, 0, ammendedArguments, 2, arguments.length);
        return delegateFactory.start(directory, ammendedArguments);
    }

}
