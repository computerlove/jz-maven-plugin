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
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Goal which touches a timestamp file.
 *
 */
@Mojo( name = "source", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class BuildtimeMojo extends AbstractMojo {

    /**
     * Location of target/classes.
     */
    @Parameter( defaultValue = "${project.build.directory}/generated-sources/something", property = "generated-sources", required = true )
    private File outputSourceDirectory;

    @Parameter(property = "project", readonly = true)
    private MavenProject project;


    @Override
    public void execute() throws MojoExecutionException  {
        if ( !outputSourceDirectory.exists() ){
            boolean mkdirsSuccess = outputSourceDirectory.mkdirs();
            if(!mkdirsSuccess){
                getLog().warn("Could not create " + outputSourceDirectory.getAbsolutePath());
            }
        }

        File properties = new File( outputSourceDirectory, "Generated.java" );

        try (FileWriter w = new FileWriter( properties ) ) {
            w.write( "import java.io.IOException;" +
                    "public class Generated implements java.io.Closeable {" +
                    "    public Generated() {" +
                    "        System.out.println(\"Creating\");" +
                    "    }" +
                    "    @Override" +
                    "    public void close() throws IOException {" +
                    "        System.out.println(\"Closing\");" +
                    "    }" +
                    "}" );
        } catch ( IOException e ) {
            throw new MojoExecutionException( "Error creating file " + properties, e );
        }

        project.addCompileSourceRoot(outputSourceDirectory.getPath());
    }
}
