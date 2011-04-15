/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.plugins.ide.eclipse.model

/**
 * DSL-friendly model of the eclipse project needed for .project generation
 * <p>
 * Example of use with a blend of all possible properties.
 * Bear in mind that usually you don't have configure eclipse project directly because Gradle configures it for free!
 *
 * <pre autoTested=''>
 * apply plugin: 'java'
 * apply plugin: 'eclipse'
 *
 * eclipse {
 *   project {
 *     //if you don't like the name Gradle has chosen
 *     name = 'someBetterName'
 *
 *     //if you want to specify the Eclipse project's comment
 *     comment = 'Very interesting top secret project'
 *
 *     //(TODO SF: what are referenced projects?)
 *     //if you want to append some extra referenced projects in a declarative fashion:
 *     referencedProjects 'someProject', 'someOtherProject'
 *
 *     //if you want to assign referenced projects
 *     referencedProjects = ['someProject'] as Set
 *
 *     //if you want to append some extra natures in a declarative fashion:
 *     natures 'some.extra.eclipse.nature', 'some.another.interesting.nature'
 *
 *     //if you want to assign natures in a groovy fashion:
 *     natures = ['some.extra.eclipse.nature', 'some.another.interesting.nature']
 *
 *   }
 * }
 * </pre>
 *
 * Author: Szczepan Faber, created at: 4/13/11
 */
class EclipseProject {

    /**
     * Configures eclipse project name. It is <b>optional</b> because the task should configure it correctly for you.
     * By default it will try to use the <b>project.name</b> or prefix it with a part of a <b>project.path</b>
     * to make sure the moduleName is unique in the scope of a multi-module build.
     * The 'uniqeness' of a module name is required for correct import
     * into Eclipse and the task will make sure the name is unique.
     * <p>
     * The logic that makes sure project names are uniqe is available <b>since</b> 1.0-milestone-2
     * <p>
     * For example see docs for {@link EclipseProject}
     */
    String name

    /**
     * A comment used for the eclipse project. By default it will be configured to <b>project.description</b>
     * <p>
     * For example see docs for {@link EclipseProject}
     */
    String comment

    /**
     * The referenced projects of this Eclipse project.
     * <p>
     * For example see docs for {@link EclipseProject}
     */
    Set<String> referencedProjects = new LinkedHashSet<String>()

    /**
     * Adds project references to the eclipse project.
     *
     * @param referencedProjects The name of the project references.
     */
    void referencedProjects(String... referencedProjects) {
        assert referencedProjects != null
        this.referencedProjects.addAll(referencedProjects as List)
    }

    /**
     * The natures to be added to this Eclipse project.
     * <p>
     * For example see docs for {@link EclipseProject}
     */
    List<String> natures = []

    /**
     * Appends natures entries to the eclipse project.
     * <p>
     * For example see docs for {@link EclipseProject}
     *
     * @param natures the nature names
     */
    void natures(String... natures) {
        assert natures != null
        this.natures.addAll(natures as List)
    }
}
