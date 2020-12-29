package io.s19f.smoothy.format

import io.s19f.x.strings._
import org.jsoup.nodes.Element

/** One responsible for formatting elements
  */
object els {

  def listItem(el: Element) =
    defaultListItemFormatter

  def listItems(els: Traversable[Element], formatter: PartialFunction[Element, String] = Map.empty): String =
    listItemsDepths(els.map((0, _)), formatter)

  def listItemsDepths(els: Traversable[(Int, Element)], formatter: PartialFunction[Element, String] = Map.empty) =
    indent(for { (depth, el) <- els } yield (depth, (formatter orElse defaultListItemFormatter)(el)))

  def firstSentences(els: Traversable[Element]) =
    els.map(_.text.trim.sentences(0)).mkString("\n")

  private val defaultFormatter: PartialFunction[Element, String] = {
    case el =>
      val title = el.text.trim.title
      el.normalName match {
        case "a" => md.link(title, el.absUrl("href"))
        case "p" => el.text.trim.sentences(0)
        case _   => title
      }
  }

  private val defaultListItemFormatter =
    defaultFormatter andThen md.listItem

  private def indent(els: Traversable[(Int, String)]) =
    (for ((depth, text) <- els) yield "  " * depth + text).mkString("\n")

}
