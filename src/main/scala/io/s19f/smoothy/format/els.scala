package io.s19f.smoothy.format

import io.s19f.x.strings._
import org.jsoup.nodes.Element

object els {

  def listItem(el: Element) = {
    val indent = "  "
    val text   = el.text.trim.title
    el.normalName match {
      case "a" => md.listItem(md.link(text, el.absUrl("href")))
      case _   => md.listItem(text)
    }
  }

  def listItems(els: Traversable[Element]) =
    els.map(listItem _).mkString("\n")

  def listItemsDepths(els: Traversable[(Int, Element)]) =
    (for { (depth, el) <- els } yield "  " * depth + listItem(el)).mkString("\n")

  def firstSentences(els: Traversable[Element])         =
    els.map(_.text.trim.sentences(0)).mkString("\n")

}
