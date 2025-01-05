package classes

case class Airport(id: Int, ident: String, name: String, isoCountry: String)
object Airport {
  def fromCsvLine(line: String): Option[Airport] = {
    val fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)").map(_.replaceAll("^\"|\"$", ""))
    //val fields = line.split(",")
    if (fields.length >= 4) {
      Some(Airport(fields(0).trim.toInt, fields(1).trim, fields(3).trim, fields(8).trim))
    } else None
  }
}