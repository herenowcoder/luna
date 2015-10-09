# luna


### running the code

`./sbt` then ` ~; container:start; container:reload /`

### deploy via eb

When changing project name or version: `./update_artifact`

Then: `./sbt package && eb deploy`
