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
        els.listItems(document.$$("p.x01-FM-Contents-CT,p.x01-FM-Contents-A-Head, p.x01-FM-Contents-FM-Head"))
    }
}