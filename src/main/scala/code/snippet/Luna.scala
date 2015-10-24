package code.snippet

import scala.xml.NodeSeq
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._
import net.liftweb.http.S
import net.liftweb.http.js.JsExp
import net.liftweb.http.js.JE.{AnonFunc => JsAnonFunc} 
import net.liftweb.http.js.jquery.JqJE.{Jq,JqAttr}

import code.lib.JsGoodies._

class Luna {

  class Moonpic(name: String, var bg: String) {
    val moonpicPrefix = "/imported/luna-ngen/images/"
    def path = moonpicPrefix + s"${name}_small.jpg"
  }
  object Pic {
    def apply(name: String, bg: String = "#000") = new Moonpic(name, bg)
  }

  val moonpicTab = Array(
    Pic("Moonburn"), Pic("Golden_Moon"),
    Pic("Lunar_eclipse_June_2011"), Pic("Solar_eclipse_1999"),
    Pic("Solar_eclipse_May_2013", "#130101")
  )

  // snippet impl changed to val as (for now) this needs to get evaluated
  // only once
  val moonpic: CssSel = {
    val selector: JsExp = Jq("#moonbox a")
    val startingPic = moonpicTab(4)
    S.appendJs(
      pageBg(startingPic bg).cmd &
      (selector ~> fade(4 seconds, 1.0)).cmd
    )
    "img [src]"   #> startingPic.path &
    "a [style]"   #> "opacity: 0.01" &
    "a [onclick]" #> moonpicSwitch(moonpicTab(0), 1 second, 2 seconds)
  }

  /* moonpic todo:
      + change background if pix requires it
      + simplify Moonpic (just class with default bg param in ctor)
      - "sinusoidal" bg change between current and next
        via setInterval()/clearInterval()
      - really get random pics on each click
      - switch pics without clicking, via some semi-randomized timeout
      - refactor
      - to be classy & elegant, bigger refactor to have sth like "emerge" element
        in Smalltalk ver - then share code of first run and subsequent runs
        (requires switching via jqReplace or similar)
  */
  private def moonpicSwitch(newPic: Moonpic,
    fadeOutTime: TimeSpan, fadeInTime: TimeSpan): JsExp = {
    val selector = jqThis
    val innerSelector = jqThisWith("img")
    selector ~> fade(fadeOutTime, 0.01, Some(JsAnonFunc(
      pageBg(newPic bg).cmd &
      (innerSelector ~> JqAttr("src", newPic path)).cmd &
      (selector ~> fade(fadeInTime, 1.0)).cmd
    )))
  }

  val logo: NodeSeq = (
    <p/>
    <span style="font-size:56px; font-family:Tahoma,Arial,sans-serif">
        luna.inthephase
    </span>
    <p/>
  )

  val placeholder: NodeSeq = (
    <p/>
    <div style=
      "font:italic small-caps 72px fantasy; text-align:center; color:#fec">
      Coming Soon!!
    </div>
  )

  val copyright: NodeSeq = (
    <div id="copyright"
      style="font:small-caps 15px normal; text-align:center; color:#ccc">
      © <span style="font-size:80%">2015 </span>
      <a href="http://dualtech.com.pl">Dual Tech</a>.
      Crafted using <span class="link">Vibrant Design</span>™.<br/>
      <a href="http://validator.w3.org/check/referer">Valid</a> Html.
      Powered by awareness.
    </div>
  )
}
