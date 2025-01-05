package classes


case class Runway(id: Int, airportRef: Int, surface: String, leIdent: String)
object Runway {
  def fromCsvLine(line: String): Option[Runway] = {
    val fields = line.split(",")
    if (fields.length >= 4) {
      Some(Runway(fields(0).trim.toInt, fields(1).trim.toInt, fields(2).trim, fields(3).trim))
    } else None
  }
}