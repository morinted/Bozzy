package bozzy

import java.io.FileNotFoundException
import java.util.PropertyResourceBundle

/**
  * Created by ted on 2016-04-06.
  */
object I18n {
  val i18n = new PropertyResourceBundle(getClass.getResource("/bundle/Resources.properties").openStream)
  if (i18n == null) {
    throw new FileNotFoundException("Cannot load resource: /bundle/Resources.properties")
  }
}
