package io.s19f
package x
package dynamics

import scala.language.dynamics

class O extends Dynamic {

  private val o = scala.collection.mutable.Map[String, Any]()

  def update(name: String, value: Any) =
    o(name) = value match {
      case f0: Function0[_]                => (_: Unit) => f0()
      case f2: Function2[_, _, _]          => f2.tupled
      case f3: Function3[_, _, _, _]       => f3.tupled
      case f4: Function4[_, _, _, _, _]    => f4.tupled
      case f5: Function5[_, _, _, _, _, _] => f5.tupled
      case f                               => f
    }

  def updateDynamic(name: String)(value: Any) =
    update(name, value)

  def applyDynamic[X, Y](fieldName: String)(arg: X): Y =
    o(fieldName).asInstanceOf[X => Y](arg)

  def selectDynamic[Y](name: String): Y = {
    val value = o(name) match {
      case f0: Function0[_] => f0()
      case x                => x
    }
    value.asInstanceOf[Y]
  }

  override def toString: String =
    o.view.toList.map { case (k, v) => k + ": " + v }.mkString("{", ", ", "}")
}

object O {
  def apply(props: (String, Any)*): O =
    props.foldLeft(new O) {
      case (o, (name, value)) =>
        o(name) = value; o;
    }
}
