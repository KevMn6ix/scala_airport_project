import classes._ 
import java.io.File

import scala.io.StdIn.readLine

object Queries {
  def queryGUI(input: String, countries: List[Country], airports: List[Airport], runways: List[Runway]): String = {
    val sb = new StringBuilder
    val exactMatches = countries.filter { country =>
      country.name.equalsIgnoreCase(input) || country.code.equalsIgnoreCase(input)
    }

    if (exactMatches.isEmpty) {
      sb.append(s"Aucun pays trouvé pour : $input\n")
    } else {
      exactMatches.foreach { country =>
        sb.append(s"Pays : ${country.name} (${country.code})\n")
        val countryAirports = airports.filter(_.isoCountry == country.code)
        if (countryAirports.isEmpty) {
          sb.append(s"  Aucun aéroport trouvé.\n")
        } else {
          sb.append("  Aéroports :\n")
          countryAirports.foreach { airport =>
            sb.append(s"    - ${airport.name} (${airport.ident})\n")
            val airportRunways = runways.filter(_.airportRef == airport.id)
            if (airportRunways.isEmpty) {
              sb.append(s"      Aucun piste trouvée.\n")
            } else {
              sb.append("      Pistes :\n")
              airportRunways.foreach { runway =>
                sb.append(s"        - Surface : ${runway.surface}\n")
              }
            }
          }
        }
      }
    }
    sb.toString()
  }
}