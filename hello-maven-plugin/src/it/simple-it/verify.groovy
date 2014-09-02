import org.codehaus.plexus.util.FileUtils

File touchFile = new File( basedir, "target/classes/build.properties" );

assert touchFile.isFile()

def content = FileUtils.fileRead(touchFile)
assert content.contains('build.timestamp')
