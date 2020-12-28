package io.s19f.smoothy.format

object md {
    
  def listItem(text: String) = "- " + text

  def listItems(texts: Traversable[String]) =
    listItemsDepths(texts.map((0, _)))

  def listItemsDepths(els: Traversable[(Int, String)]) =
    (for ((depth, text) <- els) yield "  " * depth + listItem(text)).mkString("\n")

  def link(text: String, href: String) = s"[${text}](${href})"

}
