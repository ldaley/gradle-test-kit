package org.gradle.testkit.functional.internal.classpath;

import org.gradle.api.internal.ErroringAction;
import org.gradle.api.internal.IoActions;
import org.gradle.util.TextUtil;

import java.io.File;
import java.io.Writer;

public class ClasspathAddingInitScriptBuilder {

    public void build(File initScriptFile, final File jarFile) {
        IoActions.writeFile(initScriptFile, new ErroringAction<Writer>() {
            @Override
            protected void doExecute(Writer writer) throws Exception {
                writer.write("allprojects {\n");
                writer.write("  buildscript {\n");
                writer.write("    dependencies {\n");
                writer.write(String.format("      classpath files('%s')\n", TextUtil.escapeString(jarFile.getAbsolutePath())));
                writer.write("    }\n");
                writer.write("  }\n");
                writer.write("}\n");
            }
        });
    }
}
