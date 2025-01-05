import classes._ 
import java.io.File

import scala.io.StdIn.readLine

object Reports {
    def displayMenu(countries: List[Country], airports: List[Airport], runways: List[Runway]): Unit = {
        println("Choose a report:")
        println("1. Top 10 countries with most airports")
        println("2. Top 10 countries with least airports")
        println("3. Types of runways by country")
        println("4. Top 10 most common runway latitudes")
        println("5. Return to main menu")

        val choice = scala.io.StdIn.readLine().trim
        choice match {
        case "1" => top10CountriesWithMostAirports(countries, airports)
        case "2" => top10CountriesWithLeastAirports(countries, airports)
        case "3" => runwayTypesByCountry(countries, airports, runways)
        case "4" => top10RunwayLatitudes(runways)
        case "5" => println("Returning to main menu...")
        case _   => println("Invalid option.")
        }
    }
    def top10CountriesWithMostAirports(countries: List[Country], airports: List[Airport]): Unit = {
        val airportCounts = airports.groupBy(_.isoCountry).mapValues(_.size)
        val top10 = airportCounts.toList.sortBy(-_._2).take(10)

        println("Top 10 countries with most airports:")
        top10.foreach { case (countryCode, count) =>
        val countryName = countries.find(_.code == countryCode).map(_.name).getOrElse("Unknown")
        println(s"$countryName ($countryCode): $count airports")
        }
    }

    def top10CountriesWithLeastAirports(countries: List[Country], airports: List[Airport]): Unit = {
        val airportCounts = airports.groupBy(_.isoCountry).mapValues(_.size)
        val bottom10 = airportCounts.toList.sortBy(_._2).take(10)

        println("Top 10 countries with least airports:")
        bottom10.foreach { case (countryCode, count) =>
        val countryName = countries.find(_.code == countryCode).map(_.name).getOrElse("Unknown")
        println(s"$countryName ($countryCode): $count airports")
        }
    }

    def runwayTypesByCountry(countries: List[Country], airports: List[Airport], runways: List[Runway]): Unit = {
        countries.foreach { country =>
        val countryAirports = airports.filter(_.isoCountry == country.code)
        val countryRunways = runways.filter(r => countryAirports.exists(_.id == r.airportRef))
        val runwayTypes = countryRunways.groupBy(_.surface).mapValues(_.size)

        println(s"Runway types in ${country.name}:")
        if (runwayTypes.isEmpty) {
            println("  No runways found.")
        } else {
            runwayTypes.foreach { case (surface, count) =>
            println(s"- $surface: $count")
            }
        }
        }
    }
    
    def top10RunwayLatitudes(runways: List[Runway]): Unit = {
        // Regroupe les pistes par `le_ident` (latitude) et compte les occurrences
        val latitudeCounts = runways
            .groupBy(_.leIdent) // Regroupe par la colonne le_ident
            .mapValues(_.size)  // Compte le nombre d'occurrences par latitude

        // Trie par ordre décroissant et prend les 10 premières
        val top10Latitudes = latitudeCounts.toList.sortBy(-_._2).take(10)

        // Affiche les résultats
        println("Top 10 most common runway latitudes (le_ident):")
        top10Latitudes.foreach { case (latitude, count) =>
            println(s"Latitude $latitude: $count occurrences")
        }
    }


}