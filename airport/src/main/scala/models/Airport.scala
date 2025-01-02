case class Airport(id: Int, ident: String, name: String, isoCountry: String, typeOf: String)
object Airport {
  def fromCsvLine(line: String): Option[Airport] = {
    val fields = line.split(",")
    if (fields.length >= 5) {
      Some(Airport(fields(0).trim.toInt, fields(1).trim, fields(2).trim, fields(3).trim, fields(4).trim))
    } else None
  }
}