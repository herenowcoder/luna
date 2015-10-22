package code.snippet

import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class MyHead {
  val metadata: CssSel = {
    "title *" #> "luna.inthephase lifted" &
    "@author      [content]" #> "Dual Tech" &
    "@description [content]" #> "All things lunar." &
    "@keywords    [content]" #>
      """moon, moon phase, luna, lunar, luna in the phase,
      moon in the phase, in the phase"""
  }
}
