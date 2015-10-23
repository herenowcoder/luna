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

  def q(s: String) = "\"" + s + "\""

  val jqThis: JsExp = JsRaw("$(this)")
  def jqThisWith(selector: String): JsExp = JsRaw(s"$$(${q(selector)},this)")

  def fade(time: TimeSpan, toVal: Double,
      callback: Option[JsAnonFunc] = None): JsMember = {
    callback match {
      case None => JsFunc("fadeTo", time toMillis, toVal)
      case Some(f) => JsFunc("fadeTo", time toMillis, toVal, f)
    }
  }

  def pageBg(rgb: String): JsExp =
    JsRaw(s"document.body.style.background=${q(rgb)}")
}

class Luna {
  import JqGoodies._

  val moonpixPrefix = "/imported/luna-ngen/images/"

  abstract trait Pic {
    def unfold() = moonpixP(this)
  }
  case class PicBlack(name: String) extends Pic
  case class PicWithBg(name: String, bg: String) extends Pic

  val moonpixTab = Array(
    PicBlack("Moonburn"), PicBlack("Golden_Moon"),
    PicBlack("Lunar_eclipse_June_2011"), PicBlack("Solar_eclipse_1999"),
    PicWithBg("Solar_eclipse_May_2013", "#130101")
  )

  private def moonpixP(pic: Pic): (String, String) = {
    val (name, bg) = pic match {
      case PicBlack(name) => (name, "#000")
      case PicWithBg(name, bg) => (name, bg)
    }
    (s"${name}_small.jpg", bg)
  }

  // snippet impl changed to val as (for now) this needs to get evaluated
  // only once
  val moonpix: CssSel = {
    val selector: JsExp = Jq("#moonbox a")
    val (pixFile, bg) = moonpixTab(4).unfold
    S.appendJs(
      pageBg(bg).cmd &
      (selector ~> fade(4 seconds, 1.0)).cmd
    )
    "a [style]"   #> "opacity: 0.01" &
    "a [onclick]" #> moonpixSwitch(moonpixTab(0), 1 second, 2 seconds) &
    "img [src]"   #> (moonpixPrefix + pixFile)
  }

  /* moonpix todo:
      + change background if pix requires it
      - simplify Pic (just class with default bg param in ctor)
      - really get random pics on each click
      - switch pics without clicking, via some semi-randomized timeout
      - refactor
      - to be classy & elegant, bigger refactor to have sth like "emerge" element
        in Smalltalk ver - then share code of first run and subsequent runs
        (requires switching via jqReplace or similar)
  */
  private def moonpixSwitch(newMoonpix: Pic,
    fadeOutTime: TimeSpan, fadeInTime: TimeSpan): JsExp = {
    val selector = jqThis
    val innerSelector = jqThisWith("img")
    val (newFile, newBg) = newMoonpix.unfold
    selector ~> fade(fadeOutTime, 0.01, Some(JsAnonFunc(
      pageBg(newBg).cmd &
      (innerSelector ~> JqAttr("src", moonpixPrefix + newFile)).cmd &
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
