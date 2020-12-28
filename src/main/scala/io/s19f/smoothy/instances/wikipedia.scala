package io.s19f
package smoothy
package instances

import x.strings._
import x.regex._
import x.functions.predicates._
import format.{md, els}
import jsoup.Jsoup
import jsoup.nodes._
import jsoup.select.Evaluators._

object wikipedia {

  lazy val document = Jsoup.parseURL("https://en.wikipedia.org/wiki/Phonetic_transcription?oldformat=true")

  lazy val mainContent = document.$("div#content div.mw-parser-output")

  def introductoryPart = {
    val paragraphs = mainContent
      .$$(":root > p, div#toc")
      .takeWhile(! $("div#toc")(mainContent))
    val links      = for {
      p <- paragraphs
      a <- p.$$("a:not([href^='#cite'])")
    } yield a
    "Overview" + "\n" +
      els.listItems(links) + "\n\n" +
      els.firstSentences(paragraphs) + "\n"
  }

  def overview = {
    val els = for {
      h        <- mainContent.$$("h2:not(#mw-toc-heading),h3")
      if !"""^(:?References|Further reading|Bibliography|Notes)""".r.test(h.text.trim)
      listItems = for {
        el <- h.nextElementSiblings
          .takeWhile(! $("h2,h3")(h))
        if el.normalName == "ul"
        ch <- el.$$(":root > li > a") ++ el.$$(":root > li")
        if ch.text.trim.nonEmpty
      } yield ch
      el       <- h +: listItems
    } yield el

    val titlesWithDepths = for {
      (depth, el) <- withDepths(els)
      title        = el.text.trim.replaceFirst("""\[edit\]$""", "").title
      text         = el.normalName match {
        case "a" => md.link(title, el.absUrl("href"))
        case _   => title
      }
    } yield (depth, text)
    md.listItemsDepths(titlesWithDepths)
  }

}
