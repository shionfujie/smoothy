package io.s19f
package smoothy

import java.io.File
import org.jsoup.nodes.Document

package object jsoup {

  object Jsoup {

    def parseFile(filename: String): Document =
      org.jsoup.Jsoup.parse(new File(filename), "UTF-8")

    def parseURL(url: String): Document =
      org.jsoup.Jsoup.connect(url).get
      
  }

}
