package io.s19f.smoothy.format

object md {
  def listItem(text: String) = "- " + text

  def listItems(texts: Traversable[String]) =
    texts.map(listItem _).mkString("\n")

  def listItemsDepths(els: Traversable[(Int, String)]) =
    els
      .map {
        case (depth, text) =>
          "  " * depth + listItem(text)
      }
      .mkString("\n")

  def link(text: String, href: String) = s"[${text}](${href})"
}
