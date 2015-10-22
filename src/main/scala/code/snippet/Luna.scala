package code.snippet

import scala.xml.NodeSeq
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._
import net.liftweb.http.S
import net.liftweb.http.js.JsExp
import net.liftweb.http.js.JE.{AnonFunc => JsAnonFunc} 
import net.liftweb.http.js.jquery.JqJE.{Jq,JqAttr}

object JqGoodies {
  import net.liftweb.http.js.{JsExp,JsMember}
  import net.liftweb.http.js.JE.{JsFunc,JsRaw}

  val jqThis: JsExp = JsRaw("$(this)")
  def jqThisWith(selector: String): JsExp = JsRaw(s"""$$("$selector",this)""")

  def fade(time: TimeSpan, toVal: Double,
      callback: Option[JsAnonFunc] = None): JsMember = {
    callback match {
      case None => JsFunc("fadeTo", time toMillis, toVal)
      case Some(f) => JsFunc("fadeTo", time toMillis, toVal, f)
    }
  }
}

class Luna {
  import JqGoodies._

  val moonpixPrefix = "/imported/luna-ngen/images/"
  val moonpixList = Array("Moonburn_small.jpg", "Golden_Moon_small.jpg")

  // snippet impl changed to val as (for now) this needs to get evaluated
  // only once
  val moonpix: CssSel = {
    val selector: JsExp = Jq("#moonbox a")
    S.appendJs(
      selector ~> fade(4 seconds, 1.0) cmd
    )
    "a [style]"   #> "opacity: 0.01" &
    "a [onclick]" #> moonpixSwitch(moonpixList(1), 1 second, 2 seconds) &
    "img [src]"   #> (moonpixPrefix + moonpixList.head)
  }

  /* moonpix todo:
      - change background if pix requires it
      - really get random pics on each click
      - switch pics without clicking, via some semi-randomized timeout
      - refactor
      - to be classy & elegant, bigger refactor to have sth like "emerge" element
        in Smalltalk ver - then share code of first run and subsequent runs
        (requires switching via jqReplace or similar)
  */
  private def moonpixSwitch(newMoonpix: String,
    fadeOutTime: TimeSpan, fadeInTime: TimeSpan): JsExp = {
    val selector = jqThis
    val innerSelector = jqThisWith("img")
    selector ~> fade(fadeOutTime, 0.01, Some(JsAnonFunc(
      (innerSelector ~> JqAttr("src", moonpixPrefix + newMoonpix)).cmd &
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
