package no.javazone.workshop;

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.Test;

import java.io.File;

public class DependencyPluginTest {

    @Test
    public void shouldPrintDependencies() throws Exception {
        File testDir = ResourceExtractor.simpleExtractResources(getClass(), "/plugin");

        /*
         * We must first make sure that any artifact created
         * by this test has been removed from the local
         * repository. Failing to do this could cause
         * unstable test results. Fortunately, the verifier
         * makes it easy to do this.
         */
        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        verifier.deleteArtifact( "sample.plugin.it", "simple-it", "1.0-SNAPSHOT", "jar" );

        verifier.executeGoal("hello-maven-plugin:dependencies");

        verifier.verifyErrorFreeLog();
        verifier.verifyTextInLog("org.apache.httpcomponents");
        verifier.verifyTextInLog("httpclient");

        verifier.verifyTextInLog("org.apache.httpcomponents");
        verifier.verifyTextInLog("httpcore");

        verifier.verifyTextInLog("commons-logging");

        verifier.verifyTextInLog("commons-codec");
    }


}
