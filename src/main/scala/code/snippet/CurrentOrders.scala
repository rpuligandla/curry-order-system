package code.snippet

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import Helpers._
import net.liftweb.http.js.{JsCmd, JsCmds}
import xml.NodeSeq
import net.liftweb.http.js.JsCmds.Replace
import net.liftweb.http.{S, RequestVar, TemplateFinder, SHtml}
import code.model.{User, Order, Curry, Heat}
import net.liftweb.mapper.{By, By_>, Descending}

class CurrentOrders {
  val orders = Order.findAll(By_>(Order.timeStamp, new java.util.Date(new java.util.Date().getTime - (3*24*60*60*1000))))
  
  def list = ".order" #> rows & ClearClearable
//  def order: (NodeSeq => NodeSeq) = ".orders" #>  & ClearClearable

  def rows: (NodeSeq => NodeSeq) = (ns: NodeSeq) => orders.flatMap(row(_).apply(ns))

  def row(o: Order) = ".name *" #> (User.find(By(User.id, o.user.is)).map(_.realName.is) openOr "?") &
          ".curry *" #> (o.curry.obj.map(_.name.is) openOr "?") &
          ".heat *" #> o.heat
}
