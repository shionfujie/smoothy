package io.s19f.smoothy.format

import io.s19f.x.strings._
import org.jsoup.nodes.Element

/** One responsible for formatting elements
  */
object els {

  def listItem(el: Element) =
    el2text _ andThen md.listItem _

  def listItems(els: Traversable[Element]) =
    listItemsDepths(els.map((0, _)))

  def listItemsDepths(els: Traversable[(Int, Element)]) =
    md.listItemsDepths(for { (depth, el) <- els } yield (depth, el2text(el)))

  def firstSentences(els: Traversable[Element])         =
    els.map(_.text.trim.sentences(0)).mkString("\n")

  private def el2text(el: Element) = {
    val title = el.text.trim.title
    el.normalName match {
      case "a" => md.link(title, el.absUrl("href"))
      case "p" => el.text.trim.sentences(0)
      case _   => title
    }
  }

}
