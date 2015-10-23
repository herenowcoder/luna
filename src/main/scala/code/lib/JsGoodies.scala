package code.lib

import net.liftweb.util.Helpers.TimeSpan
import net.liftweb.http.js.{JsExp,JsMember}
import net.liftweb.http.js.JE.{JsFunc,JsRaw}
import net.liftweb.http.js.JE.{AnonFunc => JsAnonFunc}

object JsGoodies {

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
