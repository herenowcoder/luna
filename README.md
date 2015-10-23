# luna

[ ![Codeship Status](https://codeship.com/projects/59cbf990-5be9-0133-9599-42612c8c8541/status?branch=master)](https://codeship.com/projects/110870)

### running the code

`./sbt` then ` ~; container:start; container:reload /`

### deploy via eb

When changing project name or version: `./update_artifact`

Then: `./sbt package && eb deploy`
