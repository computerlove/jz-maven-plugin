How to write a maven plugin
===============

## Step four, accessing dependencies and more testing ##
Unless you are making every thing from scratch, your project will most likely have dependencies. Your new task is to 
access the dependency graph, and print all dependencies your project have.
The new module ```verifier-test``` includes a test using [Maven verifier](http://maven.apache.org/shared/maven-verifier) to
look at the log for the plugin execution. 


## What you need to know ##
*@Mojo* have two properties relating to dependencies, *requiresDependencyResolution* and *requiresDependencyCollection*. These 
influence the result we get when calling *getDependencyArtifacts()*(direct dependencies) and *getArtifacts()*(all dependencies, including transitive).

Both *requiresDependencyResolution* and *requiresDependencyCollection* has default value «NONE». 
*requiresDependencyResolution* is used to tell Maven [which classpath](http://maven.apache.org/developers/mojo-api-specification.html) 
we want to have resolved for the project in question before execution. 
The difference between resolution and collection is that resolution concerns only discovering the dependency graph, while collection 
resolves the artifacts and download their files.
So if you are only interested in what the project's dependencies are you only need *requiresDependencyResolution*. If you 
need to access the files for the dependencies you will have to add *requiresDependencyCollection* as well, with the same scope.

Ps. From the javadoc of *getArtifacts()*: «Contents are lazily populated, so depending on what phases have run dependencies in some scopes won't be included. eg. if only compile phase has run, dependencies with scope test won't be included.»

Luckily, in case we want to inspect all dependecies in a early build phase, there is other ways to resolve dependencies.

    @Component(role = ProjectDependenciesResolver.class)
    private ProjectDependenciesResolver projectDependenciesResolver;
    
    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession mavenSession;
    
    public void execute(){
        Set<Artifact> artifacts = projectDependenciesResolver.resolve(project, asList("compile", "runtime"), mavenSession);
    }

In the above snippet we achieve the same result as setting *requiresDependencyResolution=ResolutionScope.COMPILE_PLUS_RUNTIME*. 


Checkout step 5 with ```git checkout step-5 -f```

## Usefull resources ##
* [What's in Maven 3.0 for Plugin Authors?](http://blog.sonatype.com/2010/11/whats-in-maven-3-0-for-plugin-authors)
* [Review of Plugin Testing Strategies](http://docs.codehaus.org/display/MAVENUSER/Review+of+Plugin+Testing+Strategies)
* [Testing maven plugins with the verifier approach](http://blog.akquinet.de/2011/02/21/testing-maven-plugins-with-the-verifier-approach/)
* [How To Use Maven Plugin Testing Harness?](http://maven.apache.org/plugin-testing/maven-plugin-testing-harness/getting-started/index.html)
* []()
* []()