package io.s19f
package x

import scala.util.matching.Regex

package object regex {

  class RegexOps(regex: Regex) {

    def test(source: CharSequence): Boolean =
      regex.pattern.matcher(source).find

  }

  implicit def ops(r: Regex) = new RegexOps(r)

}
