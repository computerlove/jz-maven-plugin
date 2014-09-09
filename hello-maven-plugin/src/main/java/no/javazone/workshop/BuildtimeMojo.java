package no.javazone.workshop;

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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Goal that generates java, and add it as source dir.
 *
 */
@Mojo( name = "source", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class BuildtimeMojo extends AbstractMojo {

    /**
     * Location of target/classes.
     */
    @Parameter( defaultValue = "${project.build.directory}/generated-sources/something", property = "generated-sources", required = true )
    private File outputSourceDirectory;

    @Override
    public void execute() throws MojoExecutionException  {
        if ( !outputSourceDirectory.exists() ){
            boolean mkdirsSuccess = outputSourceDirectory.mkdirs();
            if(!mkdirsSuccess){
                getLog().warn("Could not create " + outputSourceDirectory.getAbsolutePath());
            }
        }

        // Get hold of reference to the building project
        // Add outputSourceDirectory as CompileSourceRoot
        // Generate class «Generated» with no package, implementing java.io.Closeable.
        // run mvn install -P run-its
    }
}
