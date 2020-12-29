package io.s19f

package object smoothy {

  import scala.collection.generic.CanBuildFrom
  import org.jsoup.nodes.Element

  val ranks = Map(
    "h1"     -> 0,
    "h2"     -> 1,
    "h3"     -> 2,
    "h4"     -> 3,
    "h5"     -> 4,
    "h6"     -> 5,
    "span"   -> 6,
    "strong" -> 6,
    "a"      -> 6,
    "li"     -> 6,
    "p"      -> 6
  )

  def withDepths[Repr <: Seq[Element], That](els: Repr)(implicit cbf: CanBuildFrom[Repr, (Int, Element), That]): That = {
    val b = cbf(els); b.sizeHint(els)

    var hierarchy = List.empty[Int]
    b ++= els.map { el =>
      val thisRank = ranks(el.normalName)
      hierarchy = thisRank :: hierarchy.dropWhile(thatRank => thisRank <= thatRank)
      val depth    = hierarchy.length - 1
      (depth, el)
    }

    b.result()
  }

}
