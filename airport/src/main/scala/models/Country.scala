case class Country(code: String, name: String, continent: String, wikipediaLink: String, keywords: String)
object Country {
  def fromCsvLine(line: String): Option[Country] = {
    val fields = line.split(",")
    if (fields.length >= 5) {
      Some(Country(fields(0).trim, fields(1).trim, fields(2).trim, fields(3).trim, fields(4).trim))
    } else None
  }
}