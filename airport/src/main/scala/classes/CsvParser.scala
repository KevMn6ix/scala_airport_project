package classes

import classes.Country
import classes.Airport
import classes.Runway

import scala.io.Source

object CsvParser {
  def parseCountries(filePath: String): List[Country] = {
    val lines = Source.fromFile(filePath).getLines().toList.tail
    lines.flatMap(line => Country.fromCsvLine(line))
  }

  def parseAirports(filePath: String): List[Airport] = {
    val lines = Source.fromFile(filePath).getLines().toList.tail
    lines.flatMap(line => Airport.fromCsvLine(line))
  }

  def parseRunways(filePath: String): List[Runway] = {
    val lines = Source.fromFile(filePath).getLines().toList.tail
    lines.flatMap(line => Runway.fromCsvLine(line))
  }
}
    