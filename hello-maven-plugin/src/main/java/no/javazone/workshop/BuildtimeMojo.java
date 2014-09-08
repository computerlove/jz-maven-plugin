package sample.plugin;

/*
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

import org.apache.maven.ProjectDependenciesResolver;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import static java.util.Arrays.asList;

/**
 * Goal which lists all dependencies
 *
 */
@Mojo( name = "dependencies", requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class BuildtimeMojo extends AbstractMojo {

    @Component
    private MavenProject project;

    @Component
    private ProjectDependenciesResolver projectDependenciesResolver;

    @Component
    private MavenSession mavenSession;

    @Override
    public void execute() throws MojoExecutionException  {
        for (Artifact dependencyArtifact : project.getArtifacts()) {
            getLog().info(dependencyArtifact.toString());
        }
        // or like this:
        try {
            for (Artifact a : projectDependenciesResolver.resolve(project, asList("compile", "runtime"), mavenSession)) {
                getLog().info(a.toString());
            }
        } catch (ArtifactNotFoundException | ArtifactResolutionException e) {
            throw new MojoExecutionException("Error resolving dependencies", e);
        }

    }
}
