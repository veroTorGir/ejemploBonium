$env:JAVA_HOME = "C:\Program Files\Microsoft\jdk-17.0.18.8-hotspot"
$env:PATH = "$env:JAVA_HOME\bin;" + $env:PATH

if (-not (Test-Path ".\.maven\apache-maven-3.9.6\bin\mvn.cmd")) {
    Write-Host "Descargando Maven..."
    Invoke-WebRequest -Uri "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip" -OutFile "maven.zip"
    Write-Host "Extrayendo Maven..."
    Expand-Archive -Path "maven.zip" -DestinationPath ".\.maven" -Force
    Remove-Item "maven.zip"
}

Write-Host "Ejecutando Spring Boot..."
.\.maven\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run
