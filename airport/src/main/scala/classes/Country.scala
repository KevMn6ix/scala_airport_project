package classes

case class Country(id: String, code: String, name: String, continent: String, wikipediaLink: String, keywords: String)
object Country {
  def fromCsvLine(line: String): Option[Country] = {
    val fields = parseCsvLine(line)
    if (fields.length >= 5) {
      val id = fields(0).trim
      val code = fields(1).trim
      val name = fields(2).trim
      val continent = fields(3).trim
      val wikipediaLink = fields(4).trim
      val keywords = if (fields.length > 5) fields(5).trim else "" 

      Some(Country(id, code, name, continent, wikipediaLink, keywords))
    } else {
      println(s"Ligne ignorée : $line") 
      None
    }
  }

  private def parseCsvLine(line: String): Array[String] = {
    val regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
    line.split(regex).map(_.replaceAll("^\"|\"$", ""))
  }
}