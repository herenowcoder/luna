package code.snippet

import scala.xml.NodeSeq
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._
import net.liftweb.http.js.{JsExp,JE}
import net.liftweb.http.js.jquery.JqJE.{Jq,JqAttr}

class Luna {
  val moonpixPrefix = "/imported/luna-ngen/images/"
  val moonpixList = Array("Moonburn_small.jpg", "Golden_Moon_small.jpg")

  def moonpix: CssSel = {
    "a [onclick]" #> moonpixSwitch(moonpixList(1), 1 second, 2 seconds) &
    "img [src]"   #> (moonpixPrefix + moonpixList.head)
  }

  private def moonpixSwitch(newMoonpix: String, fadeOutTime: TimeSpan, fadeInTime: TimeSpan): JsExp = {
    val fade = "$(this).fadeTo"
    JE.Call(fade, fadeOutTime.toMillis, 0.01, JE.AnonFunc(
      JE.Call("$('img', this).attr", "src", moonpixPrefix+newMoonpix).cmd &
      JE.Call(fade, fadeInTime.toMillis, 1.0).cmd
    ))
  }

  def logo: NodeSeq = (
    <p/>
    <span style="font-size:56px; font-family:Tahoma,Arial,sans-serif">
        luna.inthephase
    </span>
    <p/>
  )

  def placeholder: NodeSeq = (
    <p/>
    <div style="font:italic small-caps 72px fantasy; text-align:center; color:#fec">
      Coming Soon!!
    </div>
  )

  def copyright: NodeSeq = (
    <div id="copyright"
      style="font:small-caps 15px normal; text-align:center; color:#ccc">
      © <span style="font-size:80%">2013 </span>
      <a href="http://dualtech.com.pl">Dual Tech</a>.
      Crafted using <span class="link">Vibrant Design</span>™.<br/>
      <a href="http://validator.w3.org/check/referer">Valid</a> Html.
      Powered by awareness.
    </div>
  )
}
