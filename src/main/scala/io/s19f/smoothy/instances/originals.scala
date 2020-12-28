package io.s19f
package smoothy
package instances

import io.s19f.x.strings._
import io.s19f.x.regex._
import io.s19f.x.functions.predicates._
import io.s19f.smoothy.format.{md, els}
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

  def creativeDestruction = {
    import Jsoup._
    val headings = for {
      doc <- Stream(
        parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_000.html"),
        parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_001.html"),
        parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_002.html"),
        parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_003.html"),
        parseFile("/Users/shion.t.fujie/epub/Originals How Non-Conformists Move the World by Adam Grant/text/part0006_split_004.html")
      )
      h   <- doc.$$("h2.x05-Head-A")
    } yield h
    els.listItems(headings)
  }

}
