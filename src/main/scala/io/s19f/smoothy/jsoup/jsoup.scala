package io.s19f
package smoothy

import java.io.File
import org.jsoup.nodes.Document

package object jsoup {

  object Jsoup {
    import org.jsoup.Jsoup.{parse, connect}

    def parseFile(filename: String): Document =
      parse(new File(filename), "UTF-8")

    def parseURL(url: String): Document =
      connect(url).get

  }

}
