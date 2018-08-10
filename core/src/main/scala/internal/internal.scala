package lol.http

package object internal {

  private[lol] def extract(url: String): (String, String, Int, String, Option[String]) = {
    val url0 = new java.net.URL(url)
    val path = if(url0.getPath.isEmpty) "/" else url0.getPath
    val port = url0.getPort
    val host = url0.getHost
    val scheme = url0.getProtocol
    val queryString = Option(url0.getQuery)
    (scheme, host, if(port < 0) url0.getDefaultPort else port, path, queryString)
  }

  private[lol] def guessContentType(fileName: String): String = {
    fileName.split("[.]").lastOption.collect {
      case "css"          => "text/css"
      case "htm" | "html" => "text/html"
      case "txt"          => "text/plain"
      case "js"           => "application/javascript"
      case "gif"          => "images/gif"
      case "png"          => "images/png"
      case "jpg" | "jpeg" => "images/jpeg"
    }.getOrElse("application/octet-stream")
  }

}