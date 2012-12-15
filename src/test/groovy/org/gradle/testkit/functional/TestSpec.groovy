/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package org.gradle.testkit.functional

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testkit.functional.internal.ToolingApiGradleRunner
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.exporter.ZipExporter
import org.jboss.shrinkwrap.api.spec.JavaArchive
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class TestSpec extends Specification {

    @Rule TemporaryFolder tmp


    def "test thing"() {
        given:
        tmp.newFile("build.gradle") << """
            apply plugin: ${SomePlugin.name}
        """

        File jar = tmp.newFile("myPackage.jar")
        ShrinkWrap.create(JavaArchive).
                addClass(SomePlugin).
                as(ZipExporter).
                exportTo(jar, true)

        def initScript = tmp.newFile("init.gradle") << """
            allprojects {
                buildscript {
                    dependencies {
                        classpath files("${jar.absolutePath}")
                    }
                }
            }
        """

        when:
        GradleRunner runner = new ToolingApiGradleRunner()
        runner.directory = tmp.root
        runner.arguments << "echo" << "-I" << initScript.absolutePath

        then:
        runner.run().standardOutput.contains("I ran!")
    }
}

class SomePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task("echo") {
            doLast {
                println "I ran!"
            }
        }

    }
}
