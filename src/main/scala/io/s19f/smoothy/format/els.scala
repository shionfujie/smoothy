package io.s19f.smoothy.format

import io.s19f.x.strings._
import org.jsoup.nodes.Element


/**
  * One responsible for formatting elements
  */
object els {

  def listItem(el: Element) = {
    var title   = el.text.trim.title
    if (el.normalName == "a") {
        title = md.link(title, el.absUrl("href"))
    }
    md.listItem(title)
  }

  def listItems(els: Traversable[Element]) =
    listItemsDepths(els.map((0, _)))

  def listItemsDepths(els: Traversable[(Int, Element)]) =
    md.listItemsDepths(for { (depth, el) <- els } yield (depth, listItem(el)))

  def firstSentences(els: Traversable[Element])         =
    els.map(_.text.trim.sentences(0)).mkString("\n")

}
