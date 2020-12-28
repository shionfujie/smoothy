package io.s19f
package smoothy
package jsoup

package object select {

  import org.jsoup.nodes.Element
  import org.jsoup.select.QueryParser

  object Evaluators {

    def $(selector: String)(root: Element): Element => Boolean =
      QueryParser.parse(selector).matches(root, _)

  }

}
