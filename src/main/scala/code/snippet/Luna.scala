package code.snippet

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JE
import net.liftweb.http.js.JsCmds.{SetHtml} // needed very soon

class Luna {
  
  def moonpix = {
    "a [onclick]" #> moonpix_fade
  }

  private def moonpix_fade = {
    val fade = "$(this).fadeTo"
    JE.Call(fade, 1000, 0.01, JE.AnonFunc(
      JE.Call(fade, 2000, 1.0).cmd
    ))
  }

  def logo = (
    <p/>
    <span style="font-size:56px; font-family:Tahoma,Arial,sans-serif">
        luna.inthephase
    </span>
    <p/>
  )

  def placeholder = (
    <p/>
    <div style="font:italic small-caps 72px fantasy; text-align:center; color:#fec">
      Coming Soon!!
    </div>
  )

  def copyright = (
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
