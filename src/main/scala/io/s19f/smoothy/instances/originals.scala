package io.s19f
package smoothy
package instances

import io.s19f.x.strings._
import io.s19f.x.regex._
import io.s19f.x.functions.predicates._
import io.s19f.smoothy._
import io.s19f.smoothy.format.{md, elem}
import io.s19f.smoothy.jsoup.Jsoup
import io.s19f.smoothy.jsoup.nodes._
import io.s19f.smoothy.jsoup.select.Evaluators._

object originals {
  lazy val document = Jsoup.parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0004.html")

  def toc = {
    val chapters = document
      .$$("p.x01-FM-Contents-CT")
      .map(chapter => {
        val title    = chapter.text.trim
        val subtitle = chapter.nextElementSibling.text.trim
        title + "\n" + subtitle
      })
    val anomaly  = document.$("""p.x01-FM-Contents-FM-Head:matches(^\s*Actions for Impact\s*$)""").text.trim
    md.listItems(chapters :+ anomaly)
  }

  import Jsoup._
  lazy val first = parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_000.html")

  /** A chapter may consists of several split documents
    */
  lazy val chapter = Stream(
    first,
    parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_001.html"),
    parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_002.html"),
    parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_003.html"),
    parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_004.html")
  )

  def creativeDestruction = {

    val headings = for {
      doc <- chapter
      h   <- doc.$$("h2.x05-Head-A")
    } yield h

    val title    = first.$("p.x03-Chapter-Title").text.trim
    val epigraph = first.$("p.x03-Chapter-Epigraph-FL").text.trim + " - " +
      first.$("p.x03-Chapter-Epigraph-Source").text.trim

    title + "\n" +
      epigraph + "\n" +
      elem.listItems(headings) + "\n"
  }

  private val fmt = elem
  def creativeDestructionSummary = {
    val els = for {
      doc <- chapter
      el  <- doc.$$("h2.x05-Head-A,p.x03-CO-Body-Text,p.x04-Body-Text,p.x04-Space-Break-Orn")
    } yield el
    fmt.listItemsDepths(
      withDepths(els),
      {
        case sep if sep.className == "x04-Space-Break-Orn" => "---"
      }
    )
  }
}
