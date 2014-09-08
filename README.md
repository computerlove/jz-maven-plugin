How to write a maven plugin
===============

## About ##
[Maven plugin](http://maven.apache.org/plugin-developers/index.html) workshop at [Javazone](javazone.no) 2014.

Clone this repo:
```git@github.com:kantega/jz-maven-plugin.git```

## Requirements ##

* Git 1.7.10+
* Apache Maven 3.x
* JVM 7.x
* A decent IDE, like IDEA, Eclipse

## The role of plugins in Maven ##
Everything that is done during the Maven-build is done in [plugins](http://maven.apache.org/plugins/index.html). 
Processing of resources are done with [Maven Resources Plugin](http://maven.apache.org/plugins/maven-resources-plugin/),
Compiling of sources are done with [Maven Compiler Plugin](http://maven.apache.org/plugins/maven-compiler-plugin/),
Assembly of jar and war files are done with [Maven JAR Plugin](http://maven.apache.org/plugins/maven-jar-plugin/) and 
[Maven WAR Plugin](http://maven.apache.org/plugins/maven-war-plugin/)…

Through Maven's plugin api we can hook into any [phase](https://maven.apache.org/plugin-tools/maven-plugin-annotations/apidocs/org/apache/maven/plugins/annotations/LifecyclePhase.html) of the build in order to do our own tasks.

## Where can I find documentation? ##
You can try looking around, starting with [Plugin Developers Centre](http://maven.apache.org/plugin-developers/index.html). But unless you are trying to do 
something that is really trivial you will probably not find what you need.

The plugin api is poorly documented, so you may need to find some other plugin that does something similar and see how it accomplishes its task, then use that as inspiration. 
Or you may have to find a part of the api that sounds like it does something you want, and experiment with it.

The plugin api has changed a lot between Maven 2 and Maven 3, so other plugins you find may be using a old version of the plugin api. 
These plugins will run fine, but when looking at the code you will se that many api classes used are marked as deprecated.
The problem with this is that there is usually no description of why a particular class or method is deprecated, or what 
replaces it. 

## Enough ranting, lets start hacking! ##
This workshop is based on one branch per step. To get started with step one execute ```git checkout step-1 -f``` 

