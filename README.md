# luna

[![Build Status](https://travis-ci.org/herenowcoder/luna.svg?branch=master)](https://travis-ci.org/herenowcoder/luna)

### running the code

`./sbt` then ` ~; container:start; container:reload /`

### deploy via eb

When changing project name or version: `./update_artifact`

Then: `./sbt package && eb deploy`
