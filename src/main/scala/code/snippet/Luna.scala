package code
package snippet

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._

class Luna {
  
  def moonpix(x: NodeSeq) = x //niy

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
